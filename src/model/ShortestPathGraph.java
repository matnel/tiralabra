package model;

import java.util.List;

/**
 * Class specifying calculation of shortest path between two nodes.
 * **/
public abstract class ShortestPathGraph extends Graph {

	/**
	 * Calculates the shortest path between Nodes from and to.
	 * 
	 * @param from the node from where the calculation is started.
	 * @param to node where the path leads
	 * @return double return the shortest distance between nodes from and to
	 * */
	public abstract double distance(Node from, Node to);
	
	/**
	 * Calculates the shortest path between Nodes from and to.
	 * 
	 * @param from the node from where the calculation is started.
	 * @param to node where the path leads
	 * @return list of nodes, including from and to, that create the shortest path between from and to
	 * */
	public abstract List<Node> path(Node from, Node to);
	
}
