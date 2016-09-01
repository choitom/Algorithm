/**
	Author	: Tom Choi
	Date	: 09/01/2016
	
	Check if a linked list is a palindrome or not
*/
import java.util.*;

public class IsPalindrome<T>{
	private static class Node<T>{
		private T data;
		private Node<T> next;
		
		private Node(T data){
			this.data = data;
			this.next = null;
		}
	}
	
	private Node<T> root;
	private int size;
	
	public IsPalindrome(){
		root = null;
		size = 0;
	}
	
	/** Insert an item to the list */
	public void insert(T data){
		root = insert(root, data);
		size++;
	}
	
	private Node<T> insert(Node<T> n, T data){
		if(n == null){
			n = new Node<T>(data);
		}else{
			n.next = insert(n.next, data);
		}
		return n;
	}
	
	/** Removes an item from the list and return it */
	public T remove(T data){
		return remove(root, null, data);
	}
	
	private T remove(Node<T> n, Node<T> prev, T data){
		if(n == null){
			System.err.println("The item to remove doesn't exist.");
			return null;
		}else if(n.data.equals(data)){
			if(n == root){
				root = root.next;
			}else{
				prev.next = n.next;
			}
			size--;
			return n.data;
		}else{
			return remove(n.next, n, data);
		}
	}
	
	/** Check palindrome */
	public boolean isPalindrome(){
		Deque<Node<T>> reverse = reverseList();
		Node<T> current = root;
		while(current != null){
			Node<T> popped = reverse.pop();
			if(!current.data.equals(popped.data)){
				return false;
			}
			current = current.next;
		}
		return true;
	}
	
	public Deque<Node<T>> reverseList(){
		Deque<Node<T>> stack = new ArrayDeque<Node<T>>();
		Node<T> current = root;
		while(current != null){
			stack.push(current);
			current = current.next;
		}
		return stack;
	}
	
	/** Return the size of the list */
	public int size(){
		return size;
	}
	
	/** Clear the list */
	public void clear(){
		root = null;
		size = 0;
	}
	
	/** See if the list is empty or not */
	public boolean isEmpty(){
		return (size == 0) ? true : false;
	}
	
	/** Print the list */
	public void print(){
		print(root);
		System.out.println();
	}
	
	private void print(Node<T> n){
		if(n != null){
			System.out.print(n.data.toString());
			print(n.next);
		}
	}
	
	public static void main(String[] args){
		IsPalindrome<Character> lst = new IsPalindrome<Character>();
		String str = "abcdabcddcbadcba";
		char[] c = str.toCharArray();
		for(int i = 0; i < c.length; i++){
			lst.insert(c[i]);
		}
		lst.print();
		System.out.println(lst.isPalindrome());
	}
}