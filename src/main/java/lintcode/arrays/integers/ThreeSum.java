package lintcode.arrays.integers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ThreeSum {
	/*
	 * @param numbers: Give an array numbers of n integer
	 * 
	 * @return: Find all unique triplets in the array which gives the sum of zero.
	 */
	public List<List<Integer>> threeSum(int[] numbers) {
		// write your code here
		List<List<Integer>> sums = new ArrayList<List<Integer>>();
		List<Integer> a = new ArrayList<Integer>();
		a.add(numbers[0]);
		sums.add(a);
		
		return sums;
	}
	
	@Test
	public void test() {
		ThreeSum ts = new ThreeSum();
		int[] numbers = {1,2,2,-4,2,-6,54,87,-1,-3};
		System.out.println(ts.threeSum(numbers));
	}
}