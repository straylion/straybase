package programming;

import org.junit.Test;

public class LintcodeSolutionTest {

	LintcodeSolution ls = new LintcodeSolution();

	@Test
	public void anagramTest() {
        String[] s = {null, "dfvwa", null, "", "fwefw", ""};
        String[] t = {null, null, "fwef", "h", "wewff", ""};
        for (int i = 0; i < s.length; i++)
        System.out.println(ls.anagram(s[i], t[i]));
	}

	@Test
	public void anagramsArrayTest() {
        String[] strs = {"abcd", "cdba", "abc", "cbad"};
        System.out.println(ls.anagramsArray(strs));
	}

	@Test
	public void compareStringsTest() {
        String s = "abcedfg";
        String t = "abcdf";
        System.out.println(ls.compareStrings(s, t));
	}

	@Test
	public void longestCommonSubstringTest() {
        String[] A = {null, "123"};
        String[] B = {null, "1"};
        for (int i = 0; i < 5; i++)
        System.out.println(ls.longestCommonSubstring(A[i], B[i]));
	}

	@Test
	public void strStrTest() {
		String s = "abcedefgaga";
		String t = null;
		System.out.println(ls.strStr(s, t));
	}

	@Test
	public void longestCommonPrefixTest() {

	}

}
