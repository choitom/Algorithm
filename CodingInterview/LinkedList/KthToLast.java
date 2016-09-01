/**
	Author	: Tom Choi
	Date	: 08/31/2016
	
	Return kth element to last element of a singly linked list.
*/

public class KthToLast{
	private static class Node{
		private int data;
		private Node next;
		private Node prev;
		private Node(int data){
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}
	
	private Node root;
	private Node last;
	private int size;
	
	public KthToLast(){
		root = null;
		last = null;
		size = 0;
	}
	
	/** returns the data of the node that is Kth nodes away from the last*/
	public int kThToLast(int k){
		return kThToLast(last, k);
	}
	
	private int kThToLast(Node last, int k){
		if(k == 0){
			return last.data;
		}else{
			return kThToLast(last.prev, k-1);
		}
	}
	
	/** recursively insert an item into the doubly linked list */
	public void insert(int data){
		root = insert(root, data);
		size++;
	}
	
	private Node insert(Node n, int data){
		if(n == null){
			n = new Node(data);
			n.prev = last;
			last = n;
		}else{
			n.next = insert(n.next, data);
		}
		return n;
	}
	
	/** recursively remove an item from the doubly linked list */
	public int remove(int data){
		return remove(root, data);
	}
	
	private int remove(Node n, int data){
		if(n == null){
			System.err.println("The item to delete doesn't exist");
			return -1;
		}else if(n.data == data){
			size--;
			int ret = n.data;
			n.prev.next = n.next;
			n.next.prev = n.prev;
			return ret;
		}else{
			return remove(n.next, data);
		}
	}
	
	/** clear the list */
	public void clear(){
		this.root = null;
		this.last = null;
		this.size = 0;
	}
	
	/** return the size of the list */
	public int size(){
		return size;
	}
	
	/** is ths list empty */
	public boolean isEmpty(){
		return (size == 0) ? true : false;
	}
	
	/** print from beg to end */
	public void printForward(){
		printForward(root);
		System.out.println();
	}
	
	private void printForward(Node n){
		if(n != null){
			System.out.print(n.data + " ");
			printForward(n.next);
		}
	}
	
	/** print from end to beg */
	public void printBackward(){
		printBackward(last);
		System.out.println();
	}
	
	private void printBackward(Node n){
		if(n != null){
			System.out.print(n.data + " ");
			printBackward(n.prev);
		}
	}
	
	public static void main(String[] args){
		KthToLast lst = new KthToLast();
		int[] arr = {1, 5, 3, 8, 7, 4, 1, 10, 9, 76, 53};
		for(int i = 0; i < arr.length; i++){
			lst.insert(arr[i]);
		}
		lst.printBackward();
		System.out.println(lst.remove(10));
		lst.printBackward();
		System.out.println(lst.kThToLast(5));
	}
}