package lintcode.arrays.integers;

public class MergeSortedArray {
	/*
	 * @param A: sorted integer array A which has m elements, but size of A is m+n
	 * 
	 * @param m: An integer
	 * 
	 * @param B: sorted integer array B which has n elements
	 * 
	 * @param n: An integer
	 * 
	 * @return: nothing
	 */
	public void mergeSortedArray(int[] A, int m, int[] B, int n) {
		// write your code here
		int pos = m + n - 1;
		int posA = m - 1;
		int posB = n - 1;
		while (posA >= 0 && posB >= 0) {
			if (A[posA] > B[posB]) {
				A[pos--] = A[posA--];
			} else {
				A[pos--] = B[posB--];
			}
		}
		while (posA >= 0) {
			A[pos--] = A[posA--];
		}
		while (posB >= 0) {
			A[pos--] = B[posB--];
		}
	}
}