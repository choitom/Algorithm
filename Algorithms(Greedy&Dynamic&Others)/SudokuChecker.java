import java.io.*;
import java.util.*;

public class SudokuChecker{
	
	private int dimension;
	private int limit;
	private int[][] m;
	
	public SudokuChecker(int[][] m, int d){
		this.dimension = d;
		this.limit = dimension * dimension;
		this.m = m;
	}
	
	public boolean isValidSudoku(){
		if(!checkRows()){
			return false;
		}
		if(!checkCols()){
			return false;
		}
		if(!checkBox()){
			return false;
		}
		return true;
	}
	
	/**
	* check each NxN small box within sudoku
	*/
	private boolean checkBox(){
		for(int i = 0; i < dimension; i++){
			for(int j = 0; j < dimension; j++){
				
				int[] box = new int[m.length];
				int index = 0;
				for(int k = 0; k < dimension; k++){
					for(int t = 0; t < dimension; t++){
						box[index++] = m[i*dimension + k][j*dimension + t];
					}
				}
				if(!isValid(box)){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	* check each col in sudoku
	*/
	private boolean checkCols(){
		for(int j = 0; j < m.length; j++){
			int[] col = new int[m.length];
			for(int i = 0; i < m.length; i++){
				col[i] = m[i][j];
			}
			if(!isValid(col)){
				return false;
			}
		}
		return true;
	}
	
	/**
	* check each row in sudoku
	*/
	private boolean checkRows(){
		for(int i = 0; i < m.length; i++){
			int[] row = m[i];
			if(!isValid(row)){
				return false;
			}
		}
		return true;
	}
	
	/**
	* check the uniqueness of each number in an array
	*/
	private boolean isValid(int[] arr){
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < arr.length; i++){
			if(arr[i] > limit){
				return false;
			}
			if(set.contains(arr[i])){
				return false;
			}
			set.add(arr[i]);
		}
		return true;
	}
	
	/**
	* print sudoku
	*/
	private void print(){
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m.length; j++){
				System.out.print(m[i][j] + " ");
			}System.out.println();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("sudoku_output.txt", "UTF-8");
		File file = new File("sudoku_input.txt");
		Scanner scan = new Scanner(file);
		int testCases = scan.nextInt();
		
		int caseNumber = 1;
		
		for(int i = 0; i < testCases; i++){
			int dimension = scan.nextInt();
			int wh = dimension * dimension;
			int[][] m = new int[wh][wh];
			for(int j = 0; j < wh; j++){
				for(int k = 0; k < wh; k++){
					m[j][k] = scan.nextInt();
				}
			}
			SudokuChecker sudoku = new SudokuChecker(m, dimension);
			boolean isSudoku = sudoku.isValidSudoku();
			String caseStr = "Case #" + String.valueOf(caseNumber) + ": ";
			if(isSudoku){
				writer.println(caseStr + "Yes");
			}else{
				writer.println(caseStr + "No");
			}
			caseNumber++;
		}
		scan.close();
		writer.close();
	}
}