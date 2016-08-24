/**
	Author	: Tom Choi
	Date	: 08/24/2016
	
	Problem
		An index in an array of integers is equilibrium if
		-> sum of left-hand side = sum of right-hand side
		Find all equilibriums in O(n) time
*/

import java.util.ArrayList;

public class EquilibriumIndex{
	public static void main(String[] args){
		int[] arr = {-1, 3, -4, 5, 1, -6, 2, 1};
		ArrayList<Integer> equilibrium = findEq(arr, arr.length);
		for(int i = 0; i <equilibrium.size(); i++){
			System.out.println(equilibrium.get(i));
		}
	}
	
	public static ArrayList<Integer> findEq(int[] arr, int n){
		ArrayList<Integer> eq = new ArrayList<Integer>();
		
		int lower = 0;
		int upper = 0;
		for(int i = 0; i < n; i++){
			upper += arr[i];
		}
		
		for(int i = 0; i < n; i++){
			upper -= arr[i];
			if(upper == lower){
				eq.add(i);
			}
			lower += arr[i];
		}
		return eq;
	}
}