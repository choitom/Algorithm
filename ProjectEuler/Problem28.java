/*
1					interval = 0
3	5	7	9		interval = 2
13	17	21	25		interval = 4
31	37	43	49		interval = 6
...
*/

public class Problem28{
	public static void main(String[] args){
		int sum = 1;
		int stretch = 0;
		int jump = 2;
		int interval = 2;
		int squareFactor = 1;
		while(stretch != 500){
			squareFactor += jump;
			int square = squareFactor * squareFactor;
			for(int i = 0; i < 4; i++){
				sum += square;
				square = square - interval;
			}
			interval += 2;
			stretch++;
		}
		System.out.println(sum);
	}
}