/**
	Author	: Tom Choi
	Date	: 09/19/2016
	
	Find the minimum coins required to make a change

		0 1 2 3 4 5 6
	  2 0 0 1 1 2 2 3
	  4 0 0 1 1 1 1 2
	  6 0 0 0 0 0 0 1
  
	opt(i,n) = min(opt(i-1,n), opt(i,n-c_i))
*/

public class MinCoinChange{
	
	public static void main(String[] args){
		int n = 11;
		int[] c = {1, 5, 6, 8};
		int[][] m = new int[c.length][n+1];
		for(int j = 1; j < m[0].length; j++){
			int c_i = c[0];
			if(c_i > j){
				m[0][j] = 0;
			}else{
				m[0][j] = j/c_i;
			}
		}
		
		for(int i = 1; i < m.length; i++){
			for(int j = 1; j < m[i].length; j++){
				int c_i = c[i];
				if(c_i > j){
					m[i][j] = m[i-1][j];
				}else{
					m[i][j] = m[i][j-c_i] + 1;
				}
			}
		}
		
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[i].length; j++){
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
		
		int minCoin = Integer.MAX_VALUE;
		for(int i = 0; i < m.length; i++){
			if(m[i][n] < minCoin){
				minCoin = m[i][n];
			}
		}
		System.out.println("Min coins: " + minCoin);
	}
}