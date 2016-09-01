/**
	Author	: Tom Choi
	Date	: 09/01/2016
	
	Given a value x in a single linked list, partition the list in a way that
	all the values smaller than x is left to the nodes that are greater than
	or equal to x.
*/

public class Partition{
	private static class Node{
		private int data;
		private Node next;
		
		private Node(int data){
			this.data = data;
			this.next = null;
		}
	}
	
	private Node root;
	private int size;
	
	public Partition(){
		root = null;
		size = 0;
	}
	
	public void partition(int p){
		Node current = root;
		Node wall = root;
		while(current != null){
			if(current.data < p){
				int temp = current.data;
				current.data = wall.data;
				wall.data = temp;
				wall = wall.next;
			}
			current = current.next;
		}
	}
	
	/** insert an item into the list */
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
	
	/** remove an item from the list and return it */
	public int remove(int data){
		return remove(root, null, data);
	}
	
	private int remove(Node n, Node prev, int data){
		if(n == null){
			System.err.println("The item to remove doesn't exist");
			return -1;
		}else if(n.data == data){
			size--;
			int ret = n.data;
			prev.next = n.next;
			return ret;
		}else{
			return remove(n.next, n, data);
		}
	}
	
	/** prit out the list */
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
	
	/** clear the list */
	public void clear(){
		root = null;
		size = 0;
	}
	
	/** return the size of the list */
	public int size(){
		return size;
	}
	
	/** check if the list is empty or not */
	public boolean isEmpty(){
		return (size == 0) ? true : false;
	}
	
	public static void main(String[] args){
		Partition lst = new Partition();
		int[] arr = {3, 5, 8, 5, 10, 2, 1};
		for(int i = 0; i < arr.length; i++){
			lst.insert(arr[i]);
		}
		lst.print();
		lst.partition(5);
		lst.print();
	}
}