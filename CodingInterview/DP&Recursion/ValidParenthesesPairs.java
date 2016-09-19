/**
	Author	: Tom Choi
	Date	: 09/18/2016
	
	Implement an algorithm to print all valid combinations
	of n pairs of parentheses.
*/

import java.util.*;

public class ValidParenthesesPairs{
	/**
		1. Find all possible combinations
		2. See if each combination is valid or not using stack
	*/
	public static HashSet<String> set;
	
	public static void main(String[] args){
		set = new HashSet<String>();
		int n = 5;
		
		// check for valid input
		if(n == 0){
			return;
		}
		
		String parens = getParen(n);
		findPermutations(parens);
		
		for(String str : set){
			if(validPair(str)){
				System.out.println(str);
			}
		}
	}
	
	private static boolean validPair(String str){
		/**
			if open paren
				-> push it to the stack
			if close paren 
				-> pop the stack
					if stack is empty: return false
			return true
		*/
		// if starts with close paren, return false
		if(str.charAt(0) == ')'){
			return false;
		}
		Deque<Character> stack = new ArrayDeque<Character>();
		stack.push(str.charAt(0));
		
		int index = 1;
		while(index < str.length()){
			char c = str.charAt(index++);
			
			// open paren: push
			if(c == '('){
				stack.push(c);
			}
			// close paren
			else{
				// nothing to pop -> mismatch
				if(stack.isEmpty()){
					return false;
				}
				// match
				else{
					stack.pop();
				}
			}
		}
		return true;
	}
	
	private static void findPermutations(String parens){
		// check for valid input
		if(parens.equals("")){
			return;
		}
		
		ArrayList<String> perms = new ArrayList<String>();
		
		// add the very first character
		perms.add(String.valueOf(parens.charAt(0)));
		
		// for each character
		for(int i = 1; i < parens.length(); i++){
			ArrayList<String> buffer = new ArrayList<String>();
			char c = parens.charAt(i);
			
			// for each permutation found so far
			for(int j = 0; j < perms.size(); j++){
				String perm = perms.get(j);
				
				// insert a character to each position
				for(int k = 0; k <= perm.length(); k++){
					String newPerm = perm.substring(0,k) + c + perm.substring(k);
					buffer.add(newPerm);
				}
			}
			
			// update the list of permutations
			perms = new ArrayList<String>(buffer);
		}
		
		// exclude duplicates
		for(int i = 0; i < perms.size(); i++){
			set.add(perms.get(i));
		}
	}
	
	private static String getParen(int n){
		String paren = "";
		for(int i = 0; i < n; i++){
			paren += '(';
			paren += ')';
		}
		return paren;
	}
}