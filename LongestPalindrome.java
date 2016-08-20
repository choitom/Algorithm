/**
	Author	: Tom Choi
	Date	: 08/20/2016
	
	Problem
	-> Given a string, return the longest palindrome that can be constructed
	by removing or shuffling characters
	
	Strategy
	-> Map each chacracter and its frequency
	-> Find the one that has the greatest odd frequency
	   and make other odd frequencies even
	-> Append characters with even frequencies half the time
	-> Append the one with the greatest odd frequency
	-> Reverse append even frequencies
*/

import java.util.*;

public class LongestPalindrome{
	private HashMap<Character, Integer> map;
	private String input;
	
	public LongestPalindrome(String str){
		map = new HashMap<Character,Integer>();
		input = str;
	}
	
	public String longestPalindrome(){
		mapCharacters();
		return findPalindrome();
	}
	
	private void mapCharacters(){
		// initialize map pairs with count 0's
		for(int i = 0; i < input.length(); i++){
			map.put(input.charAt(i), 0);
		}
		
		for(int i = 0; i < input.length(); i++){
			int v = map.get(input.charAt(i));
			map.put(input.charAt(i), v + 1);
		}
	}
	
	private String findPalindrome(){
		Set<Character> keySet = map.keySet();
		
		// array of keys
		Object[] keySetArr = keySet.toArray();
		int[] keyValueArr = new int[keySetArr.length];
		
		// store values of keys
		for(int i = 0; i < keyValueArr.length; i++){
			keyValueArr[i] = map.get(keySetArr[i]);
		}
		
		// find max odd values
		int maxOdd = -1;
		int maxOddIndex = -1;
		for(int i = 0; i < keyValueArr.length; i++){
			if(keyValueArr[i] % 2 == 1 && keyValueArr[i] > maxOdd){
				maxOdd = keyValueArr[i];
				maxOddIndex = i;
			}
		}
		
		// make every odd value other than maxOdd even
		if(maxOddIndex != -1){
			for(int i = 0; i < keyValueArr.length; i++){
				if(i != maxOddIndex && keyValueArr[i] % 2 == 1){
					keyValueArr[i] -= 1;
				}
			}
		}
		
		String longestPal = "";
		for(int i = 0; i < keySetArr.length; i++){
			if(i != maxOddIndex){
				for(int j = 0; j < keyValueArr[i] / 2; j++){
					longestPal += keySetArr[i];
				}
			}
		}
		
		for(int i = 0; i < maxOdd; i++){
			longestPal += keySetArr[maxOddIndex];
		}
		
		for(int i = keySetArr.length-1; i >= 0; i--){
			if(i != maxOddIndex){
				for(int j = 0; j < keyValueArr[i] / 2; j++){
					longestPal += keySetArr[i];
				}
			}
		}
		return longestPal;
	}
	
	public static void main(String[] args){
		String str = "gggaaa";
		LongestPalindrome lp = new LongestPalindrome(str);
		String pal = lp.longestPalindrome();
		System.out.println(pal);
	}
}