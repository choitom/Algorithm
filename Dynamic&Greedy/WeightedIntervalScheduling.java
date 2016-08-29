/**
	Author	: Tom Choi
	Date	: 08/29/2016
	
	Implementation of Weight Interval Scheduling
	(Algorithm & Design by Kleinberg and Tardos)
	
	Strategy
		Optimal weight at a certain interval can be obtained
		either with or without the schedule.
		1) With		: Optimal solution consists of the weight of the schedule
					  as well as non-conflicting previous schedules
		2) Without	: Optimal solution solely consists of previous schedules
		
	Pseudocode
		Sort schedules by finish time
		For each schedule
			find the leftmost non-conflicting schedule
		Initialize a 1-D array OPT
		OPT[0] = 0
		For i = 1 to n
			OPT[i] = max(OPT[i-1], OPT[p(i)] + v_i)
		Return OPT[n]
*/

import java.io.*;
import java.util.*;

public class WeightedIntervalScheduling{
	
	/** Interval class with start, end, weight values */
	private static class Interval{
		private int start;
		private int end;
		private int weight;
		private Interval(int s, int e, int w){
			start = s;
			end = e;
			weight = w;
		}
	}
	
	/** Instance variables */
	private Interval[] intervals;
	private Random rand;
	private int[] p;
	private final int MIN = Integer.MIN_VALUE;
	
	/** Constructor */
	public WeightedIntervalScheduling(Interval[] intervals){
		this.intervals = intervals;
		this.rand = new Random();
		this.p = new int[intervals.length];
		for(int i = 0; i < p.length; i++){
			p[i] = MIN;
		}
	}
	
	/**
	* Find the maximum possible weight of schedules
	*/
	public int maxWeight(){
		quickSort(0, intervals.length-1);
		findLeftMost();
		
		int[] OPT = new int[intervals.length + 1];
		OPT[0] = 0;
		for(int i = 1; i < OPT.length; i++){
			OPT[i] = max(OPT[i-1], OPT[p[i-1]] + intervals[i-1].weight);
		}
		return OPT[OPT.length-1];
	}
	
	/**
	* Compare two integers and return the greater one
	*/
	private int max(int i, int j){
		return (i >= j) ? i : j;
	}
	
	/**
	* For each schedule find the leftmost compatible schedules
	*/
	private void findLeftMost(){
		p[0] = 0;
		for(int i = p.length-1; i > 0; i--){
			Interval inter = intervals[i];
			for(int j = i-1; j >= 0; j--){
				if(compatible(inter, intervals[j])){
					p[i] = j+1;
					break;
				}
			}
			if(p[i] == MIN){
				p[i] = 0;
			}
		}
		//for(int i = 0; i < p.length; i++){
		//	System.out.println(p[i]);
		//}
	}
	
	/**
	* See if two schedules are compatible or not
	*/
	private boolean compatible(Interval latter, Interval former){
		return (latter.start >= former.end) ? true : false;
	}
	
	/**
	* Sort the array of schedules using the random quick sort
	*/
	private void quickSort(int low, int high){
		if(low < high){
			int p = partition(low, high);
			quickSort(low, p-1);
			quickSort(p+1, high);
		}
	}
	
	private int partition(int low, int high){
		// Randomly select a pivot
		int index = rand.nextInt(high - low + 1) + low;
		swap(index, high);
		Interval pivot = intervals[high];
		
		int wall = low;
		Interval inter;
		for(int i = low; i <= high-1; i++){
			inter = intervals[i];
			if(inter.end < pivot.end){
				swap(wall, i);
				wall++;
			}
		}
		swap(wall, high);
		return wall;
	}
	
	/**
	* Swap two schedules
	*/
	private void swap(int i, int j){
		Interval temp = intervals[i];
		intervals[i] = intervals[j];
		intervals[j] = temp;
	}
	
	private void print(){
		for(int i = 0; i < intervals.length; i++){
			System.out.print(intervals[i].end + " ");
		}System.out.println();
	}
	
	/** Test Code */
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("weightedSchedul.txt");
		Scanner scan = new Scanner(file);
		int schedules = scan.nextInt();
		
		Interval[] it = new Interval[schedules];
		int index = 0;
		
		for(int i = 0; i < schedules; i++){
			int start = scan.nextInt();
			int end = scan.nextInt();
			int weight = scan.nextInt();
			Interval inter = new Interval(start, end, weight);
			it[index++] = inter;
		}
		
		WeightedIntervalScheduling wis = new WeightedIntervalScheduling(it);
		int maxWeight = wis.maxWeight();
		System.out.println(maxWeight);
	}
}