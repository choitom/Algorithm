/**
	Author	: Tom Choi
	Date	: 08/26/2016
	
	Problem
		Given a sorted array in ascending order,
		reverse the sorted order without using any extra space
*/
public class ReverseSortOrder{
	public static void main(String[] args){
		int[] arr = new int[31];
		for(int i = 0; i < arr.length; i++){
			arr[i] = i;
		}
		
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}System.out.println();
		
		for(int i = 0; i < arr.length/2; i++){
			int temp = arr[i];
			arr[i] = arr[arr.length-1-i];
			arr[arr.length-1-i] = temp;
		}
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}System.out.println();
	}
}