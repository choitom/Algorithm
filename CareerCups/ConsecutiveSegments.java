import java.util.*;

public class ConsecutiveSegments{
	
	private static ArrayList<ArrayList<Integer>> segments;
	
	public static void main(String[] args){
		int[] arr = {2,4,6,8,10, -1, -2, -3, -4, -5, 10, 20, 30, 40, 50};
		segments = new ArrayList<ArrayList<Integer>>();
		findSegments(arr);
		if(segments != null){
			for(int i = 0; i < segments.size(); i++){
				System.out.println(segments.get(i));
			}
		}
	}
	
	private static void findSegments(int[] arr){
		// empty or one -> no sequence
		if(arr.length == 0 || arr.length == 1){
			return;
		}
		
		ArrayList<Integer> seg = new ArrayList<Integer>();
		
		int UNDEFINED = Integer.MIN_VALUE;
		int diff = UNDEFINED;
		
		for(int i = 0; i < arr.length; i++){
			// first element of the sequence
			if(seg.size() == 0 && diff == UNDEFINED){
				seg.add(arr[i]);
			}
			// second element of the sequence
			else if(seg.size() == 1 && diff == UNDEFINED){
				seg.add(arr[i]);
				diff = arr[i] - arr[i-1];
			}
			// found the next sequence
			else if(diff == (arr[i] - arr[i-1])){
				seg.add(arr[i]);
			}
			// end of the sequence
			else{
				segments.add(seg);
				diff = UNDEFINED;
				seg = new ArrayList<Integer>();
				seg.add(arr[i]);
			}
		}
		if(seg.size() > 1){
			segments.add(seg);
		}
	}
}