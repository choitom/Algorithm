public class Problem17{
	public static final String[] ones =
		{"one","two","three","four","five","six","seven","eight","nine"};
	public static final String[] teens =
		{"eleven","twelve","thirteen","fourteen","fifteen",
		"sixteen","seventeen","eighteen","nineteen"};
	public static final String[] tens =
		{"ten","twenty","thirty","forty","fifty","sixty",
		"seventy","eighty","ninety"};
	public static final String[] hundreds =
		{"onehundred","twohundred","threehundred","fourhundred","fivehundred",
		"sixhundred","sevenhundred","eighthundred","ninehundred"};
	public static final String thousand = "onethousand";
	
	public static void main(String[] args){
		int letterCount = 0;
		for(int i = 1; i <= 1000; i++){
			// thousand
			int characters = 0;
			if(i == 1000){
				letterCount += thousand.length();
			}
			// teens
			else if(i >= 11 && i <= 19){
				int k = i % 10;
				characters = teens[k-1].length();
				letterCount += characters;
			}
			// ones and tens
			else if(i < 100){
				int placeHolder  = 1;
				int n = i;
				while(n > 0){
					int k = n % 10;
					if(placeHolder == 1 && k != 0){
						characters += ones[k-1].length();
					}else if(placeHolder == 2 && k != 0){
						characters += tens[k-1].length();
					}
					placeHolder++;
					n = n/10;
				}
				letterCount += characters;
			}
			// hundreds
			else{
				int n = i;
				int placeHolder = 1;
				while(n > 0){
					if(n >= 100){
						int k = n / 100;
						n = n % 100;
						if(n == 0){
							characters += hundreds[k-1].length();
						}else{
							characters += hundreds[k-1].length() + 3;
						}
					}else if(n >= 11 && n <= 19){
						int k = n % 10;
						characters += teens[k-1].length();
						break;
					}else{
						int k = n % 10;
						if(placeHolder == 1 && k != 0){
							characters += ones[k-1].length();
						}else if(placeHolder == 2 && k != 0){
							characters += tens[k-1].length();
						}
						placeHolder++;
						n = n/10;
					}
				}
				letterCount += characters;
			}
		}
		System.out.println(letterCount);
	}
}