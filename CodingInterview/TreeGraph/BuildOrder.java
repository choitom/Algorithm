/**
	Author	: Tom Choi
	Date	: 09/04/2016
	
	You are given a list of projects and a list of dependencies (which is a list of
	pairs of projects, where the second project is dependent on the first project).
	All of a project's dependencies mus be built before the project is.
	Find the build order that will allow the projects to be built.
	
	Strategy
		It is asking for the topological ordering of the graph.
		
		1. Build the graph first and find the nodes with no incoming edges.
		2. Add it to the output and delete those nodes.
		3. Update the number of incoming edges of its adjacent nodes
		4. Repeat until no nodes are left.
*/

import java.io.*;
import java.util.*;

public class BuildOrder{
	private static class Node{
		String name;
		LinkedList<Node> edges;
		private Node(String name){
			this.name = name;
			this.edges = new LinkedList<Node>();
		}
	}
	
	private HashMap<Node,Integer> graph;
	private ArrayList<Node> order;
	
	public BuildOrder(HashMap<Node,Integer> graph){
		this.graph = graph;
		this.order = new ArrayList<Node>();
	}
	
	public void buildOrder(){
		HashMap<Node,Integer> copy = new HashMap<Node,Integer>();
		for(Node n : graph.keySet()){
			copy.put(n, graph.get(n));
		}
		
		/**
		* Remove the nodes with zero incoming edge.
		* If there doesn't exist any node with zero incoming edge,
		* then there must exist a cycle.
		*/
		while(!copy.isEmpty() && hasZeroIncoming(copy)){
			ArrayList<Node> lst = noIncomingNodes(copy);
			for(Node n : lst){
				copy.remove(n);
				order.add(n);
				LinkedList<Node> e = n.edges;
				if(e != null){
					for(Node adj : e){
						copy.put(adj, copy.get(adj) - 1);
					}
				}
			}
		}
		if(!copy.isEmpty()){
			System.out.println("There exists a cycle");
		}else{
			for(int i = 0; i < order.size(); i++){
				System.out.print(order.get(i).name + " ");
			}System.out.println();	
		}		
	}
	
	private boolean hasZeroIncoming(HashMap<Node,Integer> map){
		for(Node n : map.keySet()){
			if(map.get(n) == 0){
				return true;
			} 
		}
		return false;
	}
	
	private ArrayList<Node> noIncomingNodes(HashMap<Node,Integer> map){
		ArrayList<Node> ret = new ArrayList<Node>();
		for(Node n : map.keySet()){
			if(map.get(n) == 0){
				ret.add(n);
			}
		}
		return ret;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("ordering.txt");
		Scanner scan = new Scanner(file);
		int numNodes = scan.nextInt();
		int numEdges = scan.nextInt();
		
		// pair of the name of the node and the actual node
		HashMap<String, Node> nodes = new HashMap<String, Node>();
		
		// pair of a node and the number of incoming edges
		HashMap<Node, Integer> incoming = new HashMap<Node, Integer>();
		
		for(int i = 0; i < numNodes; i++){
			String name = scan.next();
			Node n = new Node(name);
			nodes.put(name, n);
			incoming.put(n, 0);
		}
		
		for(int i = 0; i < numEdges; i++){
			String src = scan.next();
			String dest = scan.next();
			
			Node srcNode = nodes.get(src);
			Node destNode = nodes.get(dest);
			
			//add the directed edge
			srcNode.edges.add(destNode);
			
			//update incoming edge
			incoming.put(destNode, incoming.get(destNode) + 1);
		}
		
		BuildOrder order = new BuildOrder(incoming);
		order.buildOrder();
	}
}