/**
	Author	: Tom Choi
	Date	: 08/30/2016
	
	Problem
		Given a smaller string s and a bigger string b, design an algorithm
		to find all permutations of the shorter string within the longer one.
		Print the location of each permutation.
	
	Brute Force (Highly not recommended)
		1. Find all permutations of string a	-> O(A!)
		2. From each segment of string b		-> O(B)
			see if it is a permutation of a
		
		The overall runtime is O(A! * B)
		
	Observation
		Starting from the beginning to the end of b, see if the frequencies
		of each character matches those of a.
	
	Strategy
		Map the frequencies of each character (Use HashMap)
		From beg to end
			Find a substring, map its frequencies
			Compare each frequency to those of a
			If match -> store its index
			Not decrement the frequency of the first character
			
	Analysis
		Let A be the length of string a
		Let B be the length of string b
		
		1. Generating the map for a			-> O(A)
		2. Initializing the map for b		-> O(A)
		3. Iterations of the for loop		-> O(B)
			comparing map a and b			-> O(A)
			adding and subtracting values	-> O(1)
		4. Comparing the last segment		-> O(A)
		5. Printing out the result			-> O(B)
		
		The overall runtime is O(A * B)
*/

import java.util.*;

public class LocationOfPermutations{
	public static void main(String[] args){
		String a = "abbc";
		String b = "cbabadcbbabbcbabaabccbabc";
		int lenA = a.length();
		int lenB = b.length();
		
		HashMap<Character, Integer> aMap = new HashMap<Character, Integer>();
		HashMap<Character, Integer> bMap = new HashMap<Character, Integer>();
		
		String sub = b.substring(0, lenA);
		generateMap(aMap, a);
		generateMap(bMap, sub);

		ArrayList<Integer> result = new ArrayList<Integer>();
		char toLeave;
		char toCome;
		boolean isPerm;
		for(int i = 0; i < lenB - lenA; i++){
			toLeave = b.charAt(i);
			toCome = b.charAt(i + lenA);
			isPerm = compareMaps(aMap, bMap);
			if(isPerm)
				result.add(i);
			
			bMap.put(toLeave, bMap.get(toLeave) - 1);
			if(!bMap.containsKey(toCome)){
				bMap.put(toCome, 1);
			}else{
				bMap.put(toCome, bMap.get(toCome) + 1);
			}
		}
		isPerm = compareMaps(aMap, bMap);
		if(isPerm)
			result.add(lenB-lenA);
		
		// print out the result
		for(int i = 0; i < result.size(); i++){
			System.out.print(result.get(i) + " ");
		}System.out.println();
	}
	
	/**
	* Map the frequencies of each character in a string
	*
	* @param	str		string
	* @param	map		pairs of character and its frequencies
	* @return	mapped pairs
	*/
	private static void generateMap(HashMap<Character, Integer> map, String str){
		for(int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			if(!map.containsKey(c)){
				map.put(c, 1);
			}else{
				map.put(c, map.get(c) + 1);
			}
		}
	}
	
	/**
	* Compare values associated with each key in two maps
	*
	* @param	aMap	first map
	* @param	bMap	second map
	* @return	true if the second map has all the keys in the first map and same values
	*/
	private static boolean compareMaps(HashMap<Character, Integer> aMap, HashMap<Character, Integer> bMap){
		for(char key : aMap.keySet()){
			if(!bMap.containsKey(key) ||
				bMap.get(key) != aMap.get(key)){
				return false;	
			}
		}return true;
	}
	
	/**
	* Print out the pairs of character and its frequencies
	*
	* @param	map		map to print out
	*/
	private static void printMap(HashMap<Character, Integer> map){
		for(char key : map.keySet()){
			System.out.print(key + ": " + map.get(key) + " ");
		}System.out.println();
	}
}