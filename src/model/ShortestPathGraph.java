package model;

import java.util.List;

/**
 * Class specifying calculation of shortest path between two nodes.
 * **/
public abstract class ShortestPathGraph extends Graph {
	
	/**
	 * Indicator of distance meaning that the nodes are not connected.
	 * **/
	public static double INFINITY = Double.NEGATIVE_INFINITY;

	/**
	 * Calculates the shortest path between Nodes from and to.
	 * 
	 * @param from the node from where the calculation is started.
	 * @param to node where the path leads
	 * @return double return the shortest distance between nodes from and to or INFINITY, if no such path exists.
	 * */
	public abstract double distance(Node from, Node to);
	
	/**
	 * Calculates the shortest path between Nodes from and to.
	 * 
	 * @param from the node from where the calculation is started.
	 * @param to node where the path leads
	 * @return list of nodes, including from and to, that create the shortest path between from and to, or an empty list, if no such path exists.
	 * */
	public abstract List<Node> path(Node from, Node to);
	
}
