import java.util.*;
import java.io.*;

public class Problem22{
	
	public static String[] str;
	public static String[] temp;
	public static void main(String[] args) throws FileNotFoundException{
		// read input
		File file = new File("problem22.txt");
		Scanner scan = new Scanner(file);
		
		// sanitize
		str = scan.nextLine().split(",");
		ArrayList<String> lst = new ArrayList<String>();
		for(int i = 0; i < str.length; i++){
			lst.add(str[i].substring(1,str[i].length()-1));
		}
		for(int i = 0; i < lst.size(); i++){
			str[i] = lst.get(i);
		}
		
		// 1. quick sort
		//quickSort(0, str.length-1);
		
		// 2. mege sort
		temp = new String[str.length];
		mergeSort(0, str.length-1);
		
		// find sum
		findSum();
	}
	
	private static void mergeSort(int low, int high){
		if(low < high){
			int mid = (low + high)/2;
			mergeSort(low, mid);
			mergeSort(mid+1, high);
			merge(low, mid, high);
		}
	}
	
	private static void merge(int low, int mid, int high){
		for(int i = low; i<= high; i++){
			temp[i] = str[i];
		}
		
		int l = low;
		int r = mid+1;
		int i = low;
		while(l <= mid && r <= high){
			if(temp[l].compareTo(temp[r]) <= 0){
				str[i++] = temp[l++];
			}else{
				str[i++] = temp[r++];
			}
		}
		while(l <= mid){
			str[i++] = temp[l++];
		}
		while(r <= high){
			str[i++] = temp[r++];
		}
	}
	
	private static void findSum(){
		long sum = 0L;
		int pos = 1;
		for(int i = 0; i < str.length; i++){
			String word = str[i];
			int localSum = 0;
			for(int j = 0; j < word.length(); j++){
				localSum += (int)(word.charAt(j))-64;
			}
			sum += pos++ * localSum;
		}
		System.out.println(sum);
	}
	
	private static void quickSort(int low, int high){
		if(low < high){
			int p = partition(low, high);
			quickSort(low, p-1);
			quickSort(p+1, high);
		}
	}
	
	private static int partition(int low, int high){
		String pivot = str[high];
		int wall = low;
		for(int i = low; i < high; i++){
			if(str[i].compareTo(pivot) < 0){
				swap(wall, i);
				wall++;
			}
		}
		swap(wall, high);
		return wall;
	}
	
	private static void swap(int i, int j){
		String temp = str[i];
		str[i] = str[j];
		str[j] = temp;
	}
	
	private static void print(){
		for(int i = 0; i < str.length; i++){
			System.out.print(str[i] + " ");
		}System.out.println();
	}
}