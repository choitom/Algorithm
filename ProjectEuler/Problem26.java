public class Problem26{
	public static void main(String[] args){
		/**
			1. exclude all even numbers and multiples of 5
			2. observe the pattern in division
			10 / 3 -> 3 ... 1
			10 / 3 -> 3 ... 1
			10 / 3 -> 3 ... 1
			
			
			10 / 7 -> 1 ... 3
			30 / 7 -> 4 ... 2
			20 / 7 -> 2 ... 6
			60 / 7 -> 8 ... 4
			40 / 7 -> 5 ... 5
			50 / 7 -> 7 ... 1
			
			10 / 11 -> 0 ... 10
			100 / 11 -> 9 ... 1
			10 / 11 -> 0 ... 10
			100 / 11 -> 9 ... 1
			
			10 / 101 -> 0 ... 10
			100 / 101 -> 0 ... 100
			1000 / 101 -> 9 ... 91
			910 / 101 -> 9 ... 1
			10 / 101 -> 0 ... 10
		*/
		
		int maxCycle = 0;
		int maxNum = 0;
		for(int i = 3; i < 1000; i += 2){
			if(i % 5 != 0){
				int cycle = findCycle(i);
				if(cycle > maxCycle){
					maxCycle = cycle;
					maxNum = i;
				}
			}
		}
		System.out.println(maxNum + ", " + maxCycle);
	}
	
	public static int findCycle(int n){
		int startDivSignature = 0;
		int startRemSignature = 0;
		int cycleLength = 0;
		int value = 1;
		
		do{
			value *= 10;
			int div = value/n;
			value %= n;
			if(cycleLength == 0){
				startDivSignature = div;
				startRemSignature = value;
			}else{
				if(startDivSignature == div && startRemSignature == value){
					break;
				}
			}
			cycleLength++;
		}while(true);
		
		return cycleLength;
	}
}