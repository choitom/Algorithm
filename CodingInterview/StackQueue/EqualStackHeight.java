/**
	Author	: Tom Choi
	Date	: 09/04/2016
	
	There are three stacks of integers with different heights,
	where each height is the sum of the items in the stack.
	
	Find the maximum possible equal heights either by
	not removing or removing the top items from the stacks.
	
	Strategy
		1. find the stack with the lowest height
		2. balance the heights by popping the ones with greater heights
		3. repeat until either one of the stacks are empty or balanced
*/

import java.io.*;
import java.util.*;

public class EqualStackHeight{
	
	private Deque<Integer> stack1;
	private Deque<Integer> stack2;
	private Deque<Integer> stack3;
	
	private int height1;
	private int height2;
	private int height3;
	
	public EqualStackHeight(int[] h1, int[] h2, int[] h3){
		stack1 = new ArrayDeque<Integer>();
		stack2 = new ArrayDeque<Integer>();
		stack3 = new ArrayDeque<Integer>();
		
		height1 = 0;
		height2 = 0;
		height3 = 0;
		
		initStacks(h1, h2, h3);
	}
	
	public int maxEqualHeight(){
		while(!isEqualHeight() && !stack1.isEmpty() &&
		      !stack2.isEmpty() && !stack3.isEmpty()){
			int minHeight = findMinHeight();
			
			height1 = balanceHeight(minHeight, height1, stack1);
			height2 = balanceHeight(minHeight, height2, stack2);
			height3 = balanceHeight(minHeight, height3, stack3);
		}
		
		if(stack1.isEmpty() || stack2.isEmpty() || stack3.isEmpty()){
			return 0;
		}
		return height1;
	}
	
	/**
	* Balances the stack with the heights greater than the lowest height
	*/
	private int balanceHeight(int minHeight, int height, Deque<Integer> stack){
		if(minHeight < height){
			height -= stack.pop();
		}
		return height;
	}
	
	/**
	* Returns the minimum height among the stacks
	*/
	private int findMinHeight(){
		int min = height1;
		if(min > height2){
			min = height2;
		}
		if(min > height3){
			min = height3;
		}
		return min;
	}
	
	/**
	* Returns true if the heights of the stacks are equal to one another
	*/
	private boolean isEqualHeight(){
		return (height1 == height2 && height2 == height3) ? true : false;
	}
	
	private void initStacks(int[] h1, int[] h2, int[] h3){
		for(int i = h1.length-1; i >= 0; i--){
			height1 += h1[i];
			stack1.push(h1[i]);
		}
		for(int i = h2.length-1; i >= 0; i--){
			height2 += h2[i];
			stack2.push(h2[i]);
		}
		for(int i = h3.length-1; i >= 0; i--){
			height3 += h3[i];
			stack3.push(h3[i]);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("stackHeights.txt");
		Scanner scan = new Scanner(file);
		
		int s1 = scan.nextInt();
		int s2 = scan.nextInt();
		int s3 = scan.nextInt();
		int[] h1 = new int[s1];
		int[] h2 = new int[s2];
		int[] h3 = new int[s3];
		
		for(int i = 0; i < s1; i++){
			h1[i] = scan.nextInt();
		}
		for(int i = 0; i < s2; i++){
			h2[i] = scan.nextInt();
		}
		for(int i = 0; i < s3; i++){
			h3[i] = scan.nextInt();
		}
		
		EqualStackHeight esh = new EqualStackHeight(h1, h2, h3);
		int maxEqualHeight = esh.maxEqualHeight();
		System.out.println(maxEqualHeight);
		scan.close();
	}
}