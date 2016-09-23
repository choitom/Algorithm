/**
	Author	: Tom Choi
	Date	: 09/23/2016
	
	d(n) be defined as the sum of proper divisors of n
	if d(a) = b and d(b) = a, where a != b, then a and b are an amicable pair
	and each of a and b are called amicable numbers.
	
	Evaluate the sum of all the amicable numbers under 10000.
*/

import java.util.*;

public class Problem21{
	public static void main(String[] args){
		HashSet<Integer> set = new HashSet<Integer>();
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i = 2; i < 10000; i++){
			int divSum = sumDivisor(i);
			map.put(i, divSum);
		}
		
		for(int key : map.keySet()){
			int divSum = map.get(key);
			if(map.containsKey(divSum) && divSum != key){
				if(map.get(divSum) == key){
					set.add(key);
					set.add(divSum);
				}
			}
		}
		int sum = 0;
		for(int key : set){
			sum += key;
		}
		System.out.println(sum);
	}
	
	private static int sumDivisor(int n){
		int sum = 0;
		for(int i = 1; i <= n/2; i++){
			if(n % i == 0){
				sum += i;
			}
		}
		return sum;
	}
}