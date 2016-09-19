/**
	Author	: Tom Choi
	Date	: 09/18/2016
	
	Write a permutation of a string.
	The list of permutations should not have duplicates.
*/

import java.util.*;

public class UniqueCharacterPermutation{
	
	public static HashSet<String> set;
	
	public static void main(String[] args){
		String str = "abcc";
		set = new HashSet<String>();
		findPermutation(str);
		for(String s : set){
			System.out.println(s);
		}
	}
	
	/**
		Similar to the power set
		Except that power set add onto the existing subsets
		and put them back.
		Permutation insert an additional character
		into every possible place into the existing strings
	*/
	private static void findPermutation(String str){
		
		// stores the permutations found so far
		ArrayList<String> list = new ArrayList<String>();
		
		// add the very first character for base case
		list.add(String.valueOf(str.charAt(0)));
		
		// for each character
		for(int i = 1; i < str.length(); i++){
			ArrayList<String> buffer = new ArrayList<String>();
			
			// get the permutations found so far
			for(int j = 0; j < list.size(); j++){
				String perm = list.get(j);
				for(int k = 0; k <= perm.length(); k++){
					String newPerm = perm.substring(0,k) + str.charAt(i) + perm.substring(k);
					buffer.add(newPerm);
				}
			}
			list = new ArrayList<String>(buffer);
		}
		
		// add strings to the set to exclude duplicates
		for(int i = 0; i < list.size(); i++){
			set.add(list.get(i));
		}
	}
}