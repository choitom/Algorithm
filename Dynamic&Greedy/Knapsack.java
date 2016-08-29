/**
	Author	: Tom Choi
	Date	: 08/29/2016
	
	Implementation of Knapsack Problem
	(Algorithm & Design by Kleinberg and Tardos)
	
	Given
		1. Maximum capacity(W) of the knapsack
		2. Number of items(N) with its weight such that 0 <= w <= W
	Goal
		1. Find the maximum possible weight that I can fill the knapsack
	Strategy
		Let OPT be the optimal solution to the problem
		For nth item, there are two possibilities to consider
			a. OPT includes n
			b. OPt doesn't include n
		
		If OPT includes the nth item,
			- remaining weight  = W - w_n
			- remaining items   = N - 1
			- cumulative weight = W' + w_n
		
		If OPT doesn't include the item,
			- remaining weight  = W
			- remaining items   = N - 1
			- cumulative weight = W'
			
		And I want the maximum value among those two possibilities
		=>	OPT(n, w_n) = max(OPT(n-1, W), OPT(n-1, W-w_n) + w_n)
			but, if w_n > W, OPT(n, w_n) = OPT(n-1, W)
*/

import java.io.*;
import java.util.*;

public class Knapsack{
	private int[] items;
	private int max;
	
	public Knapsack(int maxWeight, int[] items){
		this.max = maxWeight;
		this.items = items;
	}
	
	public int getMaxWeight(){
		int[][] sack = new int[items.length+1][max+1];
		
		for(int i = 1; i < sack.length; i++){
			int itemSize = items[i-1];
			for(int j = 1; j < sack[i].length; j++){
				int weight = j;
				if(itemSize > weight){
					sack[i][j] = sack[i-1][j];
				}else{
					sack[i][j] = max(sack[i-1][j], itemSize + sack[i-1][j-itemSize]);
				}
			}
		}
		print(sack);
		return sack[items.length][max];
	}
	
	/**
	* Compare two integers and return the greater one
	*/
	private int max(int i, int j){
		return (i >= j) ? i : j;
	}
	
	/** Print out 1-D array */
	private void print(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}System.out.println();
	}
	
	/** Print out 2-D array */
	private void print(int[][] arr){
		for(int i = arr.length-1; i >= 0; i--){
			for(int j = 0; j < arr[i].length; j++){
				System.out.print(arr[i][j] + " ");
			}System.out.println();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("knapsackItems.txt");
		Scanner scan = new Scanner(file);
		
		int maxWeight = scan.nextInt();
		int numItems = scan.nextInt();
		int[] items = new int[numItems];
		
		for(int i = 0; i < items.length; i++){
			items[i] = scan.nextInt();
		}
		
		Knapsack knapsack = new Knapsack(maxWeight, items);
		int max = knapsack.getMaxWeight();
		System.out.println(max);
	}
}