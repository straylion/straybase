package lintcode.arrays.integers;

import java.util.ArrayList;
import java.util.List;

public class SubarraySum {

	/*
	 * @param nums: A list of integers
	 * 
	 * @return: A list of integers includes the index of the first number and the
	 * index of the last number
	 */
	public List<Integer> subarraySum(int[] nums) {
		// write your code here
		int sum = 0;
		List<Integer> a = new ArrayList<>();
		if (nums.length == 1) {
			if (nums[0] == 0) {
				a.add(0);
				a.add(0);
				return a;
			} else {
				return null;
			}
		}
		for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length; j++) {
				sum += nums[j];
				if (sum == 0) {
					a.add(i);
					a.add(j);
					return a;
				}
			}
			sum = 0;
		}
		return null;
	}

}
