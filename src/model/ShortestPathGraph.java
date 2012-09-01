package model;

import java.util.Iterator;
import java.util.List;

/**
 * Class specifying calculation of shortest path between two nodes.
 * **/
public abstract class ShortestPathGraph extends Graph {
	
	/**
	 * Indicator of distance meaning that the nodes are not connected.
	 * **/
	public static double INFINITY = Double.MAX_VALUE;

	/**
	 * Calculates the shortest path between Nodes from and to.
	 * 
	 * @param from the node from where the calculation is started.
	 * @param to node where the path leads
	 * @return double return the shortest distance between nodes from and to or INFINITY, if no such path exists.
	 * */
	public double distance(Node from, Node to) {
		
		// this is the most computation heavy operation
		List<Node> path = path(from, to);
		
		// compared to generating the Dikstra's graph these are trivial,
		// no major impact in O(n) sense!
		
		// special case, there's no path!
		if( path.isEmpty() ) {
			return INFINITY;
		}
		
		double distance = 0;
		Iterator<Node> pathI = path.iterator();
		
		Node last = pathI.next();
		while( pathI.hasNext() ) {
			Node next = pathI.next();
			distance += last.linkWeight( next );
			last = next;
		}
		
		return distance;
	}

	
	/**
	 * Calculates the shortest path between Nodes from and to.
	 * 
	 * @param from the node from where the calculation is started.
	 * @param to node where the path leads
	 * @return list of nodes, including from and to, that create the shortest path between from and to, or an empty list, if no such path exists.
	 * */
	public abstract List<Node> path(Node from, Node to);
	
	
	/**
	 * Container storing the node and the current found distance of this node.
	 * Used in systems that require distance comparisons between elements of the system.
	 * **/
	protected class QueueNode implements Comparable {
		
		/**
		 * The node stored in this object.
		 * */
		private Node node;
		
		/**
		 * The distance associated with this node.
		 * */
		private double distance;
		
		
		/**
		 * Default constructor.
		 * 
		 * @param node node stored in this container
		 * @param initial the distance associated with this node
		 * 
		 **/
		QueueNode(Node node, double initial) {
			this.node = node;
			this.distance = initial;
		}
		
		/**
		 * Returns the node stored in this container.
		 * 
		 * @return the node
		 * */
		Node node() {
			return node;
		}
		
		/**
		 * Compares if the nodes stored in the QueueNodes are same.
		 * 
		 * @param o other object compared with
		 * 
		 * @return true if the nodes stored are same, false otherwise
		 * */
		public boolean equals(Object o) {
			if( ! (o instanceof QueueNode) ) {
				return false;
			}
			QueueNode n = (QueueNode) o;
			return n.node.equals( this.node );
		}

		/**
		 * Allows comparing the distances between nodes.
		 * 
		 * @param o other object
		 * 
		 * @returns positive, if other node is in shorter distance,<br/>
		 * 			negative if this node is in shorter distance<br/>
		 * 			zero if distances are the same.
		 * */
		public int compareTo(Object o) {
			QueueNode other = (QueueNode) o;
			return (int)( this.distance - other.distance );
		}
		
	}
	
}
