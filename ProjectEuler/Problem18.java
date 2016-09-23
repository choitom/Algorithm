import java.io.*;
import java.util.*;

/**
	Very naive approach -> find the total for every path in the pyramid
*/
public class Problem18{
	public static ArrayList<Integer> allPaths = new ArrayList<Integer>();
	public static ArrayList<ArrayList<Integer>> pyramid;
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("problem19.txt");
		Scanner scan = new Scanner(file);
		
		pyramid = new ArrayList<ArrayList<Integer>>();
		
		while(scan.hasNextLine()){
			String level = scan.nextLine();
			String[] parsed = level.split(" ");
			ArrayList<Integer> nums = new ArrayList<Integer>();
			for(int i = 0; i < parsed.length; i++){
				nums.add(Integer.parseInt(parsed[i]));
			}
			pyramid.add(nums);
		}
		findPaths(0, 0, 0, pyramid.size()-1);
		int max = 0;
		for(int i = 0; i < allPaths.size(); i++){
			if(allPaths.get(i) > max){
				max = allPaths.get(i);
			}
		}
		System.out.println(max);
	}
	
	public static void findPaths(int index, int partial, int curDepth, int dest){
		if(curDepth == dest){
			allPaths.add(partial + pyramid.get(curDepth).get(index));
		}else{
			findPaths(index, partial + pyramid.get(curDepth).get(index), curDepth+1, dest);
			findPaths(index+1, partial + pyramid.get(curDepth).get(index), curDepth+1, dest);
		}
	}
}