package lintcode;

import org.junit.Test;

public class AnagramsTest {

	Anagrams an = new Anagrams();
	String[] str = {"abcd", "abdc", "fwef", "acde", "dbac"};
	
	@Test
	public void test01() {
		System.out.println(an.anagramsArray(str));
	}
	
}
