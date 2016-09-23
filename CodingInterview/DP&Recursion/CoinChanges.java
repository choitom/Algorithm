/**
	Author	: Tom Choi
	Date	: 09/20/2016
	
	Given 1, 5, 10, 25 cents
	Find the number of ways to make change of n
*/

public class CoinChanges{
	public static void main(String[] args){
		int[] coins = new int[]{1,5,10,25};
		int n = 100;
		int numWays = numberOfWays(n, coins);
		System.out.println(numWays);
	}
	
	public static int numberOfWays(int n, int[] coins){
		int[][] m = new int[coins.length][n+1];
		for(int i = 0; i < m.length; i++){
			m[i][0] = 1;
		}

		
		for(int i = 0; i < m.length; i++){
			int c = coins[i];
			for(int j = 1; j < m[i].length; j++){
				if(i == 0){
					m[i][j] = m[i][j-1];
				}else{
					if(c > j){
						m[i][j] = m[i-1][j];
					}else{
						m[i][j] = m[i-1][j] + m[i][j-c];
					}
				}
			}
		}
		return m[coins.length-1][n];
	}
}