/**
	Author	: Tom Choi
	Date	: 09/04/2016
	
	Implement a function to check if a tree is balanced or not
	
	Strategy
		-> initialize a global variable for checking the balance of the tree
		-> keep updating the value while inserting or deleting a node
*/

public class IsBalancedTree{
	private static class Node{
		private int data;
		private int height;
		private Node left;
		private Node right;
		private Node(int data){
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	
	private Node root;
	private int removed;
	private boolean balanced;
	
	public IsBalancedTree(){
		root = null;
		balanced = true;
		removed = 0;
	}
	
	
	public boolean isBalanced(){
		return balanced;
	}
	
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
		}else{
			return node;
		}
		node.height = max(height(node.left), height(node.right)) + 1;
		if(Math.abs(height(node.left)-height(node.right)) > 1){
			balanced = false;
		}else{
			balanced = true;
		}
		return node;
	}
	
	public int delete(int data){
		root = remove(root, data);
		return removed;
	}
	
	public void inorder(){
		inorder(root);
		System.out.println();
	}
	
	private void inorder(Node root){
		if(root != null){
			inorder(root.left);
			System.out.print(root.data + " ");
			inorder(root.right);
		}
	}
	
	private Node remove(Node node, int data){
		if(node == null){
			System.err.println("The item to remove doesn't exist");
		}else if(data < node.data){
			node.left = remove(node.left, data);
		}else if(data > node.data){
			node.right = remove(node.right, data);
		}else{
			if(node.left != null && node.right != null){
				node.data = leftRightmost(node.left);
				node.left = remove(node.left, data);
			}else{
				return(node.left != null) ? node.left : node.right;
			}
		}
		node.height = max(height(node.left), height(node.right))+1;
		if(Math.abs(height(node.left) - height(node.right)) > 1){
			balanced = false;
		}else{
			balanced = true;
		}
		return node;
	}
	
	private int leftRightmost(Node node){
		while(node.right != null){
			node = node.right;
		}
		return node.data;
	}
	
	private int max(int i, int j){
		return (i >= j) ? i : j;
	}
	
	private int height(Node node){
		return (node != null) ? node.height : 0;
	}
	
	public static void main(String[] args){
		IsBalancedTree tree = new IsBalancedTree();
		int[] n = {10,5,15,20,25};
		for(int i = 0; i < n.length; i++){
			tree.insert(n[i]);
		}
		System.out.println(tree.isBalanced()); // false
		tree.delete(25);
		tree.inorder();
		System.out.println(tree.isBalanced()); // true
	}
}