package lintcode.arrays.integers;

public class TwoSum {
	/*
	 * @param numbers: An array of Integer
	 * 
	 * @param target: target = numbers[index1] + numbers[index2]
	 * 
	 * @return: [index1 + 1, index2 + 1] (index1 < index2)
	 */
	public int[] twoSum(int[] numbers, int target) {
		// write your code here

		int[] a = new int[2];
		int i;
		int n = numbers.length;
		for (i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (numbers[i] + numbers[j] == target) {
					a[0] = i;
					a[1] = j;
					return a;
				}
			}
		}
		return null;
	}
}