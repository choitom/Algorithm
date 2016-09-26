/**
	GIVEN
	|a| < 1000, |b| <= 1000 gives the range for a and b as follows
		-999 <= a <= 999
		-1000 <= b <= 1000
	all possible combinations of a and b is 10^6
	
	STRATEGY
	for each combination
		-> plug coefficients into the equation
		-> see if how many prime consecutive prime numbers it can yield
		-> update maximum number and coefficients.
*/

public class Problem27{
	public static void main(String[] args){
		int co_a = 0;
		int co_b = 0;
		int maxConsecutivePrimes = 0;
		
		for(int a = -999; a <= 999; a++){
			for(int b = -1000; b <= 1000; b++){
				int consecutivePrimes = findConsecutivePrimes(a,b);
				if(consecutivePrimes > maxConsecutivePrimes){
					maxConsecutivePrimes = consecutivePrimes;
					co_a = a;
					co_b = b;
				}
			}
		}
		
		int product = co_a * co_b;
		System.out.println(co_a + ", " + co_b + ", " + product + ", " + maxConsecutivePrimes);
	}
	
	public static int findConsecutivePrimes(int a, int b){
		int n = 0;
		int primes = 0;
		if(equation(n,a,b) < 2){
			return 0;
		}
		
		while(true){
			int value = equation(n,a,b);
			if(isPrime(value)){
				primes++;
			}else{
				break;
			}
			n++;
		}
		return primes;
	}
	
	public static int equation(int n, int a, int b){
		return (n*n + a*n + b);
	}
	
	public static boolean isPrime(int n){
		if(n<2){
			return false;
		}
		boolean isPrime = true;
		for(int i = 2; i <= (int)Math.sqrt(n); i++){
			if(n%i == 0){
				isPrime = false;
				break;
			}
		}
		return isPrime;
	}
}