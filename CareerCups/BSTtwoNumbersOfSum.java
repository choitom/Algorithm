/**
	Author	: Tom Choi
	Date	: 08/24/2016
	
	Problem
		Given a binary search tree and an integer n,
		Find two numbers in the tree that sums up to n.
		
	Pseudocode
		For each # in the tree				O(n)
			find diff where diff = n - #	O(1)
			see if diff exists in the tree	O(logn)
		
		total runtime: O(nlong)
	
	Note
		For the sake of how to solve the problem,
		I implemented a very crude BST only with
		insert and search functions.
*/

import java.util.*;

public class BSTtwoNumbersOfSum{
	private static class Node{
		int item;
		Node left;
		Node right;
		
		private Node(int item){
			this.item = item;
			this.left = null;
			this.right = null;
		}
	}
	
	private Node root;
	private int size;
	private ArrayList<Integer> nums;
	private final int MAX = Integer.MAX_VALUE;
	
	public BSTtwoNumbersOfSum(){
		this.root = null;
		this.size = 0;
		this.nums = new ArrayList<Integer>();
	}
	
	/**
	* Given an integer, finds the two numbers that add up to n in the tree
	*/
	public void twoNumbersOfSum(int n){
		/** generate a list of numbers in the tree */
		searchNumbers();
		
		/** prevents duplicates */
		HashSet<Integer> foundSet = new HashSet<Integer>();
		
		boolean result = false;
		for(int i = 0; i < nums.size(); i++){
			int diff = n - nums.get(i);
			if(search(diff) != MAX && !foundSet.contains(diff)){
				System.out.println(nums.get(i) + " + " + diff + " = " + n);
				foundSet.add(diff);
				foundSet.add(nums.get(i));
				result = true;
			}
		}
		if(!result){
			System.out.println("THe two numbers that sum up to " + n + " doesn't exist.");
		}
	}
	
	/**
	* Insert an integer into the tree
	*/
	public void insert(int n){
		root = insert(root, n);
		size++;
	}
	
	private Node insert(Node node, int n){
		if(node == null){
			return new Node(n);
		}
		if(n < node.item){
			node.left = insert(node.left, n);
		}else if(n > node.item){
			node.right = insert(node.right, n);
		}
		return node;
	}
	
	/**
	* See if an integer exists in BST
	*/
	public int search(int n){
		return searchHelper(root, n);
	}
	
	private int searchHelper(Node node, int n){
		if(node == null){
			return MAX;
		}
		if(n == node.item){
			return n;
		}else if(n < node.item){
			return searchHelper(node.left, n);
		}else{
			return searchHelper(node.right, n);
		}
	}
	
	/**
	* Search the numbers in the tree (left -> node -> right)
	*/
	public void searchNumbers(){
		inorder(root);
	}
	
	private void inorder(Node node){
		if(node != null){
			inorder(node.left);
			nums.add(node.item);
			inorder(node.right);
		}
	}
	
	/** Test code */
	public static void main(String[] args){
		BSTtwoNumbersOfSum bst = new BSTtwoNumbersOfSum();
		int[] arr = new int[]{7,4,10,3,6,9,11};
		for(int i = 0; i < arr.length; i++){
			bst.insert(arr[i]);
		}
		bst.twoNumbersOfSum(15);
	}
}