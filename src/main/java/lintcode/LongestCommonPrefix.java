package lintcode;

public class LongestCommonPrefix {
	
	/**
	 * @param strs:
	 *            A list of strings
	 * @return: The longest common prefix
	 */
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) {
            return "";
        }
		String lcp = strs[0];
		for (int i = 1; i < strs.length; i++) {
			int j = 0;
			while (j < (lcp.length() < strs[i].length() ? lcp.length() : strs[i].length()) && lcp.charAt(j) == strs[i].charAt(j)) {
				j++;
			}
			
			lcp = lcp.substring(0, j);
		}
		return lcp;
	}

}
