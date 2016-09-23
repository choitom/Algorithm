import java.math.BigInteger;

public class Problem15{
	public static void main(String[] args){
		BigInteger a = factorial(40, BigInteger.ONE);
		BigInteger b = factorial(20, BigInteger.ONE);
		BigInteger c = b.multiply(b);
		System.out.println(a.divide(c));
	}
	
	public static BigInteger factorial(int n, BigInteger partial){
		if(n == 1 || n == 0){
			return partial;
		}else{
			return factorial(n-1, partial.multiply(new BigInteger(String.valueOf(n))));
		}
	}
}