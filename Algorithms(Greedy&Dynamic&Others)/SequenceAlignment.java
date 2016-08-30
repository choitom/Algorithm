/**
	Author	: Tom Choi
	Date	: 08/29/2016
	
	Implementation of Sequencial Alignment
	(Algorithm & Design by Kleinberg and Tardos)
	
	Idea
		Given two strings M and N, the last characters of M and N,
		m and n, respectively, could be
			a) aligned
			b) m is mismatched
			c) n is mismatched
		If m,n are aligned and don't match we give penalties as follows
			a) vowel - consonant : 3
			b) vowel - vowel :1
			c) consonant - consonant : 1
		Mismatch penalty is 2

*/

import java.io.*;
import java.util.*;

public class SequenceAlignment{
	
	private static HashSet<Character> consonants;
	private static HashSet<Character> vowels;
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("stringAlignment.txt");
		Scanner scan = new Scanner(file);
		
		consonants = new HashSet<Character>();
		vowels = new HashSet<Character>();
		
		/** create vowel set */
		char[] v = {'a', 'e', 'i', 'o', 'u'};
		for(int i = 0; i < v.length; i++){
			vowels.add(v[i]);
		}
		
		/** create consonant set */
		for(int i = 98; i <= 122; i++){
			char c = (char)i;
			if(!vowels.contains(c)){
				consonants.add(c);
			}
		}
		
		int numWords = scan.nextInt();
		for(int i = 0; i < numWords; i++){
			char[] s1 = scan.next().toLowerCase().toCharArray();
			char[] s2 = scan.next().toLowerCase().toCharArray();
			int minAlignment = minAlignment(s1, s2);
			System.out.println(minAlignment);
		}
	}
	
	private static int minAlignment(char[] s1, char[] s2){
		int[][] m = new int[s1.length+1][s2.length+1];
		int gap = 2;
		int mismatch;
		
		for(int i = 1; i < m.length; i++){
			m[i][0] = m[i-1][0] + gap;
		}
		for(int j = 1; j < m[0].length; j++){
			m[0][j] = m[0][j-1] + gap;
		}
		
		for(int i = 1; i < m.length; i++){
			for(int j = 1; j < m[i].length; j++){
				/** find mismatch penalty */
				char m1 = s1[i-1];
				char m2 = s2[j-1];
				if(m1 == m2){
					mismatch = 0;
				}else if(vowels.contains(m1) && vowels.contains(m2) ||
						consonants.contains(m1) && consonants.contains(m2)){
					mismatch = 1;
				}else{
					mismatch = 3;
				}
				
				m[i][j] = min(mismatch + m[i-1][j-1],
							  gap + m[i-1][j],
							  gap + m[i][j-1]);
			}
		}
		return m[s1.length][s2.length];
	}
	
	private static int min(int i, int j, int k){
		if(i <= j && i <= k){
			return i;
		}else if(j <= i && j <= k){
			return j;
		}else{
			return k;
		}
	}
	
	private static void print(int[][] arr){
		for(int i = arr.length-1; i >= 0; i--){
			for(int j = 0; j < arr[i].length; j++){
				System.out.print(arr[i][j] + " ");
			}System.out.println();
		}
	}
}