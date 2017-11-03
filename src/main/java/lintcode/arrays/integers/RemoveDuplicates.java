package lintcode.arrays.integers;

public class RemoveDuplicates {

	/*
	 * @param nums: An integer array
	 * 
	 * @return: An integer
	 */
	public int removeDuplicates(int[] nums) {
		// write your code here
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int index = 1;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i + 1] != nums[i]) {
				nums[index] = nums[i + 1];
				index++;
			}
		}
		return index;
	}
}