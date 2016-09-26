import java.util.*;

public class Problem23{
	public static void main(String[] args){
		int LIMIT = 28123;
		
		ArrayList<Integer> abundant = new ArrayList<Integer>();
		abundant.add(12);
		
		isAbundant(12);
		// O(n^(3/2))
		for(int i = 13; i <= LIMIT; i++){
			if(isAbundant(i)){
				abundant.add(i);
			}
		}
		
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i <= 28123; i++){
			set.add(i);
		}
		
		for(int i = 0; i < abundant.size(); i++){
			for(int j = i; j < abundant.size(); j++){
				int k = abundant.get(i) + abundant.get(j);
				if(set.contains(k)){
					set.remove(k);
				}
			}
		}
		
		int sum = 0;
		for(int k : set){
			sum += k;
		}
		System.out.println(sum);
	}
	
	private static boolean isAbundant(int n){
		return (sumDivisors(n) > n) ? true : false;
	}
	
	private static int sumDivisors(int n){
		int sum = 0;
		for(int i = 1; i <= (int)Math.sqrt(n); i++){
			if(n%i == 0){
				sum += i;
				if(i != n/i && n/i != n){
					sum += (n/i);
				}
			}
		}
		return sum;
	}
}