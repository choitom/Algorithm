/**
	Author	: Tom Choi
	Date	: 09/18/2016
	
	A child is running up a staircase with n steps and can hop either
	1 step, 2 steps, or 3 steps at a time. Implement a method to count
	how many possible ways the child can run up the stairs.
*/
import java.util.*;

public class HopStaircase{
	
	public static int count = 0;
	
	public static void main(String[] args){
		int n = 15;
		findHop(n, new ArrayList<Integer>());
		System.out.println(count);
	}
	
	private static void findHop(int n, ArrayList<Integer> list){
		if(n == 0){
			System.out.println(list);
			count++;
		}else if(n < 0){
			return;
		}else{
			ArrayList<Integer> obj1 = new ArrayList<Integer>(list);
			ArrayList<Integer> obj2 = new ArrayList<Integer>(list);
			ArrayList<Integer> obj3 = new ArrayList<Integer>(list);
			obj1.add(1);
			obj2.add(2);
			obj3.add(3);
			findHop(n-1, obj1);
			findHop(n-2, obj2);
			findHop(n-3, obj3);
		}
	}
}