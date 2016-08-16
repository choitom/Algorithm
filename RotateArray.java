/**
	Author	: Tom Choi
	Date	: 08/16/2016
	
	Problem
		-> rotate an array of n elements to the right by k steps
*/
public class RotateArray{
	public static int[] arr = {1,2,3,4,5,6,7};
	
	public static void main(String[] args){
		System.out.print("Before rotation: ");
		print();
		int rotate = 4;
		rotateArr(rotate);
		System.out.print("After rotation by " + rotate + ": ");
		print();
	}
	
	/**
	* Rotate the items by a given input
	* by using an intermediate array and mod operation
	*/
	public static void rotateArr(int rotate){
		int[] temp = new int[arr.length];
		int i;
		int index;
		for(i = 0; i < arr.length; i++){
			index = (i + rotate) % temp.length;
			temp[index] = arr[i];
		}
		arr = temp;
	}
	
	/**
	* Prints out items in the array
	*/
	public static void print(){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}System.out.println();
	}
}