/**
	Author	: Tom Choi
	Date	: 08/31/2016
	
	Write an algorithm such that if an element in an MxN matrix is 0,
	its entire row and column are set to 0.

	Strategy
		-> read through the matrix and find coordinates of 0's
		-> replace each row and column to 0
*/
import java.util.*;

public class ZeroMatrix{
	public static void main(String[] args){
		int[][] m = {{1,1,1,1,1,0},
					 {1,1,1,1,1,1},
					 {1,1,0,1,1,1},
					 {1,1,1,1,1,1}};
		replace(m);
		print(m);
	}
	
	public static void replace(int[][] m){
		HashSet<int[]> coords = getCoordinates(m);
		for(int[] c : coords){
			replaceRowCol(m, c[0], c[1]);
		}
	}
	
	private static void replaceRowCol(int[][] m, int row, int col){
		// replace row
		for(int j = 0; j < m[row].length; j++){
			m[row][j] = 0;
		}
		// replace col
		for(int i = 0; i < m.length; i++){
			m[i][col] = 0;
		}
	}
	
	private static HashSet<int[]> getCoordinates(int[][] m){
		HashSet<int[]> set = new HashSet<int[]>();
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[i].length; j++){
				int[] coor = new int[2];
				if(m[i][j] == 0){
					coor[0] = i;
					coor[1] = j;
					set.add(coor);
				}
			}
		}return set;
	}
	
	private static void print(int[][] m){
		for(int i = m.length-1; i >= 0; i--){
			for(int j = 0; j < m[i].length; j++){
				System.out.print(m[i][j] + " ");
			}System.out.println();
		}
	}
}