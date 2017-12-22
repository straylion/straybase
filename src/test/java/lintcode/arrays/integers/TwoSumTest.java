package lintcode.arrays.integers;

import org.junit.Test;

public class TwoSumTest {
	
	int[] numbers = {3,4,5,6,9,7,8,1};
	int target = 17;
	TwoSum ts = new TwoSum();

	@Test
	public void test() {
		ts.twoSum(numbers, target);
	}

}
