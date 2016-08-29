/**
	Author	: Tom Choi
	Date	: 08/28/2016
	
	Problem
		Print out how many different ways you can make change
		from a given list of coins
		
		The first line of the input has two numbers N and M
		separated by empty space, where N is the change to make
		and M is the number of coins given
		
		The following line is the list of coins separated by empty spaces
		
		
	Idea
		1. starting from the first coin, find the number of ways to make the change
		2. then carry the result to the next coin and see if I could make the change
			with the previous coins and the current one
		
*/

import java.io.*;
import java.util.*;

public class CoinChange{
	public static void main(String[] args) throws FileNotFoundException{
		File f = new File("coins.txt");
		Scanner scan = new Scanner(f);
		int amount = scan.nextInt();
		int numCoins = scan.nextInt();
		int[] coins = new int[numCoins];
		int i = 0;
		while(scan.hasNextInt()){
			coins[i++] = scan.nextInt();
		}
		
		findChanges(coins, amount);
	}
	
	private static void findChanges(int[] coins, int amount){
		long[][] result = new long[coins.length+1][amount+1];
		
		// no coin -> cannot make any change
		for(int j = 0; j < amount+1; j++){
			result[0][j] = 0;
		}
		
		/*	change is 0 -> only 1 way to make the change
			i.e. do not give any coin
		*/
		for(int i = 0; i < result.length; i++){
			result[i][0] = 1;
		}
		
		for(int i = 1; i < result.length; i++){
			for(int j = 1; j < result[i].length; j++){
				/*	if the current amount is less than
					the current coin value, carry the result
					from the previous coins
				*/
				int currentCoinValue = coins[i-1];
				if(currentCoinValue > j){
					result[i][j] = result[i-1][j];
				}
				/*	otherwise, the number of ways to make the change
					is sum of previous result and number of ways to make
					the change with the current coin added
				*/
				else{
					result[i][j] = result[i-1][j] + result[i][j-currentCoinValue];
				}
			}
		}
		int row = result.length-1;
		int col = amount;
		System.out.println(result[row][col]);
	}
	
	// print out a list of integers
	private static void print(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}System.out.println();
	}
}