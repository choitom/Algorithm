/**
	Author	: Tom Choi
	Date:	: 08/23/2016
	
	Problem	: Find the maximum path in 2-D array
		Specifications
			Input
			1. size of the array
			2. string to parse to build a 2-D array
			   in the following format
			   "(1,7,5,2),(5,12,3,6),(100,9,23,16),(16,4,5,9)"
			   
	Strategy
		-> Use dynamic programming to fill out the whole table
		with the maximum possible values for each path
*/

public class MaximumPath{
	
	public static int[][] graph;
	public static int[][] values;
	
	public static void main(String[] args){
		String matrix =  "(1,7,5,2),(5,12,3,6),(100,9,23,16),(16,4,5,9)";
		int size = 4;
		
		transform(size, matrix);
		findMaxPath();
	}
	
	/**
	* Find the path with the maximum value using Dynamic Programming
	*/
	private static void findMaxPath(){
		values[0][0] = graph[0][0];
		
		
		/** Initialize base cases */
		for(int col = 1; col < values.length; col++){
			values[0][col] = values[0][col-1] + graph[0][col];
		}
		for(int row = 1; row < values.length; row++){
			values[row][0] = values[row-1][0] + graph[row][0];
		}
		
		for(int row = 1; row < values.length; row++){
			for(int col = 1; col < values.length; col++){
				values[row][col] = max(values[row-1][col-1] + graph[row][col],
									   values[row-1][col] + graph[row][col],
									   values[row][col-1] + graph[row][col]);
			}
		}
	}
	
	private static int max(int x, int y, int z){
		if(x >= y && x >= z){
			return x;
		}else if(y >= x && y >= z){
			return y;
		}else{
			return z;
		}
	}
	
	private static void print(int[][] m){
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[i].length; j++){
				System.out.print("[" + m[i][j] + "] ");
			}System.out.println();
		}
	}
	
	/**
	* Transforms the input string of matrix into the actual 2-D matrix
	*
	* @param	size		the dimension of the matrix
	* @param	matrix		the string represenation of the matrix
	*/
	private static void transform(int size, String matrix){
		graph = new int[size][size];
		values = new int[size][size];
		String str = "";
		String[] numStrArr;
		int[] numArr;
		
		/** get rid of open and close parentheses */
		for(int i = 0; i < matrix.length(); i++){
			if(matrix.charAt(i) != '(' && matrix.charAt(i) != ')'){
				str += matrix.charAt(i);
			}
		}
		
		/** split on commas */
		numStrArr = str.split(",");
		
		/** parse str format to int */
		numArr = new int[numStrArr.length];
		for(int i = 0; i < numArr.length; i++){
			numArr[i] = Integer.parseInt(numStrArr[i]);
		}
		
		/** fill in the 2-D array */
		int row = 0;
		int col = 0;
		for(int i = 0; i < numArr.length; i++){
			if(col >= size){
				col = 0;
				row++;
			}
			graph[row][col++] = numArr[i];
		}
	}
}