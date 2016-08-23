/**
	Author	: Tom Choi
	Date	: 08/16/2016
	
	Problem
		find all possible strings whose length is n
		with the following restrictions
		
		-> alphabets are 'a', 'b', and 'c'
		-> at most one 'b' can be used
		-> can only have upto two consecutive c's, 'cc'
*/

import java.util.Scanner;

public class FindStrings{
	public static int count = 0;
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the length of a string: ");
		int length = scan.nextInt();
		findStrings(length, "", 0, " ");
		System.out.println(count);
	}
	
	/**
	* Based on the patterns it has seen so far
	* it determines what characters could follow next
	*
	* @param	length		the length of the input string
	* @param	s			the buffer for appending each character
	* @param	numB		the number of b's appended to the buffer
	* @param	cPattern	the most recent two consecutive characters
	*/
	public static void findStrings(int length, String s, int numB, String cPattern){
		if(length == 0){
			count++;
		}else{
			int charIndex = cPattern.length()-1;
			if(charIndex < 0){
				charIndex = 0;
			}
			
			// no restrictions on b and c
			if(numB < 1 && !cPattern.equals("cc")){
				findStrings(length - 1, s + "b", numB + 1,
							String.valueOf(cPattern.charAt(charIndex)) + "b");
				findStrings(length - 1, s + "c", numB,
							String.valueOf(cPattern.charAt(charIndex)) + "c");
			}
			// has one b, but no restriction on the c pattern
			else if(numB >= 1 && !cPattern.equals("cc")){
				findStrings(length - 1, s + "c", numB,
							String.valueOf(cPattern.charAt(charIndex)) + "c");
			}
			// restriction on the c pattern, but b is not
			else if(numB < 1 && cPattern.equals("cc")){
				findStrings(length - 1, s + "b", numB + 1,
							String.valueOf(cPattern.charAt(charIndex)) + "b");
			}
			// always add an a
			findStrings(length - 1, s + "a", numB,
							String.valueOf(cPattern.charAt(charIndex)) + "a");
		}
	}
}