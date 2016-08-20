/**
	Author	: Tom Choi
	Date	: 08/20/2016
	
	Problem
	-> Given two strings, find all the interleavings of the strings such that
		characters from two strings are in the same order as they were
*/

public class Interleaving{
	private String s1;
	private String s2;
	
	public Interleaving(String s1, String s2){
		this.s1 = s1;
		this.s2 = s2;
	}
	
	public void interleave(){
		interleaveHelper(0, 0, "");
	}
	
	/**
	* Recursively append characters from two strings in the same order
	*/
	private void interleaveHelper(int i, int j, String partial){
		if(i == s1.length() && j == s2.length()){
			System.out.println(partial);
			return;
		}
		if(i < s1.length()){
			interleaveHelper(i+1, j, partial + s1.charAt(i));
		}
		if(j < s2.length()){
			interleaveHelper(i, j+1, partial + s2.charAt(j));
		}
	}
	
	public static void main(String[] args){
		String s1 = "ab";
		String s2 = "c";
		Interleaving i = new Interleaving(s1, s2);
		i.interleave();
	}
}