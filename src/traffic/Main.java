package traffic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Main {
	
	public static void main(String[] args) {
		
		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		Node F = new Node("F");
		Node G = new Node("G");
		Node H = new Node("H");
		Node I = new Node("I");
		
		Graph graph = new Graph(Arrays.asList(A, B, C, D, E, F, G, H, I));
		graph.addLink(A, B, 2);
		graph.addLink(A, D, 2);
		graph.addLink(B, A, 2);
		graph.addLink(B, C, 2);
		graph.addLink(B, E, 2);
		graph.addLink(C, B, 2);
		graph.addLink(C, F, 2);
		graph.addLink(D, A, 2);
		graph.addLink(D, E, 2);
		graph.addLink(D, G, 2);
		graph.addLink(E, B, 2);
		graph.addLink(E, D, 2);
		graph.addLink(E, F, 2);
		graph.addLink(E, H, 2);
		graph.addLink(F, E, 2);
		graph.addLink(F, I, 2);
		graph.addLink(G, D, 2);
		graph.addLink(G, H, 2);
		graph.addLink(H, E, 2);
		graph.addLink(H, G, 2);
		graph.addLink(H, I, 2);
		graph.addLink(I, F, 2);
		graph.addLink(I, H, 2);
		
		// check Link Matrix works
		System.out.println("\nLinkMatrix: ");
		System.out.printf("%-6s", "");
		graph.getNodeList().forEach(n -> {
			System.out.printf("%-6s", String.valueOf(n));
		});
		System.out.println();
		for(int i=0; i<graph.getLinkMatrix().length; i++) {
			double[] a = graph.getLinkMatrix()[i];
			System.out.printf("%-6s", graph.getNodeList().get(i));
			for(double n : a) 
				System.out.printf("%-6s", String.valueOf(new Double(n).intValue()));
			System.out.println();
		}
		// check adjacency of node works
		// System.out.println("\nAdjacency: \n"+ Helper.findAdjacencyNodesOfNode(F, graph.getNodeList(), graph.getLinkMatrix()));
		
		// check discovering all path from source to destination works
		Node src = A;
		Node dst = C;
		System.out.println("\nAll paths from "+ src + " to " + dst + " : ");
		Vector<Vector<Node>> resultedPaths = Algorithm.discoverAllPathsFromSourceToDestination(graph, src, dst);
		Vector<Node> bestPath = resultedPaths.get(0);
		for(Vector<Node> path : resultedPaths) {
			System.out.println(path);
			if(path.size()<bestPath.size()) 
				bestPath = Helper.cloneVector(path);
		}
		
		System.out.println("\nBest Path: "+ bestPath);
		
		int[][] odMatrix = new int[graph.getNodeList().size()][graph.getNodeList().size()];
		addVCountToOD(A, C, 100, odMatrix, graph);
		addVCountToOD(A, E, 30, odMatrix, graph);
		addVCountToOD(E, C, 40, odMatrix, graph);
		
		// check OD Matrix works
		System.out.println("\nO-D Matrix: ");
		System.out.printf("%-6s", "");
		graph.getNodeList().forEach(n -> {
			System.out.printf("%-6s", String.valueOf(n));
		});
		System.out.println();
		for(int i=0; i<odMatrix.length; i++) {
			int[] a = odMatrix[i];
			System.out.printf("%-6s", graph.getNodeList().get(i));
			for(int n : a) 
				System.out.printf("%-6s", String.valueOf(n));
			System.out.println();
		}
		
		List<Node[]> innerPairs = new ArrayList<>();
		for(int i=1; i<bestPath.size(); i++) {
			innerPairs.add(new Node[]{ bestPath.get(i-1), bestPath.get(i) });
		}
		
		System.out.println("\nPair nodes: ");
		innerPairs.forEach( np -> System.out.print(Arrays.deepToString(np)));
		System.out.println();
		
		// find all posible paths in graphs
		for(int i=0; i<odMatrix.length; i++) {
			int[] a = odMatrix[i];
			for(int j=0; j<a.length; j++) {
				if(odMatrix[i][j]>0) {
					Node srcNode = graph.getNodeList().get(i);
					Node dstNode = graph.getNodeList().get(j);
					if(true || !srcNode.equals(src) || !dstNode.equals(dst)) {
						Vector<Vector<Node>> resultedPathsPossible= Algorithm.discoverAllPathsFromSourceToDestination(graph, srcNode, dstNode);
						System.out.println("\n===>> "+ srcNode + " - " + dstNode);
						for(Vector<Node> path : resultedPathsPossible) {
							
							for(int xi=0; xi<innerPairs.size(); xi++) {
								
								if(path.containsAll(Arrays.asList(innerPairs.get(xi))) && path.indexOf(innerPairs.get(xi)[1])-path.indexOf(innerPairs.get(xi)[0])==1 ) {
									System.out.println(path); 
//									System.out.println(" | " + path.indexOf(innerPairs.get(xi)[1]) + " - " + path.indexOf(innerPairs.get(xi)[0]));
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	public static void addVCountToOD(Node src, Node dst, int count, int[][] odMatrix, Graph graph) {
		odMatrix[Helper.findIndexInNodeList(src, graph.getNodeList())][Helper.findIndexInNodeList(dst, graph.getNodeList())] = count;
	}
	
}
