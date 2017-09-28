package lintcode;

import org.junit.Assert;
import org.junit.Test;

public class AnagramTest {
	
	Anagram an = new Anagram();

	@Test
	public void test01() {
		String a = null;
		String b = null;
		Assert.assertEquals(false, an.anagram(a, b));
	}
	
}
