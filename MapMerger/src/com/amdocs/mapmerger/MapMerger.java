package com.amdocs.mapmerger;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.amdocs.mapmerger.behaviors.MergingBehavior;

/***
 * MapMerger class provides api to merge two maps and list of maps according to
 * the merging behavior passed.
 * 
 * @author Devanshu Pareek
 * @param <K>
 * @param <V>
 */
public class MapMerger<K, V> {

	/***
	 * Merges input maps according to the merging behavior passed.
	 * 
	 * @param map1
	 * @param map2
	 * @param mergingBehavior
	 * @return mergedMap
	 */
	public Map<K, V> mergeMaps(Map<K, V> map1, Map<K, V> map2, MergingBehavior<V> mergingBehavior) {

		if (map1 == null || map2 == null || mergingBehavior == null) {
			return null;
		}

		Map<K, V> biggerMap = null;
		Map<K, V> smallerMap = null;

		if (map1.size() > map2.size()) {
			biggerMap = map1;
			smallerMap = map2;
		} else {
			biggerMap = map2;
			smallerMap = map1;
		}

		for (Iterator<Entry<K, V>> iterator = smallerMap.entrySet().iterator(); iterator.hasNext();) {

			Entry<K, V> entry = iterator.next();
			V value = biggerMap.get(entry.getKey());

			if (value != null) {
				biggerMap.put(entry.getKey(), mergingBehavior.merge(value, entry.getValue()));
			} else {
				biggerMap.put(entry.getKey(), entry.getValue());
			}
		}

		// Using Java 8 --

		// Map<K,V> output = biggerMap;
		// smallerMap.forEach((key, value) -> output.merge(key, value, mergingBehavior::merge));

		return biggerMap;
	}

	/***
	 * Merges input list of map into one map according to the merging behavior
	 * passed.
	 * 
	 * @param listOfMap
	 * @param mergingBehavior
	 * @return mergedMap
	 */
	public Map<K, V> mergeMaps(List<Map<K, V>> listOfMap, MergingBehavior<V> mergingBehavior) {

		if (listOfMap == null || mergingBehavior == null) {
			return null;
		} else if (listOfMap.size() == 1) {
			return listOfMap.get(0);
		} else if (listOfMap.size() == 2) {
			return mergeMaps(listOfMap.get(0), listOfMap.get(1), mergingBehavior);
		} else {
			return listOfMap.stream().parallel().flatMap(map -> map.entrySet().stream()).collect(
					Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue, mergingBehavior::merge));
		}
		
		/*
		 * Another way --
		 * 
		 * ConcurrentHashMap<K, V> cHmap= new ConcurrentHashMap<>();
		 * listOfMap.stream().parallel().forEach(map->map.forEach((key, value->cHmap.merge(key, value, mergingBehavior::merge)))); 
		 * return cHmap;
		 */
	}
}
