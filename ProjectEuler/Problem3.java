public class Problem3{
	public static void main(String[] args){
		/**
			num = LP * k
			LP = num / k
		*/
		long num = 600851475143L;
		int k = 2;
		while(true){
			if(num%k == 0){
				if(isPrime(num/k)){
					System.out.println(num/k);
					break;
				}
			}
			k++;
		}
	}
	
	private static boolean isPrime(long n){
		for(int i = 2; i < (int)Math.pow(n,0.5); i++){
			if(n % i == 0){
				return false;
			}
		}
		return true;
	}
}