/**
	Author	: Tom Choi
	Date	: 08/31/2016
	
	There are three types of edits that can be performed on strings
		1. insert a character
		2. remove a character
		3. replace a character
	Write a function to check if they are one or zero edits away
	
	Observation
		- obviously if length difference is greater than 1, more than 1 edit away
		- read inputs from the beginning and if it detects any difference
		  execute one of the following operations
		- inserting a character is technically the same as removing
		  because removing a character from the longer string will yield
		  the shorter string if they are one edit away from each other
		- otherwise, replacement will do
*/

public class OneEditAway{
	public static void main(String[] args){
		System.out.println(oneEditAway("pale", "ple"));		// true
		System.out.println(oneEditAway("pales", "pale"));	// true
		System.out.println(oneEditAway("pale", "bake"));	// false
		System.out.println(oneEditAway("pale", "bale"));	// true
	}
	
	public static boolean oneEditAway(String s1, String s2){
		int len1 = s1.length();
		int len2 = s2.length();
		
		if(Math.abs(len1-len2) > 1){
			return false;
		}
		
		// removal -> simply skip over the different character in the longer string
		if(len1 != len2){
			boolean removed = false;
			String shorter = getShorterString(s1, s2);
			String longer = s1;
			if(len1 == shorter.length()){
				longer = s2;
			}
			int index = 0;
			for(int i = 0; i < shorter.length(); i++){
				if(shorter.charAt(i) != longer.charAt(index++)){
					if(removed == false){
						removed = true;
						index++;
					}else{
						return false;
					}
				}
			}
		}
		// replacement
		else{
			int replaceCount = 0;
			for(int i = 0; i < len1; i++){
				if(s1.charAt(i) != s2.charAt(i)){
					replaceCount++;
				}
				if(replaceCount > 1){
					return false;
				}
			}
		}
		return true;
	}
	
	private static String getShorterString(String s1, String s2){
		return (s1.length() > s2.length()) ? s2 : s1;
	}
}