/**
	Author	: Tom Choi
	Date	: 08/28/2016
	
	Problem from HackerRank.com
		- Initially there is an array containig N integers.
		- In each move, partition the array into two non-empty parts
		  such that the sum of the elements in the left and right partitions
		  are equal to each other.
		- If you can make such move, you earn 1 point and discard
		  either the left or the right half
		- Find the maximum possible points you can earn
	
	Naive approach: Recursion
		1. Find the left and right partition of the array
		2. Increment the score and return the maximum score
		   from either left or right partition.
*/

import java.io.*;
import java.util.*;

public class PartitionGame {
    
    public static void main(String[] args) throws FileNotFoundException{
		File file = new File("partitionGameInput.txt");
        Scanner scan = new Scanner(file);
        int cases = scan.nextInt();
        for(int i = 0; i < cases; i++){
            int size = scan.nextInt();
            int[] arr = new int[size];
            for(int j = 0; j < arr.length; j++){
                arr[j] = scan.nextInt();
            }
            int maxPoint = findMaxPoints(arr, findSum(arr), 0);
            System.out.println(maxPoint);
        }
    }
    
	/**
	* Finds the maximum possible points by recursively using its
	* left and right partitions as inputs to this method
	*
	* @param	arr		array of integers
	* @param	sum		the sum of integers in arr
	* @param	point	the points earned so far
	* @return	the maximum possible points
	*/
    private static int findMaxPoints(int[] arr, long sum, int point){
        /**
        *   Base cases
		*	1. sum is 0
        *   2. array has only one item left
        *   3. array has more than one items and the sum cannot be divided into 2
		*	   or yields fractions when divided by 2
        */
		if(sum == 0){
			return arr.length-1;
		}
		
        int partition = partitionIndex(arr, sum);
        if(partition == -1 || arr.length == 1){
            return point;
        }else{
            int[] left = leftHalf(arr, partition);
            int[] right = rightHalf(arr, partition);
			return max(findMaxPoints(left, findSum(left), point + 1), findMaxPoints(right, findSum(right), point + 1));
        }
    }
    
	/**
	* Compare two integers and return the greater one
	* @param	i		first integer
	* @param	j		second integer
	* @return	greater integer
	*/
    private static int max(int i, int j){
        return (i > j) ? i : j;
    }
    
	/**
	* Find the left partition of an array
	*
	* @param	partition	the index at which divide the arr
	* @param	the partial array to the left of the partition
	*/
    private static int[] leftHalf(int[] arr, int partition){
        int[] l = new int[partition];
        for(int i = 0; i < l.length; i++){
            l[i] = arr[i];
        }
        return l;
    }
    
	/**
	* Find the right partition of an array
	*
	* @param	partition	the index at which divide the arr
	* @param	the partial array to the right of the partition
	*/
    private static int[] rightHalf(int[] arr, int partition){
        int[] r = new int[arr.length - partition];
        for(int i = 0; i < r.length; i++){
            r[i] = arr[i + partition];
        }
        return r;
    }
    
    /**
    * Find the index of the partition
    *
    * @param    arr     an array of integers
    * @param    sum     sum of the integers in the list
    * @return   index of the partition
    */
    private static int partitionIndex(int[] arr, long sum){
        if(arr.length == 1){
            return -1;
        }
        if(sum == 0){
            return 1;
        }
        double target = (double)(sum)/2;
        long current = 0;
        int index = 0;
        
        while(current != target && index < arr.length){
            current += arr[index++];
        }
        if(index == arr.length){
            return -1;
        }
        return index;
    }
    
    /**
    * Find the sum of integers in the array
    *
    * @param    arr     an array of integers
    * @return   the sum of integers
    */
    private static long findSum(int[] arr){
        long sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        return sum;
    }
	
	/**
	* Print an array of integers
	*
	* @param	arr		array to print out
	*/
	private static void print(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}System.out.println();
	}
}