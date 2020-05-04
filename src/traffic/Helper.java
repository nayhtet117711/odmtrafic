package traffic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

public class Helper {

	// find index of a node in nodeList
	public static int findIndexInNodeList(Node node, List<Node> nodeList) {
		for (int i = 0; i < nodeList.size(); i++) {
			if (node.getName().equals(nodeList.get(i).getName()))
				return i;
		}
		return -1;
	}

	// find all adjacency of a node in graph
	public static List<Node> findAdjacencyNodesOfNode(Node node, List<Node> nodeList, double[][] linkMatrix) {
		int indexOfNode = findIndexInNodeList(node, nodeList);
//		double[] nodeListOfNode = linkMatrix[indexOfNode];
		List<Node> adjacencyNodes = new ArrayList<>();
		for (int i = 0; i < nodeList.size(); i++) {
			for (int j = 0; j < nodeList.size(); j++) {
				if (i == indexOfNode && linkMatrix[i][j] > 0.0 && i != j && !adjacencyNodes.contains(nodeList.get(j))) {
					adjacencyNodes.add(nodeList.get(j));
				} else if (j == indexOfNode && linkMatrix[i][j] > 0.0 && i != j
						&& !adjacencyNodes.contains(nodeList.get(i))) {
					adjacencyNodes.add(nodeList.get(i));
				}
			}
		}
//		if(node.getName().equals("C")) {
//			System.out.println(" adj: "+ adjacencyNodes );
//		}
		return adjacencyNodes;
	}

	public static Vector<Node> cloneVector(Vector<Node> vector) {
		Vector<Node> newVector = new Vector<>();
		for (Node node : vector) {
			newVector.add(node);
		}
		return newVector;
	}

	public static Vector<Vector<Node>> findAllPossiblePathsPassed(Graph graph, int[][] odMatrix, List<Node[]> passedNodePairs) {
		Vector<Vector<Node>> possiblePathsPassed = new Vector<>();
		// find all posible paths between source and destination nodes in graphs
		for (int i = 0; i < odMatrix.length; i++) {
			int[] a = odMatrix[i];
			for (int j = 0; j < a.length; j++) {
				if (odMatrix[i][j] > 0) {
					Node srcNode = graph.getNodeList().get(i);
					Node dstNode = graph.getNodeList().get(j);
					if (true/* (Remove for temporary) || !srcNode.equals(src) || !dstNode.equals(dst) */) {
						Vector<Vector<Node>> resultedPathsPossible = Algorithm
								.discoverAllPathsFromSourceToDestination(graph, srcNode, dstNode);

						Vector<Node> tmpPossiblePathsPassed = new Vector<>();
						for (Vector<Node> path : resultedPathsPossible) {

							for (int xi = 0; xi < passedNodePairs.size(); xi++) {

								if (path.containsAll(Arrays.asList(passedNodePairs.get(xi)))
										&& path.indexOf(passedNodePairs.get(xi)[1])
												- path.indexOf(passedNodePairs.get(xi)[0]) == 1) {
									if (tmpPossiblePathsPassed.size() == 0) {
										tmpPossiblePathsPassed = path;
									} else if (tmpPossiblePathsPassed.size() > 0
											&& tmpPossiblePathsPassed.size() > path.size()) {
										tmpPossiblePathsPassed = path;
									}
								}
							}

						}
						if (tmpPossiblePathsPassed.size() > 0) {
							possiblePathsPassed.add(tmpPossiblePathsPassed);
						}
					}
				}
			}
		}
		return possiblePathsPassed;
	}
	
	public static Vector<Vector<Node>> findAllPossiblePathsPassed(Graph graph, int[][] odMatrix, Node[] passedNodePairs, Node rootNode) {
		Vector<Vector<Node>> possiblePathsPassed = new Vector<>();
		// find all posible paths between source and destination nodes in graphs
		for (int i = 0; i < odMatrix.length; i++) {
			int[] a = odMatrix[i];
			for (int j = 0; j < a.length; j++) {
				if (odMatrix[i][j] > 0) {
					Node srcNode = graph.getNodeList().get(i);
					Node dstNode = graph.getNodeList().get(j);
					if (true/* (Remove for temporary) || !srcNode.equals(src) || !dstNode.equals(dst) */) {
						Vector<Vector<Node>> resultedPathsPossible = Algorithm
								.discoverAllPathsFromSourceToDestination(graph, srcNode, dstNode);

						Vector<Node> tmpPossiblePathsPassed = new Vector<>();
						for (Vector<Node> path : resultedPathsPossible) {

							if (path.get(0).equals(rootNode) && path.containsAll(Arrays.asList(passedNodePairs))
									&& path.indexOf(passedNodePairs[1])
											- path.indexOf(passedNodePairs[0]) == 1) {
								if (tmpPossiblePathsPassed.size() == 0) {
									tmpPossiblePathsPassed = path;
								} else if (tmpPossiblePathsPassed.size() > 0
										&& tmpPossiblePathsPassed.size() > path.size()) {
									tmpPossiblePathsPassed = path;
								}
							}

						}
						if (tmpPossiblePathsPassed.size() > 0) {
							possiblePathsPassed.add(tmpPossiblePathsPassed);
						}
					}
				}
			}
		}
		return possiblePathsPassed;
	}
	
	// Find each pairs of nodes passed
	public static List<Node[]> findAllInnerArcs(Vector<Node> shortestPath) {
		List<Node[]> passedNodePairs = new ArrayList<>();
		for(int i=1; i<shortestPath.size(); i++) {
			passedNodePairs.add(new Node[]{ shortestPath.get(i-1), shortestPath.get(i) });
		}
		return passedNodePairs;
	}
	
	// Find shortest Path
	public static Vector<Vector<Node>> findShortestPath(Vector<Vector<Node>> resultedPaths) {
		if(resultedPaths.size()==0) return new Vector<>();
		
		Vector<Vector<Node>> shortestPaths = new Vector<>();
		int minPathSize = Integer.MAX_VALUE;
		for(Vector<Node> path : resultedPaths) {
			if(path.size()<minPathSize) 
				minPathSize = path.size();
		}
		for(Vector<Node> path : resultedPaths) {
			if(path.size()==minPathSize) 
				shortestPaths.add(Helper.cloneVector(path));
		}
		return shortestPaths;
	}
	
	// print Link Matrix of graph
	public static void printLinkMatrix(Graph graph) {
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
	}
	
	// print all paths from source to dest 
	public static void printAllPathFromSrcToDst(Vector<Vector<Node>> resultedPaths, Node src, Node dst) {
		System.out.println("\nAll paths from "+ src + " to " + dst + " : ");
		for(Vector<Node> path : resultedPaths) {
			System.out.println(path);
		}
	}
	
	// print OD Matrix 
	public static void printODMatrix(Graph graph, int[][] odMatrix) {
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
	}
	
	// print all possible paths Passed
	public static void printAllPossiblePath(Vector<Vector<Node>> possiblePathsPassed) {
		System.out.println("\nPossiblePathsPassed:  ");
		for(Vector<Node> path1 : possiblePathsPassed) {
			System.out.println(path1);
		}
	}

	// add vehicle count to OD (for demo)
	public static void addVCountToOD(Node src, Node dst, int count, int[][] odMatrix, Graph graph) {
		odMatrix[Helper.findIndexInNodeList(src, graph.getNodeList())][Helper.findIndexInNodeList(dst, graph.getNodeList())] = count;
	}
	
	// find number of possible outcomes
	public static int findNoPossibleOutcomes(Graph graph, int[][] odMatrix, Vector<Vector<Node>> possiblePathsPassed, Node tNode1, Node tNode2) {
		int noPossibleOutcomes = 0;
		for(Vector<Node> path1 : possiblePathsPassed) {
			Node fromNode = path1.get(0);
			Node toNode = path1.get(path1.size()-1);
			
			int numberOfVehicles = odMatrix[findIndexInNodeList(fromNode, graph.getNodeList())][findIndexInNodeList(toNode, graph.getNodeList())];
			Vector<Vector<Node>> resultedPaths = Algorithm.discoverAllPathsFromSourceToDestination(graph, fromNode, toNode);
			Vector<Vector<Node>> shortestPaths = Helper.findShortestPath(resultedPaths); 
			int noPathContainTargetNodes = 0;
			for (Vector<Node> path : shortestPaths) {
				if (path.containsAll(Arrays.asList(tNode1, tNode2))
						&& (path.indexOf(tNode2)- path.indexOf(tNode1)) == 1) {
					noPathContainTargetNodes++;
				}
			}
			
//			System.out.println("S-Path: "+ shortestPaths + " : " + (noPathContainTargetNodes+"/"+shortestPaths.size()) + " = " + (numberOfVehicles*noPathContainTargetNodes/shortestPaths.size()));
			
			noPossibleOutcomes += numberOfVehicles*noPathContainTargetNodes/shortestPaths.size();
		}
//		System.out.println("------------------------------------------------------------------ = "+ noPossibleOutcomes+"\n");
		return noPossibleOutcomes;
	}

	public static List<Node[]> findAllPossibleInnerArcs(Vector<Vector<Node>> shortestPaths) {
		List<Node[]> passedNodePairs = new ArrayList<>();
		for(Vector<Node> shortestPath : shortestPaths) {
			for(int i=1; i<shortestPath.size(); i++) {
				passedNodePairs.add(new Node[]{ shortestPath.get(i-1), shortestPath.get(i) });
			}
		}
//		System.out.println("check: " );
//		passedNodePairs.forEach(e -> System.out.println(Arrays.deepToString(e)));
		return passedNodePairs;
	}

}
