import java.util.*;

public class Problem12{
	public static void main(String[] args){
		long triangle = 0L;
		int num = 1;
		while(true){
			triangle += num++;
			if(fiveHundredDivisors(triangle)){
				System.out.println(triangle);
				break;
			}
		}
	}
	
	private static boolean fiveHundredDivisors(long num){
		long n = num;
		int divisors = 1;
		int factor = 2;
		int count = 0;
		ArrayList<Integer> factorCounts = new ArrayList<Integer>();
		while(true){
			// divisible by factor
			if(n % factor == 0){
				count++;
				n = n/factor;
			}
			// not divisible by factor
			else{
				if(n == 1){
					factorCounts.add(count);
					break;
				}
				factor++;
				if(count > 0){
					factorCounts.add(count);
				}
				count = 0;
			}
		}
		for(int i = 0; i < factorCounts.size(); i++){
			divisors = divisors * (factorCounts.get(i) + 1);
		}
		System.out.println(num + ", " + divisors);
		if(divisors > 500){
			return true;
		}
		return false;
	}
}