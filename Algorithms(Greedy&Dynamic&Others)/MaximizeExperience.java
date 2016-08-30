/**
	Author	: Tom Choi
	Date	: 08/30/2016
	
	Problem
		You have a pet and there are N monsters in a forest
		and each monster has health, H
		The initial strength of the pet is 1 and experience is 0
		The pet can do the following
			1. Eat a monster and gain 1 strength
			2. Battle and gain H * strength amount of experience
		You can kill monsters in any order
		
	Strategy
		1. Sort monster by H in ascending order because the greater
		   the health, the more experience you can gain (greedy)
		
		2. Starting from the first monster, if you don't eat anything,
		   you gain (1)*(H1+H3+...+Hn) experience.
		
		3. On the other hand, if you eat and battle all the other monsters,
		   you gain (1+1)*(H2+H3+...+Hn)
		   
		4. Likewise, if you eat the first two monsters
		   and battle the rest of them, you gain (1+2)*(H3+H4+...+Hn)
		
		5. You can eat upto (n-1)th monsters
		
		6. So, the optimal solution is
		   -> OPT(i) = max (1+k)(H_k+1 + H_k+2 + ...), where 
		
*/

import java.io.*;
import java.util.*;
import java.math.*;

public class MaximizeExperience{
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("monsters.txt");
		Scanner scan = new Scanner(file);
		int cases = scan.nextInt();
		for(int i = 0; i < cases; i++){
			int monsterNum = scan.nextInt();
			int[] monsters = new int[monsterNum];
			int index = 0;
			for(int j = 0; j < monsterNum; j++){
				monsters[index++] = scan.nextInt();
			}
			BigInteger maxExp = findMaxExp(monsters);
			System.out.println(maxExp);
		}
	}
	
	private static BigInteger findMaxExp(int[] monsters){
		// sort
		monsters = sort(monsters);
		
		// finds the total sum
		BigInteger sum = BigInteger.ZERO;
		BigInteger v;
		for(int i = 0; i < monsters.length; i++){
			v = new BigInteger(String.valueOf(monsters[i]));
			sum = sum.add(v);	
		}
		
		BigInteger max = new BigInteger(sum.toString());
		BigInteger one = BigInteger.ONE;
		BigInteger strength;
		BigInteger exp;
		
		/**
		* Eat each monster, find exp it can get with the strength
		* Then, compare with the previously found exp
		* Carry the greater exp
		*/
		for(int i = 0; i < monsters.length-1; i++){
			strength = one.add(new BigInteger(String.valueOf(i+1)));
			sum = sum.subtract(new BigInteger(String.valueOf(monsters[i])));
			exp = strength.multiply(sum);	
			if(exp.compareTo(max) > 0){
				max = exp;
			}
		}return max;
	}
	
	/** Sort an array using the count sort */
	private static int[] sort(int[] arr){
		int[] output = new int[arr.length];
		int max = max(arr);
		int[] count = new int[max+1];
		for(int i = 0; i < arr.length; i++){
			count[arr[i]]++;
		}
		
		int index = 0;
		for(int i = 0; i < count.length; i++){
			int freq = count[i];
			while(freq > 0){
				output[index++] = i;
				freq--;
			}
		}
		return output;
	}
	
	/** returns the maximum number in the array */
	private static int max(int[] arr){
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++){
			if(max < arr[i]){
				max = arr[i];
			}
		}return max;
	}
}