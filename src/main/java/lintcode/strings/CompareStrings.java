package lintcode.strings;

/**
 * 比较两个字符串A和B，确定A中是否包含B中所有的字符。字符串A和B中的字符都是 大写字母
 * 
 * @author stray
 *
 */
public class CompareStrings {

	/**
	 * @param A
	 *            : A string includes Upper Case letters
	 * @param B
	 *            : A string includes Upper Case letter
	 * @return : if string A contains all of the characters in B return true else
	 *         return false
	 */
	public boolean compareStrings(String A, String B) {
		// write your code here
		int aLen = A.length();
		int bLen = B.length();
		if (aLen == 0) {
			return bLen == 0;
		}
		for (int i = 0; i < bLen; i++) {
			String temp = B.substring(i, i + 1);
			if (A.contains(temp))
				A = A.replaceFirst(temp, "");
			else
				return false;
		}
		return true;
	}
}
