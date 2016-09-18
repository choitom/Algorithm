/**
	Author	: Tom Choi
	Date	: 09/18/2016
	
	Write a recurive function to multiply two positive integers without
	using * operator. You can use addition, subtraction, and bit shifting,
	but you should minimize the number of those operations.
	
	Strategy
		1. Recursively shift the bits to left
		
*/

public class RecursiveMultiply{
	public static void main(String[] args){
		int n = 10;
		int multiplier = 17;
		
		int result = multiply(n, n, multiplier);
		System.out.println(result);
	}
	
	private static int multiply(int n, int partial, int mult){
		if(mult == 0){
			return partial;
		}else if(mult == 1){
			return partial + n;
		}else{
			mult = mult/2;
			partial = partial << 1;
			return multiply(n, partial, mult);
		}
	}
}