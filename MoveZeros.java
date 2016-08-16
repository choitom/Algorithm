/**
	Author	: Tom Choi
	Date	: 08/16/2016
	
	Problem
		-> move all the zeros to the end of the array while keeping
		the sequence of non-zero integers
*/

public class MoveZeros{
	public static void main(String[] args){
		int[] arr = new int[]{2,4,0,56,0,12,66,43,0,0,9,73};
		print(arr);
		arr = moveZeros(arr);
		print(arr);
	}
	
	/**
	* Shift non-zero digits to the left as many as
	* the nubmer of zeros counted upto that point
	* Then, from the last index of the list, fill out 0's
	*
	* @param	arr		the array to sort 0's
	* @return	arr sorted
	*/
	public static int[] moveZeros(int[] arr){
		int zeroCount = 0;	
		for(int i = 0; i < arr.length; i++){
			// zero -> increment the count
			if(arr[i] == 0){
				zeroCount++;
			}
			// nonezero -> shift the nubmer to the left by zeroCount
			else{
				arr[i-zeroCount] = arr[i];
			}
		}
		
		for(int i = 0; i < zeroCount; i++){
			arr[arr.length-1-i] = 0;
		}
		return arr;
	}
	
	/**
	* Prints out an array
	*
	* @param	arr		an array to print out
	*/
	public static void printArr(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}