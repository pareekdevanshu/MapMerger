package com.amdocs.mapmerger.runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amdocs.mapmerger.MapMerger;
import com.amdocs.mapmerger.behaviors.AddIntegerBehavior;

/***
 * Class to test the API.
 * 
 * @author dev
 *
 */
public class MapMergerTest {

	public static void main(String[] args) {
		MapMerger<Integer, Integer> mapMerger = new MapMerger<>();
		Map<Integer, Integer> map1 = getInputMap1(3);
		Map<Integer, Integer> map2 = getInputMap2(5);
		Map<Integer, Integer> map3 = getInputMap3(15);
		List<Map<Integer, Integer>> listOfMap = new ArrayList<>();

		listOfMap.add(map1);
		listOfMap.add(map2);
		listOfMap.add(map3);
		listOfMap.add(getInputMap1(12));
		listOfMap.add(getInputMap2(6));
		listOfMap.add(getInputMap3(18));
		listOfMap.add(getInputMap3(2));

		long t1 = System.currentTimeMillis();

		System.out.println(
				"Size of merged map(2 input) - " + mapMerger.mergeMaps(map1, map2, new AddIntegerBehavior()).size());
		System.out.println("Time Taken - " + (System.currentTimeMillis() - t1));

		t1 = System.currentTimeMillis();
		System.out.println(
				"Size of merged map(list input) - " + mapMerger.mergeMaps(listOfMap, new AddIntegerBehavior()).size());
		System.out.println("Time Taken - " + (System.currentTimeMillis() - t1));

	}

	private static Map<Integer, Integer> getInputMap1(int j) {
		int size = 1000000;
		Map<Integer, Integer> map1 = new HashMap<>(size + 10, 1);
		for (int i = 0; i < size; i++) {
			map1.put(i * j, i * j);
		}
		return map1;
	}

	private static Map<Integer, Integer> getInputMap2(int j) {
		int size = 2000000;
		Map<Integer, Integer> map1 = new HashMap<>(size + 10, 1);
		for (int i = 0; i < size; i++) {
			map1.put(i * j, i * j);
		}

		return map1;
	}

	private static Map<Integer, Integer> getInputMap3(int j) {
		int size = 100000;
		Map<Integer, Integer> map1 = new HashMap<>(size + 10, 1);
		for (int i = 0; i < size; i++) {
			map1.put(i * j, i * j);
		}

		return map1;
	}

}
