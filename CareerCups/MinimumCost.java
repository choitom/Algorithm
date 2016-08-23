/**
	Author	: Tom Choi
	Date	: 08/20/2016
	
	Problem
	-> Given a matrix of positive integers, find the minimum cost
	   from the top left corner to the bottom right 
*/

public class MinimumCost{
	
	/** Test code */
	public static void main(String[] args){
		int[][] matrix = {{4,5,6},
						  {1,2,3},
						  {9,1,2}};
		int mincost = minPath(matrix, 0, 0, matrix[0][0]);
		System.out.println(mincost);
	}
	
	/**
	* Recursively search the minimum possible path in the matrix
	*
	* @param	matrix		2-D matrix
	* @param	x			x-coordinate
	* @param	y			y-coordinate
	* @param	partial		accumulated cost so far
	* @return	minimum weight from the source to the destination
	*/
	public static int minPath(int[][] matrix, int x, int y, int partial){
		int row = matrix.length-1;
		int col = matrix[matrix.length-1].length-1;
		
		if(x == row && y == col){
			return partial;
		}
		// right, down, diagonal
		else if(x+1 <= row && y+1 <= col){
			return min(minPath(matrix, x+1, y+1, partial + matrix[x+1][y+1]),
					   minPath(matrix, x+1, y, partial + matrix[x+1][y]),
					   minPath(matrix, x, y+1, partial + matrix[x][y+1]));
		}
		// down
		else if(x+1 <= row && y+1 > col){
			return minPath(matrix, x+1, y, partial + matrix[x+1][y]);
		}
		// right
		else{
			return minPath(matrix, x, y+1, partial + matrix[x][y+1]);
		}
	}
	
	/**
	* Returns the minimum value among three values
	*/
	private static int min(int x, int y, int z){
		if(x <= y && x <= z){
			return x;
		}else if(y <= x && y <= z){
			return y;
		}else{
			return z;
		}
	}
}