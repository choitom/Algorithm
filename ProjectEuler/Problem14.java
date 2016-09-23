public class Problem14{
	public static void main(String[] args){
		long sequenceLength = 0;
		long startNumber = 0;
		long sequence;
		
		for(int i = 2; i <= 1000000; i++){
			int length = 1;
			sequence = i;
			while(sequence != 1){
				if(sequence%2 == 0){
					sequence = sequence/2;
				}else{
					sequence = 3*sequence + 1;
				}
				length++;
			}
			if(length > sequenceLength){
				sequenceLength = length;
				startNumber = i;
			}
		}
		System.out.println(startNumber);
	}
}