/**
	Author	: Tom Choi
	Date	: 08/30/2016
	
	Problem from HackerRank.com
		
		A string ababaewabaq is marked by substring if it becomes like XXXXXewXXXq
		Island is defined as the group X's separated by different characters.
		In the case above, there are two islands.
		
		Given a string and an integer for the number of islands,
		find the number of substrings that satisfy the given number of islands.
		
	Strategy
		Find all substrings (use HashMap)
		For each substring
			Find indices where it maps onto the string
			Convert such indices to 'X'
			Find the number of islands
			
	Limitation
		OutOfMemoryError for big input because of finding all the
		substrings of a string which takes O(n^2).
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class letterIslands {

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("letters.txt");
		Scanner scan = new Scanner(file);
        char[] c = scan.next().toCharArray();
        int k = scan.nextInt();
        mark(c,k);
    }
    
    private static void mark(char[] c, int k){
        HashSet<String> sub = findSubStrings(c);
		String original = String.valueOf(c);
        HashSet<Integer> markedIndex;
        int result = 0;
        
		// For each substring
		for(String substring : sub){
            markedIndex = new HashSet<Integer>();
			int boundary = c.length - substring.length();
            String s;
			
			// Find indices where it maps onto the original string
			for(int i = 0; i <= boundary; i++){
				s = original.substring(i, i + substring.length());
				if(s.equals(substring)){
					for(int j = i; j < i + substring.length(); j++){
						markedIndex.add(j);
					}
				}
			}
			int islands = findIslands(markedIndex, c);
			if(islands == k){
				result++;
			}
        }
        System.out.println(result);
    }
    
	/**
	* Mark off characters and find the number of islands
	*/
	private static int findIslands(HashSet<Integer> markedIndex, char[] c){
		char[] temp = new char[c.length];
		for(int i = 0; i < c.length; i++)
			temp[i] = c[i];
		for(int index : markedIndex)
			temp[index] = 'X';
		String s = String.valueOf(temp);
		return findCount(s);
	}
	
	/**
	* Count the number of X groups
	*/
	private static int findCount(String s){
		int count = 0;
		int index = 0;
		boolean seen = false;
		while(index < s.length()){
			char c = s.charAt(index);
			if(c == 'X'){
				if(seen == false){
					seen = true;
					count++;
				}
			}else{
				seen = false;
			}
			index++;
		}return count;
	}
	
	/**
	* Find every substring of a string
	*/
    private static HashSet<String> findSubStrings(char[] c){
        HashSet<String> sub = new HashSet<String>();
        String subString;
        for(int i = 0; i < c.length; i++){
            subString = "";
            for(int j = i; j < c.length; j++){
                subString += c[j];
                sub.add(subString);
            }
        }return sub;
    }
}