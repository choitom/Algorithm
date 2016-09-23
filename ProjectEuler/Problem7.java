// Find 10001st prime number
public class Problem7{
	public static void main(String[] args){
		long prime = 0;
		int count = 1;
		int n = 2;
		while(true){
			n++;
			if(isPrime(n)){
				count++;
			}
			if(count == 10001){
				prime = n;
				break;
			}
		}System.out.println(prime);
	}
	
	private static boolean isPrime(long n){
		for(int i = 2; i <= (int)Math.pow(n,0.5); i++){
			if(n % i == 0){
				return false;
			}
		}
		return true;
	}
}