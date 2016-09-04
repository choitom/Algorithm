/**
	Author	: Tom Choi
	Date	: 09/04/0216
	
	Implement a function to check if a binary tree is a binary search tree
	
	Strategy
		-> store each node by inorder traversal
		-> compare the values
*/

import java.util.*;

public class IsBST{
	private class Node{
		private int data;
		private Node left;
		private Node right;
		private Node(int data){
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	
	private Node root;
	
	public IsBST(){
		this.root = null;
	}
	
	/**
	* Check if the binary tree is BST
	*/
	public boolean isBST(){
		ArrayList<Integer> inorderList = new ArrayList<Integer>();
		isBST(root, inorderList);
		
		for(int i = 1; i < inorderList.size(); i++){
			if(inorderList.get(i) < inorderList.get(i-1)){
				return false;
			}
		}
		return true;
	}
	
	public void isBST(Node node, ArrayList<Integer> lst){
		if(node != null){
			isBST(node.left, lst);
			lst.add(node.data);
			isBST(node.right, lst);
		}
	}
	
	/**
	* Randomly insert items to the tree
	*/
	public void insert(int data){
		root = insert(root, data);
	}
	
	private Node insert(Node node, int data){
		Random rand = new Random();
		int r = rand.nextInt(2);
		if(node == null){
			node = new Node(data);
		}else if(r == 0){
			node.left = insert(node.left, data);
		}else if(r == 1){
			node.right = insert(node.right, data);
		}
		return node;
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
	
	public static void main(String[] args){
		IsBST tree = new IsBST();
		int[] n = {7, 4, 10, 9, 99, 15, 6};
		for(int i = 0; i < n.length; i++){
			tree.insert(n[i]);
		}
		System.out.println(tree.isBST());
	}
}