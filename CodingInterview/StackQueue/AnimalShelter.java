/**
	Author	: Tom Choi
	Date	: 09/01/2016
	
	An animal shelter, which holds only dogs and cats, operates on a strictly
	"first in, first out" basis. People must adopt either the "oldest"
	(based on arrival time) of all animals at the shetler, or they can select
	whether they would prefer a dog or a cat (and will receive the oldest animal
	of tha type). They cannot select which sepecific animal they would like.
	Create the data structures to maintain this system and implement operations
	such as enqueue, dequeueAny, dequeueDog, dequeueCat.
	
	
	Use polymorphism for the animals
		Super class : Animal
		Sub classes	: Cat, Dog
*/

public class AnimalShelter<A>{
	private ShelterQueue<A> q;
	
	public AnimalShelter(){
		q = new ShelterQueue<A>();
	}
	
	/** return the first animal in the queue */
	public A dequeueAny(){
		return q.dequeue("Animal");
	}
	
	/** return the first dog in the queue */
	public A dequeueDog(){
		return q.dequeue("Dog");
	}
	
	/** return the first cat in the queue */
	public A dequeueCat(){
		return q.dequeue("Cat");
	}
	
	public void add(A animal){
		q.add(animal);
	}
	
	public boolean isEmpty(){
		return q.isEmpty();
	}
	
	public void print(){
		q.print();
	}
	
	public static void main(String[] args){
		AnimalShelter<Animal> shelter = new AnimalShelter<Animal>();
		
		String[] dogNames = {"A", "B", "C", "D", "E"};
		String[] catNames = {"a", "b", "c", "d", "e"};
		for(int i = 0; i < dogNames.length; i++){
			shelter.add(new Dog(dogNames[i]));
		}
		
		for(int i = 0; i < catNames.length; i++){
			shelter.add(new Cat(catNames[i]));
		}
		
		for(int i = 0; i <catNames.length; i++){
			Animal cat = shelter.dequeueCat();
			System.out.println(cat.type() + ": " + cat.getName());
		}
		shelter.print();
	}
}