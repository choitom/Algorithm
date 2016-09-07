/**
	Author	: Tom Choi
	Date	: 09/07/2016
	
	Find zero based index of the first occurrence of any character of
	the second string in the first string.
*/

import java.util.*;

public class FirstOccurences{
	public static void main(String[] args){
		String str1 = "adf6ysh";
		String str2 = "1234567";
		
		HashSet<Character> s2 = new HashSet<Character>();
		for(int i = 0; i < str2.length(); i++){
			s2.add(str2.charAt(i));
		}
		
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < str1.length(); i++){
			char c = str1.charAt(i);
			if(s2.contains(c)){
				map.put(c, i);
				s2.remove(c);
			}
		}
		
		for(Character c : map.keySet()){
			System.out.print(c + ": " + map.get(c));
		}
	}
}