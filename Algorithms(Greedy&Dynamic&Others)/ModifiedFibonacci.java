/**
	Author	: Tom Choi
	Date	: 08/28/2016
	
	Problem
		The modified fibonacci is as follows
			T_i = T_(i-1)^2 + T_(i-2)
		The input has three numbers separated by space as follows
			t1 t2 n
		where t1 and t2 are the first two terms of the sequence
		and n is the term to search.
		
	Strategy
		Starting from the first term, fill out
		the whole table upto the nth term. (O(n))
*/

import java.io.*;
import java.util.*;
import java.math.*;

public class ModifiedFibonacci {
    public static void main(String[] args) throws FileNotFoundException{
		File file = new File("modifiedSequence.txt");
        Scanner scan = new Scanner(file);
        int t1 = scan.nextInt();	// first term
        int t2 = scan.nextInt();	// second term
        int n = scan.nextInt();		// target
      
        BigInteger[] arr = new BigInteger[n];	// storage
        arr[0] = new BigInteger(String.valueOf(t1));
        arr[1] = new BigInteger(String.valueOf(t2));
        
		// fill out the seqence bottom up
        for(int i = 2; i < arr.length; i++){
            BigInteger mult = arr[i-1].multiply(arr[i-1]);
            arr[i] = arr[i-2].add(mult);
        }
		
		// print out the result
        System.out.println(arr[n-1]);
    }
}