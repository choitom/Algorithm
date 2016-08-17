/**
	Author	: Tom Choi
	Date	: 08/17/2016
	
	Problem
	-> Given an input string and ordering string,
		return true if the ordering string is present in the input string
		otherwise, return false
		
	Strategy
	1. Mapping
		- For each character from the beginning, map its ordering
	2. Check orderings
		- See if the ordering violates the ordering in the map
*/

import java.util.*;

public class Ordering{
	private HashMap<Character, Integer> orderMap;
	private String input;
	private String pattern;
	
	public Ordering(String input, String pattern){
		this.input = input;
		this.pattern = pattern;
		mapOrder();
	}
	
	/**
	* Check for valid orderings
	*/
	public boolean checkOrdering(){
		char[] patChars = pattern.toCharArray();
		int i;
		for(i = 0; i < patChars.length-1; i++){
			if(orderMap.get(patChars[i]) > orderMap.get(patChars[i+1])){
				return false;
			}
		}
		return true;
	}
	
	/**
	* Maps the order of each character in the input string
	*/
	private void mapOrder(){
		orderMap = new HashMap<Character, Integer>();
		char[] inputChars = input.toCharArray();
		int i;
		char c;
		int order = 0;
		for(i = 0; i < inputChars.length; i++){
			c = inputChars[i];	
			orderMap.put(c, order);
			order++;
		}
	}
	
	public static void main(String[] args){
		String input = "hello world!";
		String ordering = "helo!"; // not all 'l' are in front of 'o' -> false
		Ordering order = new Ordering(input, ordering);
		System.out.println(order.checkOrdering());
	}
}