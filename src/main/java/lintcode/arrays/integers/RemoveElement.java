package lintcode.arrays.integers;

public class RemoveElement {

	/*
	 * @param A: A list of integers
	 * 
	 * @param elem: An integer
	 * 
	 * @return: The new length after remove
	 */
	public int removeElement(int[] A, int elem) {
		// write your code here
		int num = 0;
		int len = A.length;
		for (int i = 0; i < len; i++) {
			if (A[i] == elem) {
				num++;
			}
		}
		int n = len - num;
		int[] newA = new int[n];
		for (int i = 0, j = 0; i < n && j < len; i++, j++) {
			while (A[j] == elem) {
				j++;
			}
			newA[i] = A[j];
		}
		for (int i = 0; i < n; i++) {
			System.out.print(newA[i] + " ");
		}
		System.out.println("");
		return newA.length;
	}

}
