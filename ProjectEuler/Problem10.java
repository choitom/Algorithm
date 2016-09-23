public class Problem10{
	public static void main(String[] args){
		long sum = 2L;
		int n = 2;
		while(n < 2000000){
			n++;
			if(isPrime(n)){
				sum += n;
			}
		}
		System.out.println(sum);
	}
	
	private static boolean isPrime(int n){
		for(int i = 2; i <= (int)Math.sqrt(n); i++){
			if(n % i == 0){
				return false;
			}
		}
		return true;
	}
}