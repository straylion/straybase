package lintcode;

import org.junit.Test;

public class AnagramsTest {

	AnagramsArray aa = new AnagramsArray();
	String[] str = {"abcd", "abdc", "fwef", "acde", "dbac"};
	
	@Test
	public void test01() {
		System.out.println(aa.anagramsArray(str));
	}
	
}
