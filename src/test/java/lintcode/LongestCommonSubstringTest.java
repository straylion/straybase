package lintcode;

import org.junit.Test;

public class LongestCommonSubstringTest {

	String A = "wbb.com code";
	String B = "www.ninechapter.com code";
	LongestCommonSubstring lcs = new LongestCommonSubstring();
	
	@Test
	public void test01() {
		System.out.println(lcs.longestCommonSubstring(A, B));
	}
	
}
