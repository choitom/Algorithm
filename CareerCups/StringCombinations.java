/**
	Author	: Tom Choi
	Date	: 08/15/2016
	
	Problem	:
		- A string consists of '0', '1', and '?'
		- '?' can be either '0' or '1'.
		- Find all possible combinations for a string
*/

import java.util.*;

public class StringCombinations{
	private String randString;
	private ArrayList<String> lst;
	
	public StringCombinations(String randString){
		this.randString = randString;
		this.lst = new ArrayList<String>();
	}
	
	public void showAllCombinations(){
		findAllCombinations(0, "");
		System.out.println("The original string: " + randString);
		for(int i = 0; i < lst.size(); i++){
			System.out.println(lst.get(i));
		}
	}
	
	private void findAllCombinations(int index, String str){
		if(index >= randString.length()){
			lst.add(str);
		}else{
			String c = String.valueOf(randString.charAt(index));
			if(c.equals("0")){
				findAllCombinations(index + 1, str + "0");
			}else if(c.equals("1")){
				findAllCombinations(index + 1, str + "1");
			}else{
				findAllCombinations(index + 1, str + "0");
				findAllCombinations(index + 1, str + "1");
			}
		}
	}
	
	public static void main(String[] args){
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		
		int randInt;
		for(int i = 0; i < 15; i++){
			randInt = rand.nextInt(3);
			switch(randInt){
				case 0:
					sb.append("0");
					break;
				case 1:
					sb.append("1");
					break;
				case 2:
					sb.append("?");
			}
		}
		
		StringCombinations sc = new StringCombinations(sb.toString());
		sc.showAllCombinations();
	}
}