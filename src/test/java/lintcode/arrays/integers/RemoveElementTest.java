package lintcode.arrays.integers;

import org.junit.Test;

public class RemoveElementTest {

	RemoveElement re = new RemoveElement();
	int[] A = { 0, 4, 4, 0, 4, 4, 4, 0, 2, 5 };
	int elem = 4;

	@Test
	public void test01() {
		System.out.println(re.removeElement(A, elem));
	}
}
