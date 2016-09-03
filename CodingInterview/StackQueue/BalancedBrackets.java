/**
	Author	: Tom Choi
	Date	: 09/03/2016
	
	Using the stack see if the brackets are balanced or not
*/

import java.io.*;
import java.util.*;

public class BalancedBrackets{
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("brackets.txt");
		Scanner scan = new Scanner(file);
		int tests = scan.nextInt();
		for(int i = 0; i < tests; i++){
			String s = scan.next();
			boolean isBalanced = isBalanced(s);
			if(isBalanced){
				System.out.println("YES");
			}else{
				System.out.println("NO");
			}
		}
	}
	
	private static boolean isBalanced(String s){
		S<Character> stack = new S<Character>();
		char[] arr = s.toCharArray();
		char c;
		for(int i = 0; i < arr.length; i++){
			c = arr[i];
			
			// open type
			if(isOpen(c)){
				stack.push(c);
			}
			// close type
			else{
				if(stack.isEmpty()){
					return false;
				}
				char popped = stack.pop();
				if(popped != match(c)){
					return false;
				}
			}
		}
		if(!stack.isEmpty()){
			return false;
		}
		return true;
	}
	
	// find the corresponding open bracket
	private static char match(char c){
		char match = ' ';
		switch(c){
			case '}' :
				match = '{';
				break;
			case ']' :
				match = '[';
				break;
			case ')' :
				match = '(';
				break;
		}
		return match;
	}
	
	// check if a bracket open type or not
	private static boolean isOpen(char c){
		return (c == '(' || c == '{' || c == '[') ? true : false; 
	}
}

class S<T>{
	private class Node<T>{
		private T data;
		private Node<T> next;
		private Node(T data){
			this.data = data;
			this.next = null;
		}
	}
	
	Node<T> top;
	
	S(){
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
	
	T peek(){
		if(top == null){
			throw new EmptyStackException();
		}
		return top.data;
	}
	
	boolean isEmpty(){
		return (top == null) ? true : false;
	}
}