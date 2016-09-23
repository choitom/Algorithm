import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Problem13{
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("problem13.txt");
		Scanner scan = new Scanner(file);
		BigInteger sum = new BigInteger("0");
		while(scan.hasNextLine()){
			BigInteger num = new BigInteger(scan.nextLine());
			sum = sum.add(num);
		}
		String result = sum.toString();
		for(int i = 0; i < 10; i++){
			System.out.print(result.charAt(i));
		}
	}
}