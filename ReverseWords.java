/**
	Author	: Tom Choi
	Date	: 08/16/2016
	
	Problem
		-> Given an input string, reverse the string word by word
		where a word is defined as a sequence of non space characters
		
		ex) the sky is blue -> blue is sky the
*/

public class ReverseWords{
	public static void main(String[] args){
		String str = "the sky is blue and sometimes is red";
		String reverseStr = reverse(str);
		System.out.println(str);
		System.out.println(reverseStr);
	}
	
	/**
	* Split the words by empty space and reverse append
	*/
	public static String reverse(String str){
		String[] strArr = str.split(" ");
		String reversed = "";
		for(int i = strArr.length-1; i >= 0; i--){
			if(i != 0){
				reversed = reversed + strArr[i] + " ";
			}else{
				reversed = reversed + strArr[i];
			}
		}
		return reversed;
	}
}