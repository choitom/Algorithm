/**
	Author	: Tom Choi
	Date	: 09/01/2016
	
	Implement a queue using two stacks
	
	Strategy
		- Use one stack for storing items
		- Use the other for the temporary buffer for accessing the front element
*/
import java.util.*;

public class TwoStackQueue<T>{
	
	/** temporary buffer used for moving the data */
	private MyStack<T> buffer;
	
	/** the main stack that stores the data */
	private MyStack<T> q;
	
	public TwoStackQueue(){
		buffer = new MyStack<T>();
		q = new MyStack<T>();
	}
	
	/** push the item to the main stack */
	public void add(T data){
		q.push(data);
	}
	
	/** true if the queue is empty */
	public boolean isEmpty(){
		return q.isEmpty();
	}
	
	/**
	* Transfer all the data from the queue to the buffer and pop the top node
	* from the buffer and return it. Then, move the data back to the queue
	*/
	public T remove(){
		moveToBuffer();
		T removed = buffer.pop();
		moveToQ();
		return removed;
	}
	
	public void print(){
		if(q.isEmpty()){
			System.out.println("Queue is empty");
		}else{
			moveToBuffer();
			buffer.print();
			moveToQ();	
		}		
	}
	
	/** move all of the items from q to buffer */
	private void moveToBuffer(){
		while(!q.isEmpty()){
			buffer.push(q.pop());
		}
	}
	
	/** move all of the items from buffer to q */
	private void moveToQ(){
		while(!buffer.isEmpty()){
			q.push(buffer.pop());
		}
	}
	
	public static void main(String[] args){
		String[] arr = {"Tom", "Choi", "The", "Googler"};
		TwoStackQueue<String> queue = new TwoStackQueue<String>();
		for(int i = 0; i < arr.length; i++){
			queue.add(arr[i]);
		}
		queue.print();
		
		while(!queue.isEmpty()){
			System.out.print(queue.remove() + " ");
		}System.out.println();
		queue.print();
	}
}

class MyStack<T>{
	private class Node<T>{
		private T data;
		private Node<T> next;
		private Node(T data){
			this.data = data;
			this.next = null;
		}
	}
	
	Node<T> top;
	
	MyStack(){
		top = null;
	}
	
	void push(T data){
		Node<T> t = new Node<T>(data);
		t.next = top;
		top = t;
	}
	
	T pop(){
		if(top == null){
			throw new EmptyStackException();
		}
		T ret = top.data;
		top = top.next;
		return ret;
	}
	
	boolean isEmpty(){
		return (top == null) ? true : false;
	}
	
	void print(){
		print(top);
		System.out.println();
	}
	
	void print(Node<T> top){
		if(top != null){
			System.out.print(top.data + " ");
			print(top.next);
		}
	}
}