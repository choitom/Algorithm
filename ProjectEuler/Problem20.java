/**
	Author	: Tom Choi
	Date	: 09/23/2016
	
	Find the sum of the digits in the number 100!
	
	1. Use BigInteger for storing numbers
	2. Use tail recursion to compute 100!
	3. Add each digit
*/

import java.math.BigInteger;

public class Problem20{
	public static void main(String[] args){
		BigInteger hundred_factorial = factorial(100, BigInteger.ONE);
		char[] c = hundred_factorial.toString().toCharArray();
		int sum = 0;
		for(int i = 0; i < c.length; i++){
			sum += Integer.parseInt(String.valueOf(c[i]));
		}
		System.out.println(sum);
	}
	
	private static BigInteger factorial(int n, BigInteger partial){
		if(n == 1 || n == 0){
			return partial;
		}else{
			return factorial(n-1, partial.multiply(new BigInteger(String.valueOf(n))));
		}
	}
}