/**
	Author	: Tom Choi
	Date	: 09/03/2016
	
	There are three types of queries using the stacks
		1 x -> push the element to the stack
		2	-> pop the top element
		3	-> print out the maximum value in the stack
	For each type 3 query, print out the maximum value in O(1) time
	
	Strategy
		Initialize a variable for storing the maximum value.
		Each time pusing an integer, compare and find the maximum
		value seen so far and store it together.
*/

import java.io.*;
import java.util.*;

public class MaximumElement{
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("queries.txt");
		Scanner scan = new Scanner(file);
		
		Deque<int[]> stack = new ArrayDeque<int[]>();
		
		int[] pair;
		int max = Integer.MIN_VALUE;
		int queries = scan.nextInt();
		int qType;
		int item;
		for(int i = 0; i < queries; i++){
			qType = scan.nextInt();
			
			// push
			if(qType == 1){
				pair = new int[2];
				item = scan.nextInt();
				pair[0] = item;
				max = max(item, max);
				pair[1] = max;
				stack.push(pair);
			}
			// pop
			else if(qType == 2){
				if(!stack.isEmpty()){
					stack.pop();
				}
				if(stack.isEmpty()){
					max = Integer.MIN_VALUE;
				}else{
					max = stack.peek()[1];
				}
			}
			// print max
			else{
				if(!stack.isEmpty()){
					System.out.println(stack.peek()[1]);
				}else{
					System.out.println("The stack is empty");
				}
			}
		}
	}
	private static int max(int i, int j){
		return (i >= j) ? i : j;
	}
}