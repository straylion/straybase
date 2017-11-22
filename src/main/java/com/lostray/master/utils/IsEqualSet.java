package com.lostray.master.utils;

import java.util.HashSet;
import java.util.Set;

public class IsEqualSet {
	
	static String ip1 = "192.168.1.1";
	static String ip2 = "192.168.1.2";

	public static void main(String[] args) {
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		set1.add(ip1);
		set1.add(ip2);
		
		set2.add(ip2);
		set2.add(ip1);
		
		if (set1.equals(set2)) {
			System.out.println("================");
		} else {
			System.out.println("!!!!!!!!!!!!!!!!");
		}
	}
	
}
