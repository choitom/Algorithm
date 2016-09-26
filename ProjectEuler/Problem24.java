import java.util.*;

public class Problem24{
	public static ArrayList<String> allPermutations;

	public static void main(String[] args){
	String initial = "0123456789";
	char[] digits = initial.toCharArray();
		
		allPermutations = new ArrayList<String>();
		findAllPermutations(digits);
		
		// sort permutations in lexicographic order
		String[] permArray = new String[allPermutations.size()];
		for(int i = 0; i < allPermutations.size(); i++){
			permArray[i] = allPermutations.get(i);
		}
		Arrays.sort(permArray);
		
		//print out one millionth item whose index is 1million - 1;
		System.out.println(permArray[999999]);
	}

	public static void findAllPermutations(char[] digits){

		allPermutations.add("01");
		allPermutations.add("10");

		// from the second to the very last digit
		for(int i = 2; i < digits.length; i++){
			// character to add
			char toAdd = digits[i];
			
			// storage for new permutations
			ArrayList<String> buffer = new ArrayList<String>();

			// retrieve permutations found so far
			for(int j = 0; j < allPermutations.size(); j++){
				String perm = allPermutations.get(j);
				for(int k = 0; k <= perm.length(); k++){
					String newPerm = perm.substring(0,k) + toAdd + perm.substring(k, perm.length());
					buffer.add(newPerm);
				}
			}
	
			// update allPermutations
			allPermutations = new ArrayList<String>(buffer);
		}
	}
}
