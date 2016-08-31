/**
	Author	: Tom Choi
	Date	: 08/31/2016
	
	Implement a method to perform basic string compression
	using the counts of repeated characters. For example,
	"aabcccccaaa" -> "a2b1c5a3". If the compressed string is
	longer than the original string, then return the original.
*/
import java.util.*;

public class StringCompression{
	public static void main(String[] args){
		System.out.println(stringCompress("aabcccccaaa"));
		System.out.println(stringCompress("abcdefghijklmnop"));
	}
	
	public static String stringCompress(String s){
		char[] arr = s.toCharArray();
		
		StringBuilder sb = new StringBuilder();
		char prev = arr[0];
		sb.append(prev);
		int count = 1;
		
		for(int i = 0; i < arr.length; i++){
			if(prev == arr[i]){
				count++;
			}else{
				sb.append(String.valueOf(count));
				sb.append(arr[i]);
				count = 1;
			}
			prev = arr[i];
		}
		sb.append(String.valueOf(count));
		String compressed = sb.toString();
		if(compressed.length() > s.length()){
			return s;
		}
		return compressed;
	}
}