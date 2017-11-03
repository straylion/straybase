package lintcode.strings;

import org.junit.Test;

import lintcode.strings.StringStr;

/**
 * @author stray
 *
 */
public class StringStrTest {

	@Test
	public void test01() {
		String s = "abcedefgaga";
		String t = null;
		StringStr ss = new StringStr();
		System.out.println(ss.strStr(s, t));
	}
	
}
