/**
	Author	: Tom Choi
	Date	: 08/31/2016
	
	Given two strings, s1 and s2, write code to check if s2 is
	a rotation of s1 using only one call to isSubstring.
	
	Observation
		-> the ordering of string doesn't change even if rotated.
		-> split the rotated string into two from the beginning
		   and see if both parts are substrings of the original one.
	
	Efficiency
		Let the lengths of both strings be N
		1. splitting the rotated string		-> O(N)
		2. checking substrings				-> O(N)
		Overall runtime = O(N^2)
*/
import java.util.*;

public class StringRotation{
	public static void main(String[] args){
		String s1 = "waterbottle";
		String s2 = "erbottlewat";
		System.out.println(isRotated(s1, s2));
		
		s1 = "lololol";
		s2 = "yaloloa";
		System.out.println(isRotated(s1, s2));
	}
	
	private static boolean isSubstring(String s1, String sub){
		for(int i = 0; i <= s1.length() - sub.length(); i++){
			if(sub.equals(s1.substring(i, i+sub.length()))){
				return true;
			}
		}
		return false;
	}

	public static boolean isRotated(String s1, String s2){
		for(int i = 0; i < s1.length(); i++){
			String sub1 = s2.substring(0,i);
			String sub2 = s2.substring(i+1);
			if(isSubstring(s1, sub1) && isSubstring(s1, sub2)){
				return true;
			}
		}return false;
	}
}