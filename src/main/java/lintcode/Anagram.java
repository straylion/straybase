package lintcode;

/**
 * 写出一个函数 anagram(s, t) 判断两个字符串是否可以通过改变字母的顺序变成一样的字符串。
 * 
 * @author stray
 *
 */
public class Anagram {

	/**
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean anagram(String s, String t) {
		if (s == null || t == null || s.length() != t.length()) {
			return false;
		}
		int[] count = new int[256];
		for (int i = 0; i < s.length(); i++) {
			count[(int) s.charAt(i)]++;
			count[(int) t.charAt(i)]--;
		}
		for (int k = 0; k < count.length; k++) {
			if (count[k] != 0) {
				return false;
			}
		}
		return true;
	}
}
