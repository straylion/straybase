package lintcode.strings;

import org.junit.Test;

import lintcode.strings.AnagramsArray;

public class AnagramsTest {

	AnagramsArray aa = new AnagramsArray();
	String[] str = {"abcd", "abdc", "fwef", "acde", "dbac"};
	
	@Test
	public void test01() {
		System.out.println(aa.anagramsArray(str));
	}
	
}
