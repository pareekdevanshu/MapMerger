package com.amdocs.mapmerger.behaviors;

/***
 * MergingBehavior interface to define the merging criteria to merge two maps in
 * case of common keys.
 * 
 * @author Devanshu Pareek
 * @param <T>
 */
public interface MergingBehavior<T> {

	public abstract T merge(T value1, T value2);

}
