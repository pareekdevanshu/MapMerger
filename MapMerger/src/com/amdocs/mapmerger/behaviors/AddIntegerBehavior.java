package com.amdocs.mapmerger.behaviors;

/***
 * Defines merging criteria for two Integer values by adding them.
 * 
 * @author Devanshu Pareek
 * 
 *
 */
public class AddIntegerBehavior implements MergingBehavior<Integer> {

	@Override
	public Integer merge(Integer value1, Integer value2) {
		return value1 + value2;
	}

}