import java.util.*;
import java.math.*;

/**
	Strategy
		1. Use HashSet for storing distinct terms -> O(1)
		2. Compute each term and store int to the set
		3. Return the size of the set
		-> runs in O(n^2), but takes up space
		
	Alternatively,
		convert a^b into inseparable format like..
		4^2 -> 2^(2*2) = 2^(4)
		and remove duplicates
		-> this will take O(n^3), but saves space
*/

public class Problem29{
	public static void main(String[] args){
		HashSet<String> set = new HashSet<String>();
		for(int a = 2; a <= 100; a++){
			for(int b = 2; b <= 100; b++){
				BigInteger term = findTerm(a,b);
				String newTerm = term.toString();
				set.add(newTerm);
			}
		}
		System.out.println(set.size());
	}
	
	public static BigInteger findTerm(int a, int b){
		BigInteger A = new BigInteger(String.valueOf(a));
		BigInteger ret = A.pow(b);
		return ret;
	}
}