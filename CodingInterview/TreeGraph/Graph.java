/**
	Author	: Tom Choi
	Date	: 09/02/2016
	
	Using bidrectional search, see if there exists a path between two nodes.
	
	Initiailize two queues one for forward, the other for backward.
	if nodes from forward and backward are equal or cross, then path exists.
*/

import java.util.*;
import java.io.*;

public class Graph{
	private static class Node{
		private String name;
		private boolean visited;
		private HashSet<Node> edges;
		private Node(String name){
			this.name = name;
			this.edges = new HashSet<Node>();
		}
	}
	
	private HashSet<Node> nodeSet;
	private HashMap<String, Node> graph;
	
	public Graph(Scanner file){
		this.nodeSet = new HashSet<Node>();
		this.graph = new HashMap<String, Node>();
		initGraph(file);
	}
	
	public void printGraph(){
		for(Node n : nodeSet){
			System.out.print(n.name + ": ");
			if(n.edges != null){
				for(Node adj : n.edges){
					System.out.print(adj.name + " ");
				}
			}
			System.out.println();
		}
	}
	
	public boolean isPath(String src, String dest){
		boolean path = false;
		if(!graph.containsKey(src) || !graph.containsKey(dest)){
			System.err.println("Check the source and target nodes.");
			return false;
		}
		if(src.equals(dest)){
			return true;
		}
		
		Queue<Node> queueForward = new LinkedList<Node>();
		Queue<Node> queueBackward = new LinkedList<Node>();
		queueForward.add(graph.get(src));
		queueBackward.add(graph.get(dest));
		
		while(!queueForward.isEmpty() && !queueBackward.isEmpty()){
			Node front = queueForward.poll();
			Node back = queueBackward.poll();
			
			front.visited = true;
			back.visited = true;
			
			if(front == back){
				path = true;
				break;
			}
			
			if(front.edges.contains(back) && back.visited == true &&
			   back.edges.contains(front) && front.visited == true){
				path = true;
				break;
			}else{
				for(Node n : front.edges){
					queueForward.add(n);
				}
				for(Node n : back.edges){
					queueBackward.add(n);
				}
			}
		}
		resetVisited();
		return path;
	}
	
	private void resetVisited(){
		for(Node n : nodeSet){
			n.visited = false;
		}
	}
	
	private void initGraph(Scanner file){
		int numNodes = file.nextInt();
		int numEdges = file.nextInt();
		for(int i = 0; i < numNodes; i++){
			String nodeName = file.next();
			Node n = new Node(nodeName);
			nodeSet.add(n);
			graph.put(nodeName, n);
		}
		
		for(int i = 0; i < numEdges; i++){
			String src = file.next();
			String dest = file.next();
			Node srcNode = graph.get(src);
			Node destNode = graph.get(dest);
			srcNode.edges.add(destNode);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("graph.txt");
		Scanner scan = new Scanner(file);
		Graph g = new Graph(scan);
		g.printGraph();
		System.out.println(g.isPath("A", "D"));
	}
}