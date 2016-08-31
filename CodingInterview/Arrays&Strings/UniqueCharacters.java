/**
	Author	: Tom Choi
	Date	: 08/31/2016
	
	Implement an algorithm to determine if a string has all unique characters.
	What if you cannot use additional data structures?
	
	Strategy
		Ascii codes for characters are from 0 to 127.
		So, initialize an array size of 128, and map each character.
		If an index is already filled while filling the array,
		then the character is not unique.
*/

public class UniqueCharacters{
	public static void main(String[] args){
		String input = "09`u4ihya;";
		boolean unique = isUnique(input);
		System.out.println(unique);
	}
	
	public static boolean isUnique(String str){
		int[] map = new int[128];
		for(int i = 0; i < str.length(); i++){
			int index = (int)str.charAt(i);
			if(map[index] == 0){
				map[index] = 1;
			}else{
				return false;
			}
		}return true;
	}
}