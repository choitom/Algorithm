import java.io.*;
import java.util.*;

public class Problem11{
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("problem11.txt");
		Scanner scan = new Scanner(file);
		int[][] grid = new int[20][20];
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20; j++){
				grid[i][j] = scan.nextInt();
			}
		}
		
		int max = 0;
		for(int i = 3; i + 3 < 20; i++){
			for(int j = 3; j + 3 < 20; j++){
				int d1 = grid[i-3][j-3]*grid[i-2][j-2]*grid[i-1][j-1]*grid[i][j];
				int d2 = grid[i-3][j+3]*grid[i-2][j+2]*grid[i-1][j+1]*grid[i][j];
				int d3 = grid[i][j]*grid[i+1][j+1]*grid[i+2][j+2]*grid[i+3][j+3];
				int d4 = grid[i][j]*grid[i+1][j-1]*grid[i+2][j-2]*grid[i+3][j-3];
				int h1 = grid[i][j-3]*grid[i][j-2]*grid[i][j-1]*grid[i][j];
				int h2 = grid[i][j]*grid[i][j+1]*grid[i][j+2]*grid[i][j+3];
				int v1 = grid[i-3][j]*grid[i-2][j]*grid[i-1][j]*grid[i][j];
				int v2 = grid[i][j]*grid[i+1][j]*grid[i+2][j]*grid[i+3][j];
				int localmax = max(d1,d2,d3,d4,h1,h2,v1,v2);
				if(localmax > max){
					max = localmax;
				}
			}
		}System.out.println(max);
	}
	
	private static int max(int d1, int d2, int d3, int d4, int h1, int h2, int v1, int v2){
		int[] arr = new int[]{d1,d2,d3,d4,h1,h2,v1,v2};
		int max = d1;
		for(int i = 1; i < arr.length; i++){
			if(max < arr[i]){
				max = arr[i];
			}
		}
		return max;
	}
	
	private static void print(int[][] grid){
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++){
				System.out.print(grid[i][j] + " ");
			}System.out.println();
		}
	}
}