package lintcode;

import org.junit.Assert;
import org.junit.Test;

public class AnagramSolutionTest {
	
	AnagramSolution as = new AnagramSolution();

	@Test
	public void test01() {
		String a = null;
		String b = null;
		Assert.assertEquals(false, as.anagram(a, b));
	}
	
}
