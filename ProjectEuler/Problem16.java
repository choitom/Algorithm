import java.math.BigInteger;

public class Problem16{
	public static void main(String[] args){
		BigInteger ans = BigInteger.ONE;
		BigInteger two = new BigInteger("2");
		for(int i = 0; i < 1000; i++){
			ans = ans.multiply(two);
		}
		
		String result = ans.toString();
		long sum = 0L;
		for(int i = 0; i < result.length(); i++){
			sum += Integer.parseInt(String.valueOf(result.charAt(i)));
		}
		System.out.println(sum);
	}
}