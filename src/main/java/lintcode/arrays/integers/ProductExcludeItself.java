package lintcode.arrays.integers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class ProductExcludeItself {
	/*
	 * @param nums: Given an integers array A
	 * 
	 * @return: A long long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... *
	 * A[n-1]
	 */
	public List<Long> productExcludeItself(List<Integer> nums) {
		// write your code here
		ArrayList<Long> B = new ArrayList<Long>();
		Long mul = 1l;
		for (int i = 0; i < nums.size(); i++) {
			for (int j = 0; j < nums.size(); j++) {
				if (i != j) {
					mul *= nums.get(j);
				}
			}
			B.add(mul);
			mul = 1l;
		}
		return B;
	}

	@Test
	public void test() {
		ProductExcludeItself pei = new ProductExcludeItself();
		List<Integer> nums = new LinkedList<Integer>();
		nums.add(1);
		nums.add(2);
		nums.add(3);
		nums.add(4);
		nums.add(5);
		nums.add(6);
		nums.add(7);
		nums.add(8);
		System.out.println(pei.productExcludeItself(nums));
	}
}