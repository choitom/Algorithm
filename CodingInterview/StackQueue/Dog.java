public class Dog extends Animal{
	Dog(String name){
		super(name);
		type = "Dog";
	}
	
	public String type(){
		return type;
	}
}