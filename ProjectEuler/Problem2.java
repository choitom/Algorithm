public class Problem2{
	public static void main(String[] args){
		long sum = 2;
		int first = 1;
		int second = 2;
		int term = 3;
		while(term < 4000000){
			if(term % 2 == 0){
				sum += term;
			}
			first = second;
			second = term;
			term = first + second;
		}
		System.out.println(sum);
	}
}