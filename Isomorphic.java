/**
	Author	: Tom Choi
	Date	: 08/16/2016
	
	Problem
		-> Given two strings s and t, determine if they are isomorphic.
		Two strings are isomorphic if the characters in s can be replaced to get t
*/

import java.util.*;

public class Isomorphic{
	public static void main(String[] args){
		String s1 = "abcdefg";
		String s2 = "qwertyu";
		boolean iso = isIsomorphic(s1, s2);
		System.out.println(iso);
	}
	
	public static boolean isIsomorphic(String s1, String s2){
		if(s1 == null || s2 == null){
			return false;
		}
		
		if(s1.length() != s2.length()){
			return false;
		}
		
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		
		// for each character
		for(int i = 0; i < s1.length(); i++){
			char c1 = s1.charAt(i); // key
			char c2 = s2.charAt(i); // value
			
			// key already exists i.e. (c1, c2) pair mapped
			if(map.containsKey(c1)){
				if(map.get(c1) != c2){
					return false;
				}
			}
			// key does not exist i.e. (c1, c2) pair does not exist
			else{
				// if c2 is mapped with other key
				if(map.containsValue(c2)){
					return false;
				}
				map.put(c1, c2);
			}
		}
		return true;
	}
}