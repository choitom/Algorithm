/**
	Author	: Tom Choi
	Date	: 09/01/2016
	
	Imagine a stack of plates. If the stack gets too high, it might topple.
	Create a data structure that initializes a new stack when a stack is full,
	but behaves the same as a single stack.
*/
import java.util.*;

public class SetOfStacks<T>{
	private ArrayList<Stack<T>> stackSet;
	private Stack<T> currentStack;
	private int numStacks;
	
	public SetOfStacks(){
		init();
	}
	
	public void push(T data){
		if(currentStack.isFull()){
			Stack<T> newStack = new Stack<T>();
			stackSet.add(newStack);
			currentStack = newStack;
			numStacks++;
		}
		currentStack.push(data);
	}
	
	public void pushAt(T data, int index){
		if(index < 0 || index >= numStacks){
			System.err.println(index + "th stack doesn't exist");
			return;
		}
		if(stackSet.get(index).isFull()){
			System.err.println("The " + index + "th stack is full");
			currentStack.push(data);
		}else{
			stackSet.get(index).push(data);
		}
	}
	
	public T pop(){
		return currentStack.pop();
	}
	
	public T popAt(int index){
		if(index < 0 || index >= numStacks){
			System.err.println(index + "th stack doesn't exist");
			return null;
		}
		return stackSet.get(index).pop();
	}
	
	/** Returns the number of the stacks in the set */
	public int size(){
		return numStacks;
	}
	
	/** Print out the set of stacks */
	public void print(){
		for(int i = stackSet.size()-1; i >= 0; i--){
			System.out.print(i + ": ");
			Stack<T> stack = stackSet.get(i);
			stack.print();
		}
	}
	
	/** Initialize instance variables */
	private void init(){
		this.stackSet = new ArrayList<Stack<T>>();
		this.currentStack = new Stack<T>();
		stackSet.add(currentStack);
		this.numStacks = 1;
	}
	
	public static void main(String[] args){
		SetOfStacks<Integer> s = new SetOfStacks<Integer>();
		for(int i = 0; i < 15; i++){
			s.push(i);
		}
		s.print();
		s.pushAt(1, 1);
		s.popAt(5);
		s.print();
	}
}

class Stack<T>{
	private static class Node<T>{
		private T data;
		private Node<T> next;
		
		private Node(T data){
			this.data = data;
			this.next = null;
		}
	}
	
	Node<T> top;
	int size;
	final int MAX_CAPACITY = 10;
	
	Stack(){
		top = null;
		size = 0;
	}
	
	/** add an item to the stack */
	void push(T data){
		if(top == null){
			top = new Node(data);
		}else{
			Node<T> t = new Node(data);
			t.next = top;
			top = t;
		}
		size++;
	}
	
	/** remove the top item and return it */
	T pop(){
		if(top == null){
			throw new EmptyStackException();
		}
		T ret = top.data;
		top = top.next;
		return ret;
	}
	
	/** returns the top item */
	T peek(){
		return top.data;
	}
	
	/** size of the stack */
	int size(){
		return size;
	}
	
	/** returns true if the stack is full*/
	boolean isFull(){
		return (size < MAX_CAPACITY) ? false : true;
	}
	
	/** true if empty */
	boolean isEmpty(){
		return (size == 0) ? true : false;
	}
	
	/** clear everything */
	void clear(){
		size = 0;
		top = null;
	}
	
	/** print the stack from top to bottom */
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