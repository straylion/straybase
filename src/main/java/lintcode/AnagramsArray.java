package lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给出一个字符串数组S，找到其中所有的乱序字符串(Anagram)。
 * 如果一个字符串是乱序字符串，那么他存在一个字母集合相同，但顺序不同的字符串也在S中。
 * 
 */
public class AnagramsArray {

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

	/*
	 * create a unique label for a string "cat", "atc" => a1c1t1
	 */
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
}
