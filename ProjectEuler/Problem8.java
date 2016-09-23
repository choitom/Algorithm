import java.io.*;
import java.util.*;

public class Problem8{
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("problem8.txt");
		Scanner scan = new Scanner(file);
		String str = "";
		while(scan.hasNextLine()){
			str += scan.nextLine();
		}
		
		char[] c = str.toCharArray();
		long max = 0L;
		for(int i = 0; i + 12 < c.length; i++){
			long sum = 1L;
			for(int j = 0; j < 13; j++){
				sum *= Integer.parseInt(String.valueOf(c[i+j]));
			}
			if(sum > max){
				max = sum;
			}
		}
		System.out.println(max);
	}
}