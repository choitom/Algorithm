/**
	Author	: Tom Choi
	Date	: 08/18/2016
	
	Problem
	- check if the given string is repeated substring or not
*/

import java.util.*;

public class RepeatedSubstring{
	/** Append a character upto half length of the original string */
	public static boolean repeated(String str){
		String pat = "";
		for(int i = 0; i < str.length()/2; i++){
			pat = pat + str.charAt(i);
			if(isPattern(str, pat)){
				return true;
			}
		}
		return false;
	}
	
	/** Check if repeated pattern consists the whole string */
	private static boolean isPattern(String str, String pat){
		int patNum = str.length()/pat.length();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < patNum; i++){
			sb.append(pat);
		}
		
		if(sb.toString().equals(str)){
			return true;
		}
		return false;
	}
	
	/** Test code */
	public static void main(String[] args){
		String s1 = "abcdabcdabcd";
		String s2 = "abcddcbaabcd";
		String s3 = "AbCaBcAbC";
		System.out.println("\'" + s1 + "\' repeated substring? " + repeated(s1));
		System.out.println("\'" + s2 + "\' repeated substring? " + repeated(s2));
		System.out.println("\'" + s3 + "\' repeated substring? " + repeated(s3));
	}
}