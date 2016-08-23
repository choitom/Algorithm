/**
	Author	: Tom Choi
	Date	: 08/17/2016
	
	Problem
	-> Given a function F6() that returns 1,2,3,4,5,6 randomly with equal prob
	-> Implement a new function F12() that reutns 1 ~ 12 randomly with equal prob using F6()

	Strategy
	-> Get a random number, r1, from F6()
	-> Get another number, r2, from F6()
		If r2 is even, return r1 + r2
		Otherwise, return r1
*/

import java.util.Random;

public class EqualProb{
	public static Random rand = new Random();
	
	public static void main(String[] args){
		for(int i = 0; i < 100; i++){
			System.out.print(F12() + " ");
		}
	}
	
	/**
	* Randomly returns 1 ~ 6 with equal probability
	*/
	public static int F6(){
		return rand.nextInt(6) + 1;
	}
	
	/**
	* Randomly returns 1 ~ 12 with equal probability
	*/
	public static int F12(){
		int r1 = F6();
		int r2 = F6();
		if(r2 % 2 == 0){
			return r1 + r2;
		}
		return r1;
	}
}