/**
	Author	: Tom Choi
	Date	: 09/02/2016
	
	Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth
	(e.g. if you have a tree with depth D, you will have D linked lists)
	
	Strategy
		Apply the same principle as BFS.
		
	Note
		I am re-implementing AVL Tree for practice
*/
import java.util.*;

public class ListOfDepths{
	private static class Node{
		private int data;
		private int height;
		private Node left;
		private Node right;
		private Node(int data){
			this.data = data;
			this.height = 0;
			this.left = null;
			this.right = null;
		}
	}
	
	private Node root;
	private int removed;
	private int size;
	private ArrayList<LinkedList<Integer>> listOfDepths;
	
	public ListOfDepths(){
		this.root = null;
		this.removed = 0;
		this.size = 0;
		this.listOfDepths = new ArrayList<LinkedList<Integer>>();
	}
	
	/**
	* Returns the linked lists of all nodes for each depth
	*/
	public ArrayList<LinkedList<Integer>> listOfDepths(){
		listOfDepths(root, 0);
		return listOfDepths;
	}
	
	private void listOfDepths(Node node, int depth){
		if(node != null){
			LinkedList<Integer> lst;
			if(listOfDepths.size() == depth){
				lst = new LinkedList<Integer>();
				listOfDepths.add(lst);
			}else{
				lst = listOfDepths.get(depth);
			}
			lst.add(node.data);
			listOfDepths(node.left, depth + 1);
			listOfDepths(node.right, depth + 1);
		}		
	}
	
	/**
	* Insert an item into the tree
	*/
	public void insert(int data){
		root = insert(root, data);
	}
	
	private Node insert(Node node, int data){
		if(node == null){
			node = new Node(data);
		}else if(data < node.data){
			node.left = insert(node.left, data);
		}else if(data > node.data){
			node.right = insert(node.right, data);
		}else{
			return node;
		}
		size++;
		return balanceHeight(node);
	}
	
	/**
	* Removes an item from the tree and return it
	*/
	public int remove(int data){
		root = remove(root, data);
		return removed;
	}
	
	private Node remove(Node node, int data){
		if(node == null){
			System.err.println("The item to remove doesn't exist in the tree");
			return null;
		}
		if(data < node.data){
			node.left = remove(node.left, data);
		}else if(data > node.data){
			node.right = remove(node.right, data);
		}else{
			removed = node.data;
			size--;
			if(node.left != null && node.right != null){
				node.data = leftRightmost(node.left);
				node.left = remove(node.left, node.data);
			}else if(node.left == null && node.right == null){
				return null;
			}else if(node.left != null && node.right == null){
				node = node.left;
			}else{
				node = node.right;
			}
		}
		return balanceHeight(node);
	}
	
	private int leftRightmost(Node node){
		while(node.right != null){
			node = node.right;
		}
		return node.data;
	}
	
	/**
	* Balances the height of the tree
	*/
	private Node balanceHeight(Node node){
		// left imbalance
		if(height(node.left) - height(node.right) > 1){
			// rr rotation
			if(height(node.left.left) > height(node.left.right)){
				node = rightRightRotation(node);
			}
			// lr rotation
			else{
				node = leftRightRotation(node);
			}
		}
		// right imbalance
		else if(height(node.right) - height(node.left) > 1){
			// ll rotation
			if(height(node.right.right) > height(node.right.left)){
				node = leftLeftRotation(node);
			}
			// rl rotation
			else{
				node = rightLeftRotation(node);
			}
		}
		node.height = max(height(node.left), height(node.right)) + 1;
		return node;
	}
	
	private Node rightRightRotation(Node node){
		Node temp = node.left;
		node.left = temp.right;
		temp.right = node;
		node.height = max(height(node.left), height(node.right)) + 1;
		temp.height = max(height(temp.left), height(temp.right)) + 1;
		return temp;
	}
	
	private Node leftLeftRotation(Node node){
		Node temp = node.right;
		node.right = temp.left;
		temp.left = node;
		node.height = max(height(node.left), height(node.right)) + 1;
		temp.height = max(height(temp.left), height(temp.right)) + 1;
		return temp;
	}
	
	private Node leftRightRotation(Node node){
		node.left = leftLeftRotation(node.left);
		return rightRightRotation(node);
	}
	
	private Node rightLeftRotation(Node node){
		node.right = rightRightRotation(node.right);
		return leftLeftRotation(node);
		
	}
	
	private int height(Node node){
		return (node != null) ? node.height : 0;
	}
	
	private int max(int i, int j){
		return (i >= j) ? i : j;
	}
	
	/**
	* Traverses the tree in left -> node -> right order
	*/
	public void inorder(){
		inorder(root);
		System.out.println();
	}
	
	private void inorder(Node node){
		if(node != null){
			inorder(node.left);
			System.out.print(node.data + " ");
			inorder(node.right);
		}
	}
	
	/**
	* Traverses the tree in node -> left -> right order
	*/
	public void preorder(){
		preorder(root);
		System.out.println();
	}
	
	private void preorder(Node node){
		if(node != null){
			System.out.print(node.data + " ");
			preorder(node.left);
			preorder(node.right);
		}
	}
	
	/**
	* Traverses the tree in left -> right -> node order
	*/
	public void postorder(){
		postorder(root);
		System.out.println();
	}
	
	private void postorder(Node node){
		if(node != null){
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.data + " ");
		}
	}
	
	/**
	* Returns the height of the tree
	*/
	public int treeHeight(){
		if(root == null){
			System.err.println("The tree is empty");
			return -1;
		}
		return root.height;
	}
	
	/**
	* Returns the number of the nodes in the tree
	*/
	public int size(){
		return size;
	}
	
	public static void main(String[] args){
		ListOfDepths tree = new ListOfDepths();
		int[] arr = {10, 5, 15, 17};
		for(int i = 0; i < arr.length; i++){
			tree.insert(arr[i]);
		}
		System.out.println(tree.treeHeight());
		tree.preorder();
		
		
		ArrayList<LinkedList<Integer>> listOfDepths = tree.listOfDepths();
		for(int i = 0; i < listOfDepths.size(); i++){
			System.out.print("Depth(" + i + "): ");
			for(int j : listOfDepths.get(i)){
				System.out.print(j + " ");
			}System.out.println();
		}
	}
}