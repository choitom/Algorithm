/**
	Author	: Tom Choi
	Date	: 09/18/2016
	
	Imagine a robot sitting on the upper left corner of grid with r rows
	and c columns. The robot can only move in two directions, right and down,
	but certain cells far "off limits" such that the robot cannot step on them.
	Design an algorithm to find a path for the robot from the top left to
	the bottom right.
*/

import java.io.*;
import java.util.*;

public class RobotInGrid{
	// can only move either right or down
	
	public static int paths = 0;
	public static int ROW = 0;
	public static int COL = 0;
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("RobotGrid.txt");
		Scanner scan = new Scanner(file);
		int r = scan.nextInt();
		int c = scan.nextInt();
		ROW = r-1;
		COL = c-1;
		int[][] grid = new int[r][c];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				grid[i][j] = scan.nextInt();
			}
		}
		findPaths(grid, 0, 0, new ArrayList<String>());
		System.out.println(paths);
	}
	
	public static void findPaths(int[][] grid, int row, int col, ArrayList<String> p){
		// reached the destination
		if(row == ROW && col == COL){
			System.out.println(p);
			paths++;
		}
		// out of bounds
		else if(row > ROW || col > COL){
			return;
		}
		// off limit
		else if(grid[row][col] == 1){
			return;
		}
		// otherwise, either move right or down
		else{
			ArrayList<String> p1 = new ArrayList<String>(p);
			ArrayList<String> p2 = new ArrayList<String>(p);
			p1.add("Right");
			p2.add("Down");
			
			// move right
			findPaths(grid, row, col+1, p1);
			
			// move down
			findPaths(grid, row+1, col, p2);
		}
	}
}