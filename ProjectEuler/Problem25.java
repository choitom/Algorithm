/**
	Goal: The index of fib sequence that has the first 1000 digits
	base case -> F1 = 1, F2 = 1
*/

import java.math.BigInteger;

public class Problem25{
	public static void main(String[] args){
		// create base cases
		BigInteger first = BigInteger.ONE;
		BigInteger second = BigInteger.ONE;

		findSolution(first, second, 3);
	}

public static void findSolution(BigInteger firstTerm, BigInteger secondTerm, int n){
		BigInteger next = firstTerm.add(secondTerm);
		if(next.toString().length() >= 1000){
			System.out.println(n);
			return;
		} else{
			findSolution(secondTerm, next, n+1);
		}
	}
}