/**
	Author	: Tom Choi
	Date	: 08/18/2016
	
	Problem
	-> A matrix is Toepliz if each descending diagonal from left to right is constant
*/

public class Toepliz{
	public static void main(String[] args){
		int[][] matrix = {{6,7,8,9,2},
						  {4,6,7,8,9},
						  {1,4,6,7,8},
						  {0,1,4,6,7}};
		System.out.println(checkToepliz(matrix));
	}
	
	public static boolean checkToepliz(int[][] matrix){
		// for each row except for the last one
		for(int i = 0; i < matrix.length-1; i++){
			
			// for each number except for the last one
			for(int j = 0; j < matrix[i].length-1; j++){
				
				// compare with a descending diagonal number
				if(matrix[i][j] != matrix[i+1][j+1]){
					return false;
				}
			}
		}
		return true;
	}
}