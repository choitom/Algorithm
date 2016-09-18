/**
	Author	: Tom Choi
	Date	: 09/18/2016
	
	Find the powerset of a set
*/

import java.util.*;

public class PowerSet{
	
	public static int[] set;
	public static ArrayList<ArrayList<Integer>> powerset;
	public static void main(String[] args){
		set = new int[]{1,2,3,4};
		powerset = new ArrayList<ArrayList<Integer>>();
		
		findPowerSet();
		for(int i = 0; i < powerset.size(); i++){
			System.out.println(powerset.get(i));
		}
	}
	
	/**
		It is all about adding the next number onto
		the pre-existing subset and add them into the powerset.
	*/
	private static void findPowerSet(){
		// add a null set
		powerset.add(new ArrayList<Integer>());
		
		// starting from the first number
		for(int i = 0; i < set.length; i++){
			ArrayList<ArrayList<Integer>> buffer = new ArrayList<ArrayList<Integer>>();
			for(int j = 0; j < powerset.size(); j++){
				ArrayList<Integer> temp = new ArrayList<Integer>(powerset.get(j));
				temp.add(set[i]);
				buffer.add(temp);
			}
			powerset.addAll(buffer);
		}
	}
}