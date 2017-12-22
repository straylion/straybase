package lintcode.arrays.integers;

import org.junit.Test;

public class FirstMissingPositive {
	/*
	 * @param A: An Aay of integers
	 * 
	 * @return: An integer
	 */
	public int firstMissingPositive(int[] A) {
		// write your code here
		int l = 0;
		int r = A.length;
		int temp;

		while (l < r) {
			if (A[l] == l + 1) {
				l++;
			} else if (A[l] > r || A[l] <= l || A[A[l] - 1] == A[l]) {
				A[l] = A[--r];
			} else {
				temp = A[l];
				A[l] = A[A[l] - 1];
				A[temp - 1] = temp;
			}
		}
		return l + 1;
	}

	@Test
	public void test() {
		FirstMissingPositive fmp = new FirstMissingPositive();
		int[] A = { 1, 2, 6, 78, 85, 3, 4, -4, -5 };
		System.out.println(fmp.firstMissingPositive(A));
	}
}
