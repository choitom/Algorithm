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
		String s1 = "boo";
		String[] strs = new String[]{"foo", "baa", "bar", "aAA", "lol"};
		for(int i = 0; i < strs.length; i++){
			System.out.println(isIsomorphic(s1, strs[i]));
		}
	}
	
	public static boolean isIsomorphic(String pattern, String match){
		// see if inputs are null
		if(pattern == null || match == null){
			return false;
		}
		// compare lengths
		if(pattern.length() != match.length()){
			return false;
		}
		
		/**
			Initialize a hash map and store patten-match pair for each character
			Return false if
				- patten already exists, but has a different match for its value
				- match already exists, then it has different pattern for its key
		*/
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		int i;
		char key;
		char value;
		for(i = 0; i < pattern.length(); i++){
			key = pattern.charAt(i);
			value = match.charAt(i);
			
			/** patten exists -> check for its value */
			if(map.containsKey(key)){
				if(map.get(key) != value){
					return false;
				}
			}
			/** pattern not exist -> see if the value exists */
			else{
				if(map.containsValue(value)){
					return false;
				}
				map.put(key, value);
			}
		}
		return true;
	}
}