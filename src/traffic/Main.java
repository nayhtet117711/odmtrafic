package traffic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Main {
	
	static Node A = new Node("A");
	static Node B = new Node("B");
	static Node C = new Node("C");
	static Node D = new Node("D");
	static Node E = new Node("E");
	static Node F = new Node("F");
//	static Node G = new Node("G");
//	static Node H = new Node("H");
//	static Node I = new Node("I");
	
	public static void main(String[] args) {
		
		Graph graph = createGraph();
		
		// check Link Matrix works
		Helper.printLinkMatrix(graph);
		
		// Crate O-D Matrix
		int[][] odMatrix = new int[graph.getNodeList().size()][graph.getNodeList().size()];
		Helper.addVCountToOD(A, C, 100, odMatrix, graph);
		Helper.addVCountToOD(A, E, 30, odMatrix, graph);
		Helper.addVCountToOD(A, F, 50, odMatrix, graph);
		Helper.addVCountToOD(E, C, 40, odMatrix, graph);
		Helper.addVCountToOD(A, D, 20, odMatrix, graph);
		
		Helper.printODMatrix(graph, odMatrix);
		
		// check discovering all path from source to destination works
		Node src = A;
		Node dst = F;
		Vector<Vector<Node>> resultedPaths = Algorithm.discoverAllPathsFromSourceToDestination(graph, src, dst);
		Vector<Vector<Node>> shortestPaths = Helper.findShortestPath(resultedPaths); 
		
		Helper.printAllPathFromSrcToDst(resultedPaths, src, dst);
		System.out.println("\nShortest Path: "+ shortestPaths);
		
//		List<Node[]> allPosibleInnerArcsPassed = Helper.findAllPossibleInnerArcs(shortestPaths);
//		Vector<Vector<Node>> allPossiblePathsPassed = Helper.findAllPossiblePathsPassed(graph, odMatrix, allPosibleInnerArcsPassed);
		
		
//		int noPossibleOutcomes = Helper.findNoPossibleOutcomes(graph, odMatrix, allPossiblePathsPassed);//shortestPaths.size();
//		System.out.println("\nNo. Possible Outcomes: "+ noPossibleOutcomes);
		
		for(Vector<Node> shortestPath : shortestPaths) { 
			System.out.println("\n---------------------------------------------------");
			List<Node[]> innerArcsPassed = Helper.findAllInnerArcs(shortestPath);
			
			 System.out.println("\nInnerArcs Passed: ");
			 innerArcsPassed.forEach( np -> System.out.println(Arrays.deepToString(np)));
			
//			 Vector<Vector<Node>> possiblePathsPassed = Helper.findAllPossiblePathsPassed(graph, odMatrix, innerArcsPassed);
			
//			 Helper.printAllPossiblePath(possiblePathsPassed);
			
			int previousPossibleOutcomes = 0;
			boolean startFlag = true;
			System.out.print("\nProbability Nodes: (");
			for(Node[] pair : innerArcsPassed) {
				Node oNode = pair[0];
				Node dNode = pair[1];
	//			int oNodeIndex = Helper.findIndexInNodeList(oNode, graph.getNodeList());
	//			int dNodeIndex = Helper.findIndexInNodeList(dNode, graph.getNodeList());
				
				Vector<Vector<Node>> possiblePathsPassed = Helper.findAllPossiblePathsPassed(graph, odMatrix, pair, src);
//				Helper.printAllPossiblePath(possiblePathsPassed);
				
				int noPossibleOutcomes = Helper.findNoPossibleOutcomes(graph, odMatrix, possiblePathsPassed, oNode, dNode);//shortestPaths.size();
//				System.out.println("\nNo. Possible Outcomes: "+ noPossibleOutcomes);
				
				String separator = ", ";
				String startSeparator = startFlag ? "" : separator;
				String key = oNode.toString() + dNode.toString();
				String value = previousPossibleOutcomes>0 ? (noPossibleOutcomes+"/"+previousPossibleOutcomes) : String.valueOf(noPossibleOutcomes);
				System.out.print(startSeparator+key+separator+value);
				if(startFlag) previousPossibleOutcomes = noPossibleOutcomes;
				startFlag = false;
				
//				if(oNode.equals(src)) {
//					System.out.print(oNode.toString() + dNode.toString() + ", " + noPossibleOutcomes);
//				} else if(dNode.equals(dst)) {
//					System.out.print(", "+oNode.toString() + dNode.toString()+ ", "+ (vCountOtoD + "/"+noPossibleOutcomes) );
//				} else {
//					System.out.print(", "+oNode.toString() + dNode.toString()+ ", P");
//					List<Node[]> singlePassedNodePair = new ArrayList<>();
//					singlePassedNodePair.add(pair);
//					Vector<Vector<Node>> possiblePathsPassedCurrentOD = Helper.findAllPossiblePathsPassed(graph, odMatrix, singlePassedNodePair);
//					System.out.println("\n"+possiblePathsPassedCurrentOD);
//				}
			}
			System.out.println(")");
		}
	}

	private static Graph createGraph() {
		Graph graph = new Graph(Arrays.asList(A, B, C, D, E, F));
		graph.addLink(A, B, 2);
		graph.addLink(A, D, 2);
		graph.addLink(B, A, 2);
		graph.addLink(B, C, 2);
		graph.addLink(B, E, 2);
		graph.addLink(C, B, 2);
		graph.addLink(C, F, 2);
		graph.addLink(D, A, 2);
		graph.addLink(D, E, 2);
//		graph.addLink(D, G, 2);
		graph.addLink(E, B, 2);
		graph.addLink(E, D, 2);
		graph.addLink(E, F, 2);
//		graph.addLink(E, H, 2);
		graph.addLink(F, E, 2);
//		graph.addLink(F, I, 2);
//		graph.addLink(G, D, 2);
//		graph.addLink(G, H, 2);
//		graph.addLink(H, E, 2);
//		graph.addLink(H, G, 2);
//		graph.addLink(H, I, 2);
//		graph.addLink(I, F, 2);
//		graph.addLink(I, H, 2);
		return graph;
	}
	
	
}
