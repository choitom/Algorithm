/**
	Author	: Tom Choi
	Date	: 08/31/2016
	
	Problem from Careercup.com
	
	A company is looking for algorithm to show item recommendations.
	If a customer buys items A and B, and another buys item A,
	then item B should appear as a rocommendation.
	
	There are two types of recommendations based on the connections.
		1) Strongly connected if a customer buys both items.
		2) Weakly connected if each item is strongly/weakly connected
		   to a third item.
	Find the number of strong and weak connections given a product code.
	
	For example
		person		product code
		1st 	: ABC, HIJ, DEF
		2nd 	: HIJ, KLM
		3rd 	: NOP
		4th 	: ABC, QRS
		5th 	: KLM, TUV
		
		Input : ABC
		Strong connections	: HIJ, DEF, QRS
		Weak connections	: KLM, TUV
		
	Observation
		1.  Suppose that each product is a node. Then, strongly connected
			items are the ones that are directly connected by edges to a node.
		2.  Weakly connected items are the ones that are indirectly connected
			to a node via its strongly connected nodes.
			
	Strategy
		1. Create an undirected graph for each product.
		2. Mark the nodes directly connected to the input as strongly connected.
		3. Run the breath first search and find weakly connected nodes.
		
	Runtime
		* Suppose we have K number of customers and N number of distinct items
		  and V number of edges in the graph.
		Creating a graph
			Reading item lists for each person		-> O(K)
			Creating adjacency list of n items		-> O(N + 2V)
			TOTAL => O(K * (N + 2V))
		Strong connection
			Search HashMap to find connected nodes	-> O(1)
		Weak connection
			Breath First Search through the graph	-> O(N + 2V)
		
		Overall runtime = O(K * (N + 2V))
*/

import java.util.*;
import java.io.*;
	
public class ProductRecommendation{
	private HashSet<String> itemSet;
	private HashMap<String, LinkedList<String>> map;
	
	public ProductRecommendation(ArrayList<String> itemLst, HashSet<String> items){
		this.itemSet = items;
		initGraph(itemLst);
	}
	
	/**
	* Generate the adjacency list given a list of each customer's item purchase list
	*/
	private void initGraph(ArrayList<String> itemLst){
		map = new HashMap<String, LinkedList<String>>();
		
		// item list by each person
		for(int i = 0; i < itemLst.size(); i++){
			String[] delimited = itemLst.get(i).split(" ");
			
			// for each item in the list
			for(int j = 0; j < delimited.length; j++){
				
				LinkedList<String> adj;
				String key = delimited[j];
				if(map.containsKey(key)){
					adj = map.get(key);
				}else{
					adj = new LinkedList<String>();
				}
				
				for(int k = 0; k < delimited.length; k++){
					if(!delimited[k].equals(key)){
						adj.add(delimited[k]);
					}
				}
				map.put(key, adj);
			}
		}
	}
	
	/**
	* Find strong and weak connections of a given product
	*/
	public void findConnections(String input){
		LinkedList<String> strong = map.get(input);
		HashSet<String> weak = findWeakConnection(input, strong);
		printResult(strong, weak);
	}
	
	/**
	* Find weak connections by running BFS algorithm
	*/
	private HashSet<String> findWeakConnection(String in, LinkedList<String> strong){
		// deep copy items to a new hash set
		HashSet<String> visited = new HashSet<String>();
		for(String key : itemSet){
			visited.add(key);
		}
		HashSet<String> visitedSet = new HashSet<String>();
		
		Queue<String> queue = new LinkedList<String>();
		queue.add(in);
		
		// BFS
		while(!queue.isEmpty()){
			String polled = queue.poll();
			visited.remove(polled);		// mark as visited
			visitedSet.add(polled);		// add to the visited set
			LinkedList<String> adj = map.get(polled);
			
			if(adj != null){
				for(String node : adj){
					if(visited.contains(node)){
						queue.add(node);
					}
				}
			}
		}
		
		// remove the source and strongly connected nodes
		visitedSet.remove(in);
		for(String str : strong){
			visitedSet.remove(str);
		}
		return visitedSet;
	}
	
	private void printResult(LinkedList<String> strong, HashSet<String> weak){
		System.out.print("Strong Connections: ");
		if(strong != null){
			for(String str : strong){
				System.out.print(str + " ");
			}
		}else{
			System.out.print("None");
		}System.out.println();
		
		System.out.print("Weak Connections: ");
		if(weak != null){
			for(String wk : weak){
				System.out.print(wk + " ");
			}
		}else{
			System.out.print("None");
		}System.out.println();
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("products.txt");
		Scanner scan = new Scanner(file);
		
		// the list of items that each person bought
		ArrayList<String> itemLst = new ArrayList<String>();
		
		// the distinct item
		HashSet<String> items = new HashSet<String>();
		
		while(scan.hasNext()){
			String it = scan.nextLine();
			itemLst.add(it);
			String[] delimited = it.split(" ");
			for(int i = 0; i < delimited.length; i++){
				items.add(delimited[i]);
			}
		}
		
		ProductRecommendation pr = new ProductRecommendation(itemLst, items);
		pr.findConnections("ABC");
	}
}