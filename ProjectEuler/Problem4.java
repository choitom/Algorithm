public class Problem4{
	public static void main(String[] args){
		int max = 0;
		for(int i = 100; i < 1000; i++){
			for(int j = 100; j < 1000; j++){
				if(isPalindrome(i*j)){
					if (i*j > max){
						max = i*j;
					}
				}
			}
		}
		System.out.println(max);
	}
	
	private static boolean isPalindrome(int n){
		String str = String.valueOf(n);
		char[] c = str.toCharArray();
		for(int i = 0; i < c.length/2; i++){
			if(c[i] != c[c.length-i-1]){
				return false;
			}
		}
		return true;
	}
}