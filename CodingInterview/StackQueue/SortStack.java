/**
	Author	: Tom Choi
	Date	: 09/01/2016
	
	Write a program to sort a stack such that the smallest items are on the top.
	Only allowed to use: an additional stack, push, pop, peek, and isEmpty
	
	Strategy
		1. pop the top node from the stack and keep it
		2. find the place to store it into the buffer by popping off
		   the nodes that are greater and push them into the original stack
		3. repeat until the original stack become empty
		4. move all the items from the buffer to the original
*/

import java.util.*;

public class SortStack<T extends Comparable<T>>{
	private class Node<T>{
		private T data;
		private Node<T> next;
		
		private Node(T data){
			this.data = data;
			this.next = null;
		}
	}
	
	private Node<T> top;
	
	public SortStack(){
		this.top = null;
	}
	
	/** sort the stack in ascending order */
	public void sort(){
		if(this.isEmpty()){
			throw new EmptyStackException();
		}
		
		SortStack<T> temp = new SortStack<T>();
		while(!this.isEmpty()){
			T toSort = this.pop();
			while(!temp.isEmpty() && temp.peek().compareTo(toSort) > 0){
				this.push(temp.pop());
			}
			temp.push(toSort);
		}
		
		while(!temp.isEmpty()){
			this.push(temp.pop());
		}
	}
	
	/** return the data of the top node */
	public T peek(){
		if(top == null){
			return null;
		}
		return top.data;
	}
	
	/** remove the top node and return its data */
	public T pop(){
		if(top == null){
			throw new EmptyStackException();
		}
		T ret = top.data;
		top = top.next;
		return ret;
	}
	
	/** insert an item to the stack */
	public void push(T data){
		Node<T> t = new Node<T>(data);
		t.next = top;
		top = t;
	}
	
	/** return true if the stack is empty */
	public boolean isEmpty(){
		return (top == null) ? true : false;
	}
	
	/** print the stack from top to bottom */
	public void print(){
		print(top);
		System.out.println();
	}
	
	private void print(Node<T> top){
		if(top != null){
			System.out.print(top.data + " ");
			print(top.next);
		}
	}
	public static void main(String[] args){
		String[] tom = {"Tom", "Choi", "The", "Googler"};
		SortStack<String> stack = new SortStack<String>();
		for(String t : tom){
			stack.push(t);
		}
		stack.print();
		stack.sort();
		stack.print();
	}
}