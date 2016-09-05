/**
	Author	: Tom Choi
	Date	: 09/05/2016
	
	A binary search tree was created by traversing through an array from left to right
	and inserting each element. Given a binary search tree with distinct elements,
	print all possible arrays that could have led to this tree.
	
	Observation
		-> Level of the tree should be kept
		-> Permutations of the nodes within the same level are allowed
*/

import java.util.*;

public class BSTSequences{
	private static class Node{
		private int data;
		private Node left;
		private Node right;
		private Node(int data){
			this.data = data;
		}
	}
	
	private Node root;
	private ArrayList<String> sequences;
	
	public BSTSequences(){
		this.root = null;
		this.sequences = new ArrayList<String>();
	}
	
	/**
	* Find all possible sequences of the array that could have led to this BST
	*/
	public ArrayList<String> findSequences(){
		ArrayList<Integer> listOfDepths = listOfDepths(root);
		ArrayList<LinkedList<Integer>> separated = separeteDepths(listOfDepths);
		findAllPermutations(separated);
		return sequences;
	}
	
	private void findAllPermutations(ArrayList<LinkedList<Integer>> separated){
		// stores all permutations for each height
		ArrayList<ArrayList<ArrayList<Integer>>> perms = new ArrayList<ArrayList<ArrayList<Integer>>>();
		for(int i = 0; i < separated.size(); i++){
			ArrayList<ArrayList<Integer>> p = findPerm(separated.get(i));
			perms.add(p);
		}
		findResults(perms, 0, "");
	}
	
	private void findResults(ArrayList<ArrayList<ArrayList<Integer>>> perms, int index, String partial){
		if(index == perms.size()){
			sequences.add(partial);
		}else{
			ArrayList<ArrayList<Integer>> lst = perms.get(index);
			for(int i = 0; i < lst.size(); i++){
				String seq = "";
				for(int j = 0; j < lst.get(i).size(); j++){
					seq += String.valueOf(lst.get(i).get(j) + " ");
				}
				findResults(perms, index + 1, partial + seq);
			}
		}
	}
	
	private ArrayList<ArrayList<Integer>> findPerm(LinkedList<Integer> lst){
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		result.add(new ArrayList<Integer>());
		
		for(int i = 0; i < lst.size(); i++){
			ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();
			for(ArrayList<Integer> I : result){
				for(int j = 0; j < I.size() + 1; j++){
					I.add(j, lst.get(i));
					ArrayList<Integer> temp = new ArrayList<Integer>(I);
					current.add(temp);
					I.remove(j);
				}
			}
			result = new ArrayList<ArrayList<Integer>>(current);
		}
		return result;
	}
	
	/**
	* Separate the nodes by levels
	*/
	private ArrayList<LinkedList<Integer>> separeteDepths(ArrayList<Integer> listOfDepths){
		ArrayList<LinkedList<Integer>> separated = new ArrayList<LinkedList<Integer>>();
		
		// the array for storing the height of the tree for each index
		int[] levels = new int[listOfDepths.size()];
		levels[0] = 0;
		for(int i = 1; i < levels.length; i++){
			int parent = (i-1)/2;
			levels[i] = levels[parent] + 1;
		}
		
		// separate each height of the tree
		for(int i = 0; i < listOfDepths.size(); i++){
			LinkedList<Integer> lst;
			if(separated.size() == levels[i]){
				lst = new LinkedList<Integer>();
				separated.add(lst);
			}else{
				lst = separated.get(levels[i]);
			}
			lst.add(listOfDepths.get(i));
		}
		
		for(int i = 0; i < separated.size(); i++){
			LinkedList<Integer> lst = separated.get(i);
			for(int j : lst){
				System.out.print(j + " ");
			}
			System.out.println();
		}
		
		return separated;
	}
	
	/**
	* Find the list of nodes searched by each level using the BFS algorithm
	*/
	private ArrayList<Integer> listOfDepths(Node node){
		ArrayList<Integer> lst = new ArrayList<Integer>();
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);
		int height = 0;
		while(!queue.isEmpty()){
			Node polled = queue.poll();
			lst.add(polled.data);
			if(polled.left != null){
				queue.add(polled.left);
			}
			if(polled.right != null){
				queue.add(polled.right);
			}
		}
		return lst;
	}
	
	/**
	* Insert an item into the binary search tree
	*/
	public void insert(int data){
		root = insert(root, data);
	}
	
	private Node insert(Node node, int data){
		if(node == null){
			node = new Node(data);
		}else if(node.data > data){
			node.left = insert(node.left, data);
		}else if(node.data < data){
			node.right = insert(node.right, data);
		}
		return node;
	}
	
	/**
	* Preorder traversal (node -> left -> right)
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
	
	public static void main(String[] args){
		BSTSequences bst = new BSTSequences();
		int[] arr = new int[]{10, 5, 15, 4, 6, 14, 16};
		for(int i = 0; i < arr.length; i++){
			bst.insert(arr[i]);
		}
		bst.preorder();
		ArrayList<String> bstSequences = bst.findSequences();
		for(int i = 0; i < bstSequences.size(); i++){
			System.out.println(i + ": " + bstSequences.get(i));
		}
	}
}