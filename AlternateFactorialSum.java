/**
	Author	: Tom Choi
	Date	: 08/17/2016
	
	Problem
		-> Given an integer N, write a code that calculates
		1! - 2! + 3! ... up to Nth term
		
	Strategy
		1. First write a code for just calculating factorial
			-> use tail recursion to minimize activation records
		2. Then worry about the sums and its sign
*/

public class AlternateFactorialSum{
	public static void main(String[] args){
		int n = 4;
		int factSum = altFactSum(n);
		System.out.println(factSum);
	}
	
	/**
	* Finds the alternate sum of factorials
	*
	* @param	n		nth term
	* @return	1! - 2! + 3! ...
	*/
	public static int altFactSum(int n){
		int sum = 0;
		for(int i = 1; i <= n; i++){
			if(isOdd(i)){
				sum += tailFactorial(i, 1);
			}else{
				sum -= tailFactorial(i, 1);
			}
		}
		return sum;
	}
	
	/**
	* Uses tail recursion to find the factorial of an input integer
	*
	* @param	n			an integer to find the factorial
	* @param	partial		partial multiplication of factorial
	* @return	n!
	*/
	public static int tailFactorial(int n, int partial){
		if(n <= 1){
			return partial;
		}else{
			return tailFactorial(n-1, partial * n);
		}
	}
	
	/**
	* See if an integer is even or odd
	*
	* @param	n		number
	* @return	true if odd; otherwise, false
	*/
	public static boolean isOdd(int n){
		return (n % 2 == 1);
	}
}