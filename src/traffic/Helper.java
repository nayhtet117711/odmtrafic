package traffic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

public class Helper {
	
	// find index of a node in nodeList
	public static int findIndexInNodeList(Node node, List<Node> nodeList) {
		for(int i=0; i<nodeList.size(); i++) {
			if(node.getName().equals(nodeList.get(i).getName()))
				return i;
		}
		return -1;
	}
	
	// find all adjacency of a node in graph
	public static List<Node> findAdjacencyNodesOfNode(Node node, List<Node> nodeList, double[][] linkMatrix) {
		int indexOfNode = findIndexInNodeList(node, nodeList);
//		double[] nodeListOfNode = linkMatrix[indexOfNode];
		List<Node> adjacencyNodes = new ArrayList<>();
		for(int i=0; i<nodeList.size(); i++) {
			for(int j=0; j<nodeList.size(); j++) {
				if(i==indexOfNode && linkMatrix[i][j]>0.0 && !adjacencyNodes.contains(nodeList.get(i))) {
					adjacencyNodes.add(nodeList.get(i));
				} else if(j==indexOfNode && linkMatrix[i][j]>0.0 && !adjacencyNodes.contains(nodeList.get(i))) {
					adjacencyNodes.add(nodeList.get(i));
				}
			}
		}
		return adjacencyNodes;
	}
	
	public static Vector<Node> cloneVector(Vector<Node> vector) {
		Vector<Node> newVector = new Vector<>();
		for(Node node : vector) {
			newVector.add(node);
		}
		return newVector;
	}
	
}
 