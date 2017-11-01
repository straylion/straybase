package lintcode;

import org.junit.Test;

public class LongestCommonPrefixTest {
	
	LongestCommonPrefix lcp = new LongestCommonPrefix();
	String[] strs = {"ABCDEFG","ABCEFG","ABCEFA"};

	@Test
	public void test01() {
		System.out.println(lcp.longestCommonPrefix(strs));
	}
	
}
