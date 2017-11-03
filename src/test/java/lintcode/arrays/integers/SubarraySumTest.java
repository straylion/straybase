package lintcode.arrays.integers;

import org.junit.Test;

public class SubarraySumTest {

	SubarraySum sas = new SubarraySum();
	int[] nums = { 4, 5, 5, -14, 12, 5, 2, 13, 1, -16 };

	@Test
	public void test() {
		System.out.println(sas.subarraySum(nums));
	}

}
