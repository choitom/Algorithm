public class Problem9{
	public static void main(String[] args){
		for(int i = 3; i < 1000; i++){
			for(int j = i+1; j < 1000; j++){
				int a = i;
				int b = j;
				double c = Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
				if(c == (int)c){
					if(a+b+c == 1000){
						int product = a*b*(int)c;
						System.out.println(a +", " + b + ", " + (int)c);
						System.out.println(product);
						return;
					}
				}
			}
		}
	}
}