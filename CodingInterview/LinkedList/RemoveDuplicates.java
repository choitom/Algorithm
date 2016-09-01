/**
	Author	: Tom Choi
	Date	: 08/31/2016
	
	Remove duplicates in a linked list
*/

public class RemoveDuplicates{
	
	private static class Node{
		private Node next;
		private int data;
		
		private Node(int data){
			this.next = null;
			this.data = data;
		}
	}
	
	private Node root;
	private int size;
	
	public RemoveDuplicates(){
		root = null;
		size = 0;
	}
	
	/** remove duplicated items in the list */
	public void removeDuplicates(){
		Node current = root;
		while(current != null){
			Node runner = current.next;
			Node prev = current;
			while(runner != null){
				if(runner.data == current.data){
					prev.next = runner.next;
					runner = prev.next;
					size--;
				}else{
					prev = runner;
					runner = runner.next;
				}
			}
			current = current.next;
		}
	}
	
	private void removeDuplicates(Node n){
		Node current = n.next;
		
	}
	
	/** insert an item to the list */
	public void insert(int data){
		root = insert(root, data);
		size++;
	}
	
	private Node insert(Node n, int data){
		if(n == null){
			n = new Node(data);
		}else{
			n.next = insert(n.next, data);
		}
		return n;
	}
	
	/** remove an item from the list */
	public Node remove(int data){
		return remove(root, null, data);
	}
	
	private Node remove(Node n, Node prev, int data){
		if(n == null){
			System.err.println("The item to remove doesn't exist");
			return null;
		}else if(n.data == data){
			size--;
			if(n == root){
				Node ret = root;
				root = root.next;
				return ret;
			}else{
				prev.next = n.next;
				return n;
			}
		}else{
			return remove(n.next, n, data);
		}
	}
	
	/** Clear the list */
	public void clear(){
		root = null;
		size = 0;
	}
	
	/** Size of the list */
	public int size(){
		return size;
	}
	
	/** Is the list empty */
	public boolean isEmpty(){
		return (size == 0) ? true : false;
	}
	
	/** Print out the list */
	public void print(){
		print(root);
		System.out.println();
	}
	
	private void print(Node n){
		if(n != null){
			System.out.print(n.data + " ");
			print(n.next);
		}
	}
	
	public static void main(String[] args){
		int[] arr = {1, 5, 9, 2, 6, 1, 2, 5};
		RemoveDuplicates lst = new RemoveDuplicates();
		for(int i = 0; i < arr.length; i++){
			lst.insert(arr[i]);
		}
		System.out.println(lst.size());
		lst.removeDuplicates();
		lst.print();
	}
}