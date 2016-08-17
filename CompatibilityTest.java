/**
	Author	: Tom Choi
	Date	: 08/17/2016
	
	Problem
	-> Two friends rank a list of movies like (A: 31245, B: 32415)
	-> The compatibility difference between them is the the maximum number of
	difference in the relative rankings of the same movie
	-> Find the maximum difference
*/

public class CompatibilityTest{
	
	/**
	* Find the maximum difference between two people's movie rank lists
	*
	* @param	a		rank
	* @param	b		rank
	* @return	return the max difference
	*/
	public static int maxDiff(String a, String b){
		/** check for valid inputs */
		if(a == null || b == null){
			return -1;
		}
		/** compare length of inputs */
		if(a.length() != b.length()){
			return -1;
		}
		
		int i;
		int j;
		char aChar;
		char bChar;
		int diff = 0;
		int maxdiff = 0;
		
		/** for each movie ranked by a*/
		for(i = 0; i < a.length(); i++){
			aChar = a.charAt(i);
			
			/** find where it is ranked by b */
			for(j = 0; j < b.length(); j++){
				bChar = b.charAt(j);
				if(aChar == bChar){
					diff = Math.abs(i-j);
				}
			}
			
			/** update the maximum difference in their ranks */
			if(maxdiff < diff){
				maxdiff = diff;
			}
		}
		return maxdiff;
	}
	
	public static void main(String[] args){
		String a = "12345";
		String b = "54321";
		int diff = maxDiff(a, b);
		System.out.println(diff);
	}
}