/**
	Author	: Tom Choi
	Date	: 09/04/2016
	
	There are four operations that you can use for text editing
		1 abc	-> append abc the output string, S
		2 3		-> remove the last 3 characters of S
		3 3		-> print out the third character of S
		4		-> undo the last opeation of type 1 or 2, reverting S to
				   the state it was in prior to that operation
	
	Given a text file in the format listed above, implement a simple text editor
*/

import java.io.*;
import java.util.*;

public class TextEditor{
	
	// storage for undo calls
	private Deque<String> stack;
	
	// current state of the string
	private String current;
	
	public TextEditor(){
		this.stack = new ArrayDeque<String>();
		this.current = "";
		stack.push(current);
	}
	
	public void push(String str){
		current += str;
		stack.push(current);
	}
	
	public void delete(int k){
		current = current.substring(0, current.length()-k);
		stack.push(current);
	}
	
	public void print(int k){
		System.out.println(current.charAt(k-1));
	}
	
	// pop the current state and retrieve the previous state
	public void undo(){
		stack.pop();
		current = stack.peek();
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("TextEditorCommands.txt");
		Scanner scan = new Scanner(file);
		TextEditor editor = new TextEditor();
		
		int n = scan.nextInt();
		for(int i = 0; i < n; i++){
			int op = scan.nextInt();
			switch(op){
				// append string
				case 1:
					editor.push(scan.next());
					break;
				// delete the last k characters
				case 2:
					editor.delete(scan.nextInt());
					break;
				// print out kth character
				case 3:
					editor.print(scan.nextInt());
					break;
				// undo & revert S to its prior state
				case 4:
					editor.undo();
					break;
				default:
					break;
			}
		}
		scan.close();
	}
}