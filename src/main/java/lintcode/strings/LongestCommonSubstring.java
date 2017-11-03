package lintcode.strings;

public class LongestCommonSubstring {

	/*
	 * @param A: A string
	 * 
	 * @param B: A string
	 * 
	 * @return: the length of the longest common substring.
	 */
	public int longestCommonSubstring(String A, String B) {
		int alen = A.length();
		int blen = B.length();
		int[][] a = new int[alen][blen];
		int max = 0;
		for (int i = 0; i < alen; i++) {
			for (int j = 0; j < blen; j++) {
				if (A.charAt(i) != B.charAt(j)) {
					a[i][j] = 0;
				} else {
					if (i == 0 || j == 0) {
						a[i][j] = 1;
					} else {
						a[i][j] = a[i - 1][j - 1] + 1;
					}
					if (a[i][j] > max) {
						max = a[i][j];
					}
				}
			}
		}

		return max;
	}
	
	
	/*
	 * @param A: A string
	 * 
	 * @param B: A string
	 * 
	 * @return: the longest common substring.
	 */
	public String longestCommonSubstring1(String A, String B) {
		int alen = A.length();
		int blen = B.length();
		String css = "";
		int[][] a = new int[alen][blen];
		int max = 0;
		for (int i = 0; i < alen; i++) {
			for (int j = 0; j < blen; j++) {
				if (A.charAt(i) != B.charAt(j)) {
					a[i][j] = 0;
				} else {
					if (i == 0 || j == 0) {
						a[i][j] = 1;
					} else {
						a[i][j] = a[i - 1][j - 1] + 1;
					}
					if (a[i][j] > max) {
						max = a[i][j];
						css = A.substring(i - max + 1, i + 1);
					}
				}
			}
		}
		return css;
	}
}
