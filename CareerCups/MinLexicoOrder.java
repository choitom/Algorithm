/**
	Author	: Tom Choi
	Date	: 09/07/2016

	Strategy: Build the lexicographically smallest string as you read one by one
		a. Initialize HashMap of (character, index) pairs
		b. Initialize empty string for result
		c. Read a character
			(i)	If map doesn't have a character, add it to the result
			(ii)If map has a character, then compare it to the string either
				- with the prev result
				- with the redundant char removed and added the char
		
		OPT[k] = (1) OPT[k-1].append(k) if first occurrence of kth char
				 (2) min(OPT[k-1], (OPT[k-1].deleteCharAt(k)).append(k)) if kth char already exists

	Example
		Iteration			Lexocigraphically Min
		c			-> 		c
		cb			->		c b
		cba			->		c b a
		cbac	 	->		min(cba, bac) = bac
		cbacd		->		bacd
		cbacdc		->		min(bacd, badc) = bacd
		cbacdcb		->		min(bacd, acdb)	= acdb
		cbacdcbc	->		min(acdb, adbc)	= acdb
*/

import java.util.*;

public class MinLexicoOrder{
	
	// stores the characters in the string
	private HashSet<Character> set;
	private String input;
	
	public MinLexicoOrder(String input){
		this.set = new HashSet<Character>();
		this.input = input;
	}
	
	public String minString(){
		StringBuilder result = new StringBuilder();
		char[] arr = input.toCharArray();
		for(int i = 0; i < arr.length; i++){
			char c = arr[i];
			
			// first occurrence of the character
			if(!set.contains(c)){
				result.append(c);
				set.add(c);
			}
			// more than one same character
			else{
				// the index of the first conflicting character in the result
				int prev = result.indexOf(String.valueOf(c));
				
				// remove the previous char and append the new one
				StringBuilder temp = new StringBuilder(result);
				temp.deleteCharAt(prev);
				temp.append(c);
				
				// compare the previous minimum string and the current string
				String minimum = min(result.toString(), temp.toString());
				
				// carry the lexicographically smallest string found so far
				result = new StringBuilder(minimum);
			}
		}
		return result.toString();
	}
	
	// compare two strings and return the smaller one
	private String min(String i, String j){
		return (i.compareTo(j) < 0) ? i : j;
	}
	
	public static void main(String[] args){
		String str = "xyzadatyxzttttacacacbcbbb";
		MinLexicoOrder min = new MinLexicoOrder(str);
		System.out.println(min.minString());
	}
}