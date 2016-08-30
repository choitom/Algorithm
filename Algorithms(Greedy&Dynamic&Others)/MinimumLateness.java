/**
	Author	: Tom Choi
	Date	: 08/26/2016
	
	Implementation of minimum lateness problem
		- each schedule can be scheduled at flexible time
		- it has its interval and deadline
		- latesness is defined as follows
			lateness = finished_time - deadline
			
	Pseudocode
		Sort by deadline in ascending order		O(nlogn)
		Calculate lateness						O(n)
*/

import java.util.*;
import java.io.*;

public class MinimumLateness{
	public static void main(String[] args) throws FileNotFoundException{
		File f = new File("deadline.txt");
		Scanner scan = new Scanner(f);
		
		int size = scan.nextInt();
		int[][] schedules = new int[size][];
		for(int i = 0; i < schedules.length; i++){
			int[] s = new int[2];
			s[1] = scan.nextInt(); // interval
			s[0] = scan.nextInt(); // deadline
			schedules[i] = s;
		}
		
		/** Sort by deadlines using Random Quick Sort */
		RquickSort(schedules, 0, schedules.length-1);
		
		int lateness = findLateness(schedules);
		System.out.println("The minimum lateness: " + lateness);
	}
	
	/***/
	private static int findLateness(int[][] schedules){
		int sum = 0;	// sum of lateness
		int F = 0;		// finish time
		int lateness;
		for(int i = 0; i < schedules.length; i++){
			F += schedules[i][1];
			lateness = F - schedules[i][0];
			if(lateness > 0){
				sum += lateness;
			}
		}
		return sum;
	}
	
	/** sort deadlines in ascending order */
	private static void RquickSort(int[][] schedules, int low, int high){
		if(low < high){
			int p = partition(schedules, low, high);
			RquickSort(schedules, low, p-1);
			RquickSort(schedules, p+1, high);
		}
	}
	
	/**
	* Move all the schedules whose deadlines are smaller than that of pivot
	* to the left side of the pivot. Move greater ones to the right side.
	*/
	private static int partition(int[][] schedules, int low, int high){
		/** find a random pivot */
		Random rand = new Random();
		int randIndex = rand.nextInt(high - low + 1) + low;	
		swap(schedules, high, randIndex);
		
		int[] pivot = schedules[high];
		int wall = low;
		
		for(int i = low; i < high; i++){
			int[] interval = schedules[i];
			if(interval[0] < pivot[0]){
				swap(schedules, i, wall);
				wall++;
			}
		}
		
		
		swap(schedules, wall, high);
		return wall;
	}
	
	private static void swap(int[][] schedules, int i, int j){
		int[] temp = schedules[i];
		schedules[i] = schedules[j];
		schedules[j] = temp;
	}
	
	private static void print(int[][] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print("[ ");
			for(int j = 0; j <arr[i].length; j++){
				System.out.print(arr[i][j] + " ");
			}System.out.print("] ");
		}System.out.println();
	}
}