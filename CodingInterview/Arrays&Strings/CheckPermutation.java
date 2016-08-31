/**
	Author	: Tom Choi
	Date	: 08/31/2016
	
	Given two strings, write a method to decide
	if one is a permutation of the other.
	
	Observation
		If one is a permutation of the other,
		then it must have the same number of characters
*/

import java.util.*;

public class CheckPermutation{
	public static void main(String[] args){
		String s1 = "abduijk";
		String s2 = "djkuiba";
		System.out.println(isPerm(s1, s2));
		
		s1 = "brightness";
		s2 = "spriteness";
		System.out.println(isPerm(s1, s2));
	}
	
	public static boolean isPerm(String s1, String s2){
		HashMap<Character, Integer> map1 = generateMap(s1);
		HashMap<Character, Integer> map2 = generateMap(s2);
		return compareMaps(map1, map2);
	}
	
	/**
	* Map frequencies of each character in a string
	*/
	private static HashMap<Character, Integer> generateMap(String s){
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(!map.containsKey(c)){
				map.put(c, 1);
			}else{
				map.put(c, map.get(c) + 1);
			}
		}return map;
	}
	
	/**
	* See if both maps have the same character-frequency pairs
	*/
	private static boolean compareMaps(HashMap<Character,Integer> m1,
										HashMap<Character,Integer> m2){
		for(char key : m1.keySet()){
			if(!m2.containsKey(key)){
				return false;
			}
			if(m1.get(key) != m2.get(key)){
				return false;
			}
		}return true;
	}
}