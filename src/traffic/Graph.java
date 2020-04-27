package traffic;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	
	private List<Node> nodeList;
	private double[][] linkMatrix;
	
	public Graph(int numberOfNodes) {
		super();
		nodeList = new ArrayList<>();
		this.linkMatrix = new double[numberOfNodes][numberOfNodes];
	}

	public Graph(List<Node> nodeList) {
		super();
		this.nodeList = nodeList;
		this.linkMatrix = new double[nodeList.size()][nodeList.size()];
	}
	
	//Getter and Setter
	public List<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}

	public double[][] getLinkMatrix() {
		return linkMatrix;
	}

	public void setLinkMatrix(double[][] linkMatrix) {
		this.linkMatrix = linkMatrix;
	}
	
	//Additional methods
	public void addLink(Node node1, Node node2, double distance) {
		int node1Index = Helper.findIndexInNodeList(node1, nodeList);
		int node2Index = Helper.findIndexInNodeList(node2, nodeList);
		linkMatrix[node1Index][node2Index] = distance;
	}
	
	
}
