import java.util.*;

public class SumOfShortestPaths{
	public static final int MAX = Integer.MAX_VALUE;
	
	public static void main(String[] args){
		int[][] graph = {{0, 1, 0, 0},
						{1, 0, 2, 3},
						{0, 2, 0, 0},
						{0, 3, 0, 0}};
		
		int sum = shortestPaths(graph);
		System.out.println(sum);
	}
	
	public static int shortestPaths(int[][] graph){
		int sum = 0;
		for(int i = 0; i < graph.length-1; i++){
			int[] weight = dijkstra(graph, i);
			
			for(int j = i+1; j < weight.length; j++){
				System.out.println(i + " to " + j + " has weight: " + weight[j]);
				sum += weight[j];
			}
		}
		return sum;
	}
	
	public static int[] dijkstra(int[][] graph, int src){
		/**
		What do I need?
			2. weight set 	-> updating edge values
		*/
		/** Initialize the set of nodes that excludes the start node */
		HashSet<Integer> VS = new HashSet<Integer>();
		for(int i = 0; i < graph.length; i++){
			if(src != i){
				VS.add(i);
			}
		}
		
		/** Initialize weight set */
		int[] weight = new int[graph.length];
		for(int i = 0; i < graph.length; i++){
			weight[i] = MAX;
		}
		
		/** Initialize the weights of the src and its adjacent nodes */
		weight[src] = 0;
		for(int i = 0; i < graph[src].length; i++){
			if(graph[src][i] > 0){
				weight[i] = graph[src][i];
			}
		}
		
		/** While not all nodes visited */
		while(!VS.isEmpty()){
			int minNode = findMinNode(VS, weight);
			VS.remove(minNode);
			
			/** update the weights of the edges adjacent to the minimum node */
			for(int i = 0; i < graph[minNode].length; i++){
				if(graph[minNode][i] > 0 && weight[i] > graph[minNode][i]){
					weight[i] = weight[minNode] + graph[minNode][i];
				}
			}
		}
		return weight;
	}
	
	private static int findMinNode(HashSet<Integer> set, int[] weight){
		int minValue = MAX;
		int minNode = MAX;
		for(int node : set){
			if(weight[node] < minValue){
				minNode = node;
				minValue = weight[node];
			}
		}
		return minNode;
	}
}