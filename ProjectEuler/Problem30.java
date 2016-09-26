/**
	Goal:	Find the sum of the numbers that can be written as
			the sum of fifth powers of their digits
	
	What is the range of the numbers?
	9^5 -> 59059
	
	suppose I have a number 9999
	then, obviously, 9999 < 9^5.
*/

public class Problem30{
	public static void main(String[] args){
		int sum = 0;
		for(int i = 2; i < 355000; i++){
			String str = String.valueOf(i);
			if(isGood(str)){
				sum+=i;
			}
		}
		System.out.println(sum);
	}
	
	public static boolean isGood(String str){
		int original = Integer.parseInt(str);
		int sum = 0;
		for(int i = 0; i < str.length(); i++){
			int digit = Integer.parseInt(String.valueOf(str.charAt(i)));
			int pow = (int)Math.pow(digit,5);
			sum += pow;
		}
		if(sum == original){
			return true;
		}
		return false;
	}
}