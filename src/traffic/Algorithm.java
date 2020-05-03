package traffic;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

public class Algorithm {

	/*
	 * create a queue which will store path(s) of type vector 
	 * initialise the queue with first path starting from src
	 * 
	 * Now run a loop till queue is not empty 
	 * * * get the frontmost path from queue
	 * * * check if the lastnode of this path is destination 
	 * * * * * if true then print the path
	 * * * run a loop for all the vertices connected to the current vertex i.e. lastnode extracted from path 
	 * * * * * if the vertex is not visited in current path 
	 * * * * * * (a) create a new path from earlier path and append this vertex 
	 * * * * * * (b) insert this new path to queue
	 */
	public static Vector<Vector<Node>> discoverAllPathsFromSourceToDestination(Graph graph, Node src, Node dst) {
		Vector<Vector<Node>> resultedPaths = new Vector<>();
		breadthFirstSearch(graph, src, dst, resultedPaths);
		return resultedPaths;
	}
	
	public static void breadthFirstSearch(Graph graph, Node src, Node dst, Vector<Vector<Node>> resultedPaths) {
		Queue<Vector<Node>> paths = new LinkedList<>();
		Vector<Node> path = new Vector<>();
		path.add(src);
		paths.add(path);

		while (!paths.isEmpty()) {
			path = paths.poll();
			Node lastNode = path.lastElement();
			if (lastNode.equals(dst)) {
				resultedPaths.add(path);
			}
			List<Node> adjacencyNodes = Helper.findAdjacencyNodesOfNode(lastNode, graph.getNodeList(),
					graph.getLinkMatrix());
			
			for (Node adjacencyNode : adjacencyNodes) {
				if (!path.contains(adjacencyNode)) {
					Vector<Node> newPath = Helper.cloneVector(path);
					newPath.add(adjacencyNode);
					paths.add(newPath);
				} 
			}
		}

	}

}
