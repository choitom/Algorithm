/*
	Author	: Tom Choi
	Date	: 08/26/2016
	
	PROBLEM
		Given an input with a sequence of 0's and 1's
		find the minimum number of steps to from the start
		to the end with the following rules
			-> can only jump from 0 to 0
			-> it is prohibited landing onto any 1
			-> it is possible to skip a number (long jump)
			
			 0 1 2 3 4 5
		e.g. 0 1 0 0 1 0  takes the min jumps from (0 -> 2 -> 3 -> 5)
		
	Strategy
		Use dynamic programming to keep track of minimum steps
		for each index. It refers back to two indices in the array
		and find the minimum value and increment it by 1
		
		e.g.	0 1 0 0 1 0
				0 M 1 2 M 3 , where M is the maximum integer value
			
*/

import java.util.*;
import java.io.*;

public class FindMinSteps{
	
	/** Read input data and initialize the sequence of numbers */
	public static void main(String[] args) throws FileNotFoundException{
		File f = new File("samplePath.txt");
		Scanner in = new Scanner(f);
		int size = in.nextInt();
		int[] arr = new int[size];
		for(int i = 0; i < size; i++){
			arr[i] = in.nextInt();
		}
		
		int minSteps = findMinSteps(arr);
		System.out.println("The minimum steps in the following sequence");
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}System.out.println("\nis: " + minSteps);
	}
	
	/**
	* Find the minimum steps it takes from the start to the end
	*
	* @param	arr		the sequence of numbers of 0's and 1's
	* @return	minimum steps
	*/
	private static int findMinSteps(int[] arr){
		int MAX = Integer.MAX_VALUE;
		int[] steps = new int[arr.length];
		steps[0] = 0;
		if(steps[1] == 1){
			steps[1] = MAX;
		}else{
			steps[1] = 1;
		}
		
		for(int i = 2; i <= arr.length-1; i++){
			if(arr[i] == 1){
				steps[i] = MAX;
			}else{
				steps[i] = min(steps[i-1], steps[i-2]) + 1;
			}
		}
		return steps[steps.length-1];
	}
	
	/**
	* Compare two numbers and return the smaller one
	*/
	private static int min(int i, int j){
		return (i < j) ? i : j;
	}
}