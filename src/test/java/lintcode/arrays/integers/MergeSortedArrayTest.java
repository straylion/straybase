package lintcode.arrays.integers;

import org.junit.Test;

public class MergeSortedArrayTest {

	int[] A = {1,2,3,0,0};
	int[] B = {4,5};
	int m = 3;
	int n = 2;
	MergeSortedArray msa = new MergeSortedArray();
	@Test
	public void test() {
		msa.mergeSortedArray(A, m, B, n);
	}

}
