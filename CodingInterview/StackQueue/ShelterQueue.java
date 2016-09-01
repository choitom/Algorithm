public class ShelterQueue<A>{
	private static class Node<A>{
		private Node<A> next;
		private A animal;		
		private Node(A animal){
			this.animal = animal;
			this.next = null;
		}
	}
	
	private Node<A> head;
	
	public ShelterQueue(){
		this.head = null;
	}
	
	/** return the animal of a certain type */
	public A dequeue(String type){
		/** any type */
		if(type.equals("Animal")){
			Node<A> ret = head;
			head = head.next;
			return ret.animal;
		}else{
			return dequeueType(head, null, type);
		}
	}
	
	private A dequeueType(Node<A> head, Node<A> prev, String type){
		Animal animal = (Animal)head.animal;
		if(head == null){
			System.err.println(type + " does not exist in the queue.");
			return null;
		}else if(type.equals(animal.type())){
			A ret = head.animal;
			prev.next = head.next;
			return ret;
		}else{
			return dequeueType(head.next, head, type);
		}
	}
	
	/** add an animal to the end of the queue */
	public void add(A animal){
		head = add(head, animal);
	}
	
	private Node<A> add(Node<A> head, A animal){
		if(head == null){
			head = new Node<A>(animal);
		}else{
			head.next = add(head.next, animal);
		}
		return head;
	}
	
	/** true if the queue is empty */
	public boolean isEmpty(){
		return (head == null) ? true : false;
	}
	
	/** print the queue */
	public void print(){
		print(head);
		System.out.println();
	}
	
	private void print(Node<A> head){
		if(head != null){
			Animal anim = (Animal)head.animal;
			System.out.print(anim.getName() + "(" + anim.type() + ") ");
			print(head.next);
		}
	}
}