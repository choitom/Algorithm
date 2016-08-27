/**
	Author	: Tom Choi
	Date	: 08/26/2016
	
	Implemenetation of interval scheduling problem
	
	Pseudocode
		R is the set of all requests, and A is empty
		While R is not empty
			Choose a request in R that has the smallest finishing time
			Add i to A
			Delete all requests from R incompatible with i
		Return A
*/

import java.util.*;
import java.io.*;

public class IntervalScheduling{
	
	private static HashSet<int[]> R;
	private static ArrayList<int[]> A;
	
	public static void main(String[] args) throws FileNotFoundException{
		R = new HashSet<int[]>();
		A = new ArrayList<int[]>();
		
		File f = new File("schedule.txt");
		Scanner scan = new Scanner(f);
		int[] schedule;
		
		while(scan.hasNextInt()){
			int start = scan.nextInt();
			int fin = scan.nextInt();
			schedule = new int[2];
			schedule[0] = start;
			schedule[1] = fin;
			R.add(schedule);
		}
		
		findMaxSchedules();
		for(int i = 0; i < A.size(); i++){
			schedule = A.get(i);
			System.out.print("[" +  schedule[0] + ", " + schedule[1] + "] ");
		}System.out.println();
	}
	
	private static void findMaxSchedules(){
		while(!R.isEmpty()){
			int[] minFinish = findMinFinish();
			R.remove(minFinish);
			A.add(minFinish);
			deleteIncompatible(minFinish);
		}
	}
	
	/**
	* Find the schedule with the minimum finish time
	*/
	private static int[] findMinFinish(){
		int[] min = null;
		int minTime = Integer.MAX_VALUE;
		for(int[] interval : R){
			if(interval[1] < minTime){
				min = interval;
				minTime = interval[1];
			}
		}
		return min;
	}
	
	/**
	* Delete schedules that are incompatible with a given schedule
	*/
	private static void deleteIncompatible(int[] schedule){
		int s = schedule[0];
		int f = schedule[1];
		ArrayList<int[]> toRemove = new ArrayList<int[]>();
		for(int[] interval : R){
			int S = interval[0];
			int F = interval[1];
			if( (S < s && F > f) ||
				(S == s && F == f) ||
				(F > s && F <= f) ||
				(S >= s && S < f)){
				toRemove.add(interval);
			}
		}
		for(int i = 0; i < toRemove.size(); i++){
			R.remove(toRemove.get(i));
		}
	}
}