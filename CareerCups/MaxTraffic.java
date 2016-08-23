/**
	Author	: Tom Choi
	Date	: 08/17/2016
	
	Problem
	-> Given a graph with no cycles
	-> Each node represents different city
	
	Graph Representation
	-> adjacency matrix as follows
		{node1, node2, node3, ...}
		{{edge, edge, edge},
		 {edge, edge, edge},
		 ...}
		
	-> size is O(N + 2V), where
		N is # of nodes and V is # of edges
	
	Pseudocode											Efficiency
	-> Initialize the graph								O(N + 2V)
	-> For each node adjacent to a source				O(N)
		-> Find the sum of traffic within each branch	O(2V)
		-> Keep updating max traffic					O(1)
	-> Reset all nodes unvisited						O(N)
	-> Return the max									O(1)
	
	The total runtime of the algorithm is O(N + 2V) which is equivalent to O(N)
	unless there aren't many edges within the graph.
*/

public class MaxTraffic{
	/** A node class */
	private static class Node{
		private boolean visited;
		private int maxFlow;
		private int value;
		private Node[] edges;
		
		private Node(int value){
			this.value = value;
			edges = null;
			visited = false;
			maxFlow = 0;
		}
		
		/** defines the nodes that are adjacent to a node */
		private void setEdges(Node[] edges){
			this.edges = edges;
		}
	}
	
	/** The set of nodes */
	private Node[] nodes;
	
	/** The set of edges */
	private Node[][] edges;
	
	/** The source node in the graph */
	private Node source;
	
	/** Initialize a graph */
	public MaxTraffic(){
		initGraph();
	}
	
	/**
	* Shows all traffic within the graph
	*/
	public void showTraffic(){
		for(int i = 0; i < nodes.length; i++){
			System.out.println(nodes[i].value + ": " + findMaxFlow(nodes[i]));
		}
	}
	
	/**
	* Calculates the sum of flow for each node
	* adjacent to the source and returns the max flow
	*
	* @param	nodeNum		the index of the source node
	* @return	maximum traffic
	*/
	public int findMaxFlow(Node n){
		source = n;
		source.visited = true;
		int max = findMax(source.edges[0]);
		int temp = 0;
		
		for(int i = 1; i < source.edges.length; i++){
			temp = findMax(source.edges[i]);
			if(max < temp){
				max = temp;
			}
		}
		resetVisited();
		return max;
	}
	
	/**
	* Adds all traffic of a subtree
	*
	* @param	node	sub-root
	* @return	sum of traffic within the sub-root
	*/
	private int findMax(Node node){
		node.visited = true;
		if(allVisited(node)){
			return node.value;
		}else{
			int sum = node.value;
			for(int i = 0; i < node.edges.length; i++){
				Node adjNode = node.edges[i];
				if(adjNode.visited == false){
					sum += findMax(adjNode);
				}
			}
			return sum;
		}
	}
	
	/**
	* See if all of the adjacent nodes are visited
	*
	* @param	node	node to check for its adjacent nodes
	* @return	true if all adjacent nodes visited; otherwise, false
	*/
	private boolean allVisited(Node node){
		for(int i = 0; i < node.edges.length; i++){
			if(node.edges[i].visited == false){
				return false;
			}
		}return true;
	}
	
	/**
	* Once finding max traffic is finished,
	* reset all the nodes unvisited
	*/
	private void resetVisited(){
		for(int i = 0; i < nodes.length; i++){
			nodes[i].visited = false;
		}
	}
	
	/**
	* Creates a graph
	*/
	private void initGraph(){
		// create nodes
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(7);
		Node n7 = new Node(15);
		this.nodes = new Node[]{n1,n2,n3,n4,n5,n6,n7};
		
		// edges for each node
		this.edges =
			new Node[][]{{n5}, {n5,n6,n7}, {n5}, {n5},
						{n1,n2,n3,n4}, {n2}, {n2}};
									  
		for(int i = 0; i < nodes.length; i++){
			nodes[i].setEdges(edges[i]);
		}
	}
	
	/**
	* Test code
	*/
	public static void main(String[] args){
		MaxTraffic mt = new MaxTraffic();
		mt.showTraffic();
	}
}