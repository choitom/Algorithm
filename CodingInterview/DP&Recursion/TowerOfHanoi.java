/**
	Author	: Tom Choi
	Date	: 09/18/2016
	
	Tower Of Hanoi
*/

public class TowerOfHanoi{
	
	/**
		Suppose we have two disks
		1. move top disk from start to temp
		2. move bottom disk from start to dest
		3. move top disk from temp to dest
	*/
	public static void main(String[] args){
		int disks = 3;
		TowerOfHanoi(2, "A", "B", "C");
	}
	
	private static void TowerOfHanoi(int n, String start, String temp, String dest){
		if(n >= 1){
			TowerOfHanoi(n-1, start, dest, temp);
			System.out.println("moving disk from " + start + " to " + dest);
			TowerOfHanoi(n-1, temp, start, dest);
		}
	}
}