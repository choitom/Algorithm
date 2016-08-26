/**
	Author	: Tom Choi
	Date	: 08/26/2016
	
	Problem
		Remove two same consecutive characters in a sequence of letters
		until it becomes unreducible e.g. baab -> bb -> empty string
*/

import java.util.*;
import java.io.*;

public class ReduceString{
	public static void main(String[] args){
		String str = "abbcdrabdbabebqbebbebbbepdoooqoeod";
		ArrayList<Character> lst = new ArrayList<Character>();
		for(int i = 0; i < str.length(); i++){
			lst.add(str.charAt(i));
		}
		print(lst);
		reduce(lst);
	}
	
	private static void reduce(ArrayList<Character> lst){
		int[] pat = findPattern(lst);
		
		/** String unreducible */
		if(pat[0] == Integer.MAX_VALUE){
			String str = "";
			for(int i = 0; i < lst.size(); i++){
				str += lst.get(i);
			}
			if(str.equals("")){
				System.out.println("Empty String");
			}else{
				System.out.println(str);
			}
		}
		/** Reducible */
		else{
			print(lst);
			lst.remove(pat[1]);
			lst.remove(pat[0]);
			reduce(lst);
		}
	}
	
	/**
	* Print out a list
	*/
	private static void print(ArrayList<Character> lst){
		for(int i = 0; i < lst.size(); i++){
			System.out.print(lst.get(i));
		}System.out.println();
	}
	
	/**
	* Find the indice of two consecutive characters
	*/
	private static int[] findPattern(ArrayList<Character> lst){
		int[] pat = new int[2];
		pat[0] = Integer.MAX_VALUE;
		for(int i = 0; i < lst.size()-1; i++){
			if(lst.get(i) == lst.get(i+1)){
				pat[0] = i;
				pat[1] = i+1;
				break;
			}
		}
		return pat;
	}
}