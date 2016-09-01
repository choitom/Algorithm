public abstract class Animal{
	protected String name;
	protected String type;
	
	protected Animal(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public abstract String type();
}