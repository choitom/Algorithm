/**
	Author	: Tom Choi
	Date	: 08/31/2016
	
	Write a method to replace all spaces in a string with '%20'
	You may assume that array has sufficient space at the end to
	hold additional characters.
	
	Input	: "Mr John Smith"
	Outuput	: "Mr%20John%20Smith"
	
	Strategy
		Count the number of spaces within the string
		Expand char array to hold additional characters
*/

public class StringReplace{
	public static void main(String[] args){
		String s = "Mr John Smith";
		String replaced = replaceStr(s);
		System.out.println("Original: " + s);
		System.out.println("Replaced: " + replaced);
	}
	
	public static String replaceStr(String s){
		int count = 0;
		for(int i = 0; i < s.length(); i++){
			if((int)s.charAt(i) == 32){
				count++;
			}
		}
		// no space -> just return the original string
		if(count == 0){
			return s;
		}
		char[] c = new char[s.length() + 3 * count];
		int index = 0;
		for(int i = 0; i <s.length(); i++){
			if((int)s.charAt(i) == 32){
				c[index++] = '%';
				c[index++] = '2';
				c[index++] = '0';
			}else{
				c[index++] = s.charAt(i);
			}
		}
		return String.valueOf(c);
	}
}