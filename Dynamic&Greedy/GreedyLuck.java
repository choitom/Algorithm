/**
	Author	: Tom Choi
	Date	: 08/28/2016
	
	Problem from HackerRank.com
		Lena is preparing for an important coding competition that is preceded by
		N sequential preliminary contests. She believes in "saving luck", and wants
		to check her theory. Each contest is described by two integers L and T:
			L -> the amount of luck that can be gained by winning the contest.
				 If she wins a contest, her luck balance will decrease by L.
				 If she loses, her luck balance will increase by L.
			T -> denotes the importance rating of a contest.
				 If important, T = 1; otherwise, 0.
				 
		If Lena is allowed to lose K important contests at most,
		what is the max amount of luck she can have after competing all premilinaries?
		
	Strategy
		1. Sort important contests by luck in descending order using count sort (O(n))
		2. Increment luck by K contests from the beginning of the sorted list	(O(n))
		3. Increment luck by all unimportant contests							(O(n))
		4. Decrement luck by the rest of important contests						(O(n))
		5. return luck
*/

import java.io.*;
import java.util.*;

public class GreedyLuck {

    public static void main(String[] args) throws FileNotFoundException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        File file = new File("maxLuck.txt");
		Scanner scan = new Scanner(file);
        int contests = scan.nextInt();
        int maxLoses = scan.nextInt();
        
        ArrayList<Integer> important = new ArrayList<Integer>();
        ArrayList<Integer> unimportant = new ArrayList<Integer>();
        
        for(int i = 0; i < contests; i++){
            int luck = scan.nextInt();
            int type = scan.nextInt();
            
            // unimportant contests
            if(type == 0){
                unimportant.add(luck);
            }
            // important contests
            else{
                important.add(luck);
            }
        }
        findMaxLuck(important, unimportant, maxLoses);
    }
    
    private static void findMaxLuck(ArrayList<Integer> important, ArrayList<Integer> unimportant, int maxLoses){
        int maxLuck = 0;
        
        // add all lucks from unimportant contests
        for(int i = 0; i < unimportant.size(); i++){
            maxLuck += unimportant.get(i);
        }
        
        // sort important contests by luck in descending order
        int[] imp = new int[important.size()];
        for(int i = 0; i < imp.length; i++){
            imp[i] = important.get(i);
        }
		
        if(important.size() > 0){
            imp = countSort(imp);
			
			// add lucks from K important contests
            for(int i = 0; i < maxLoses && i < imp.length; i++){
                maxLuck += imp[i];
            }
			// subtract lucks from the rest of important contests
            for(int i = maxLoses; i < imp.length; i++){
                maxLuck -= imp[i];
            }
        }
		
		// print out the maximum possible luck
        System.out.println(maxLuck);
    }
    
	// sort a list using the count sort
    private static int[] countSort(int[] lst){
        int findMaxNum = findMax(lst);
        int[] count = new int[findMaxNum+1];
        for(int i = 0; i < lst.length; i++){
            count[lst[i]]++;
        }
        
        int[] output = new int[lst.length];
        int index = output.length-1;
        for(int i = 0; i < count.length; i++){
            int freq = count[i];
            while(freq > 0){
                output[index--] = i;
                freq--;
            }
        }
        return output;
    }
    
	// find the maximum value in a list of integers
    private static int findMax(int[] lst){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < lst.length; i++){
            if(max < lst[i]){
                max = lst[i];
            }
        }
        return max;
    }
}