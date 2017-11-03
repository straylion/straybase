package lintcode.strings;

import org.junit.Assert;
import org.junit.Test;

import lintcode.strings.Anagram;

public class AnagramTest {
	
	Anagram an = new Anagram();

	@Test
	public void test01() {
		String a = null;
		String b = null;
		Assert.assertEquals(false, an.anagram(a, b));
	}
	
}
