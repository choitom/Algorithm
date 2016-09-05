/**
	Author	: Tom Choi
	Date	: 09/05/2016
	
	There are N buildings in a certain two-dimensional landscape.
	Each building has its own height. You could join adjacent buildings
	that are taller than each building and form a solid rectangle of
	the area (K * h_i) where K is the number of adjacent buildings and the building itself
	and h_i is the height of each building.
	
	=> Find the largest possible rectangle it can form
	
	Observation
		- each rectangle can extend to adjacent buildings to the right and left
		  if they have greater heights.
		- for each building, find the width that it can stretch to the left and right
		- then starting from the first to the last, find the largest rectangle
		  by updating (width * height)
	
	Dynamic Programming
		either previous rectangle or the current one
		=> MaxRectangle_i = max(current max, H_i * Width_i)
		
*/

import java.io.*;
import java.util.*;

public class LargestRectangle{
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("largestRectangle.txt");
		Scanner scan = new Scanner(file);
		int buildings = scan.nextInt();
		
		// store heights
		int[] height = new int[buildings];
		for(int i = 0; i < buildings; i++){
			height[i] = scan.nextInt();
		}
		
		// find right width for each building
		int[] widthRight = new int[buildings];
		widthRight[buildings-1] = 0;	// rightmost cannot stretch to right
		for(int i = 0; i < buildings - 1; i++){
			int width = 0;
			for(int j = i+1; j < buildings; j++){
				if(height[i] > height[j]){
					break;
				}else{
					width++;
				}
			}widthRight[i] = width;
		}
		
		// find left width for each building
		int[] widthLeft = new int[buildings];
		widthLeft[0] = 0;	// leftmost cannot stretch to left
		for(int i = 1; i < buildings; i++){
			int width = 0;
			for(int j = i-1; j >= 0; j--){
				if(height[i] > height[j]){
					break;
				}else{
					width++;
				}
			}widthLeft[i] = width;
		}
		
		findLargestRectangle(height, widthLeft, widthRight);
	}
	
	private static void findLargestRectangle(int[] height, int[] widthLeft, int[] widthRight){
		long max = Integer.MIN_VALUE;
		for(int i = 0; i < height.length; i++){
			max = max(max, height[i] * (widthLeft[i] + widthRight[i] + 1));
		}
		System.out.println(max);
	}
	
	private static long max(long i, long j){
		return (i >= j) ? i : j;
	}
}