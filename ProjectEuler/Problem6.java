public class Problem6{
	public static void main(String[] args){
		long squareSum = 0;
		long sumSquare = 0;
		for(int i = 1; i <= 100; i++){
			squareSum += i*i;
			sumSquare += i;
		}
		sumSquare = sumSquare*sumSquare;
		System.out.println(sumSquare - squareSum);
	}
}