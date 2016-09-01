public class Cat extends Animal{
	Cat(String name){
		super(name);
		type = "Cat";
	}
	
	public String type(){
		return type;
	}
}