package programming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LintcodeSolution {

	/**
	 * 写出一个函数 anagram(s, t) 判断两个字符串是否可以通过改变字母的顺序变成一样的字符串。
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

	/**
	 * 给出一个字符串数组S，找到其中所有的乱序字符串(Anagram) 如果一个字符串是乱序字符串，那么他存在一个字母集合相同，但顺序不同的字符串也在S中。
	 * 
	 */
	public List<String> anagramsArray(String[] strs) {
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		for (String str : strs) {
			// create unique label for each string
			String key = generalLabel(str);
			// map the label to a list of anagrams
			ArrayList<String> res = map.get(key);
			if (res == null) {
				res = new ArrayList<String>();
				map.put(key, res);
			}
			res.add(str);
		}
		ArrayList<String> resSet = new ArrayList<String>();
		for (ArrayList<String> anagram : map.values()) {
			// ignore strings without anagrams
			if (anagram.size() > 1)
				resSet.addAll(anagram);
		}
		return resSet;
	}

	// create a unique label for a string "cat", "atc" => a1c1t1
	public String generalLabel(String str) {
		int[] hash = new int[26];
		for (int i = 0; i < str.length(); i++) {
			int index = (int) (str.charAt(i) - 'a');
			hash[index]++;
		}
		StringBuilder ss = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			if (hash[i] == 0)
				continue;
			char c = (char) ('a' + i);
			ss.append(c);
			ss.append(hash[i]);
		}
		return ss.toString();
	}

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

	/**
	 * 对于一个给定的 source 字符串和一个 target 字符串，你应该在 source 字符串中找出 target
	 * 字符串出现的第一个位置(从0开始)。如果不存在，则返回 -1。
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public int strStr(String source, String target) {
		if (source != null && target != null && source.contains(target)) {
			return source.indexOf(target);
		} else {
			return -1;
		}
	}

	/**
	 * @param strs:
	 *            A list of strings
	 * @return: The longest common prefix
	 */
	public String longestCommonPrefix(String[] strs) {

		return null;
	}

}
