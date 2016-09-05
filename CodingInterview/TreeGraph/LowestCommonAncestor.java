/**
	Author	: Tom choi
	Date	: 09/05/0216
	
	Find the lowest common ancestors of two nodes in a binyar tree
	Note that three isn't necessarily a binary search tree.
	No additional data structure is allowed.
	
	Idea
		Start from the root of the tree
		See if both items belong to the left subtree or the right subtree of the root
		If both in the left  -> search the left subtree
		If both in the right -> search the right subtree
		If they are not in the same subtree -> they diverge on that node and LCA found
*/

import java.util.*;

public class LowestCommonAncestor<T extends Comparable<T>>{
	private static class Node<T>{
		private T data;
		private Node<T> left;
		private Node<T> right;
		
		private Node(T data){
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	
	private Node<T> root;
	private int size;
	private ArrayList<Node<T>> nodes;
	
	public LowestCommonAncestor(){
		this.size = 0;
		this.nodes = new ArrayList<Node<T>>();
	}
	
	/**
	* Uses the heap idea to find the parent node
	* This is to ensure that the tree gets filled by each level
	*/
	public void insert(T data){
		Node<T> node = new Node<T>(data);
		if(root == null){
			root = node;
		}else{
			int parent = (size-1)/2;
			Node<T> p = nodes.get(parent);
			
			// left child of the parent
			if(size % 2 == 1){
				p.left = node;
			}
			// right child of the parent
			else{
				p.right = node;
			}
		}
		nodes.add(node);
		size++;
	}
	
	/**
	* Find the data of the lowest common ancestor of two nodes
	*/
	public T LCA(T a, T b){
		if(!covers(root, a) || !covers(root, b)){
			return null;
		}
		Node<T> lca = LCA(root, a, b);
		return (lca == null) ? null : lca.data;
	}
	
	private Node<T> LCA(Node<T> node, T a, T b){
		if(node == null || a.compareTo(node.data) == 0 || b.compareTo(node.data) == 0){
			return node;
		}
		
		boolean aOnLeft = covers(node.left, a);
		boolean bOnLeft = covers(node.left, b);
		
		// the diverging point
		if(aOnLeft != bOnLeft){
			return node;
		}
		
		// find the subtree where both nodes belong to
		Node<T> childSide = aOnLeft ? node.left : node.right;
		return LCA(childSide, a, b);
	}
	
	/**
	* Check if the data exists in the subtree of the node
	*/
	private boolean covers(Node<T> node, T data){
		if(node == null){
			return false;
		}
		if(data.compareTo(node.data) == 0){
			return true;
		}else{
			return covers(node.left, data) || covers(node.right, data);
		}
	}
	
	/**
	* Traverse in left -> node -> right order
	*/
	public void inorder(){
		inorder(root);
		System.out.println();
	}
	
	private void inorder(Node<T> node){
		if(node != null){
			inorder(node.left);
			System.out.print(node.data + " ");
			inorder(node.right);
		}
	}
	
	/**
	* Traverse in node -> left -> right order
	*/
	public void preorder(){
		preorder(root);
		System.out.println();
	}
	
	private void preorder(Node<T> node){
		if(node != null){
			System.out.print(node.data + " ");
			preorder(node.left);
			preorder(node.right);
		}
	}
	
	/**
	* Traverse in left -> right -> node order
	*/
	public void postorder(){
		postorder(root);
		System.out.println();
	}
	
	private void postorder(Node<T> node){
		if(node != null){
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.data + " ");
		}
	}
	
	public static void main(String[] args){
		LowestCommonAncestor<String> lca = new LowestCommonAncestor<String>();
		String[] tom = new String[]{"Tom", "Choi", "The", "Greatest", "Googler", "In", "The", "World"};
		for(int i = 0; i < tom.length; i++){
			lca.insert(tom[i]);
		}
		lca.inorder();
		lca.preorder();
		System.out.println(lca.LCA("World", "Googler"));
	}
}