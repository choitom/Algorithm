/**
	Author	: Tom Choi
	Date	: 09/02/2016
	
	Given a sorted array with unique integer elements,
	write an algorithm to create a binary search tree with minimal height
	
	Minimal height can be maintained by AVL Tree.
	It ensure O(logn) time for searching through the tree
*/

public class MinimumHeightTree{
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
	
	// root of the tree
	private Node root;
	private int removed;
	
	// cosntructor
	public MinimumHeightTree(int[] arr){
		for(int i = 0; i < arr.length; i++){
			insert(arr[i]);
		}
	}
	
	// insert an integer into the tree
	public void insert(int data){
		root = insert(root, data);
	}
	
	// remove an integer from the tree
	public int remove(int data){
		root = remove(root, data);
		return removed;
	}
	
	// returns the height of the tree
	public int treeHeight(){
		return root.height;
	}
	
	// traverse left child -> node -> right child order
	public void inorderTraverse(){
		inorder(root);
		System.out.println();
	}
	
	// recursively find the node to delete
	private Node remove(Node node, int data){
		if(node == null){
			System.err.println("The item to remove doesn't exist in the tree: " + data);
			return null;
		}
		// search right
		if(node.data < data){
			node.right = remove(node.right, data);
		}
		// search left
		else if(node.data > data){
			node.left = remove(node.left, data);
		}
		// found
		else{
			removed = data;
			if(node.left != null && node.right != null){
				node.data = leftRightmost(node.left);
				node.left = remove(node.left, data);
			}else{
				return (node.left != null)? node.left : node.right;
			}
		}
		return balance(node);
	}
	
	private int leftRightmost(Node left){
		Node current = left;
		Node prev = null;
		while(current.right != null){
			prev = current;
			current = current.right;
		}
		int ret = current.data;
		prev.right = current.left;
		return ret;
	}
	
	// recursively find the place to insert the data
	private Node insert(Node node, int data){
		if(node == null){
			node = new Node(data);
		}else if(node.data == data){
			return node;
		}else if(node.data < data){
			node.right = insert(node.right, data);
		}else{
			node.left = insert(node.left, data);
		}
		return balance(node);
	}
	
	// balances the height of the tree
	private Node balance(Node node){
		if(node != null){
			// left imbalance
			if(height(node.left) - height(node.right) > 1){
				// rr rotation
				if(height(node.left.left) >= height(node.left.right)){
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
				if(height(node.right.right) >= height(node.right.left)){
					node = leftLeftRotation(node);
				}
				// rl rotation
				else{
					node = rightLeftRotation(node);
				}
			}
			node.height = max(height(node.left), height(node.right)) + 1;				
		}
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
	
	private int max(int i, int j){
		return (i >= j) ? i : j;
	}
	
	private int height(Node n){
		return (n == null) ? 0 : n.height;
	}
	
	private void inorder(Node node){
		if(node != null){
			inorder(node.left);
			System.out.print(node.data + " ");
			inorder(node.right);
		}
	}
	
	public static void main(String[] args){
		int[] arr = new int[50];
		for(int i = 0; i < arr.length; i++){
			arr[i] = i;
		}
		MinimumHeightTree tree = new MinimumHeightTree(arr);
		System.out.println(tree.treeHeight());
		tree.inorderTraverse();
		
		tree.remove(0);
		tree.remove(1);
		tree.remove(2);
		tree.inorderTraverse();
	}
}