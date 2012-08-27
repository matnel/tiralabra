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
	
	
	protected class QueueNode<T> implements Comparable<T> {
		
		private Node node;
		private double distance;
		
		public QueueNode(Node node, double initial) {
			this.node = node;
			this.distance = initial;
		}
		
		public Node node() {
			return node;
		}
		
		public boolean equals(Object o) {
			if( ! (o instanceof QueueNode) ) {
				return false;
			}
			QueueNode n = (QueueNode) o;
			return n.node.equals( this.node );
		}

		@Override
		public int compareTo(T o) {
			QueueNode<T> other = (QueueNode<T>) o;
			return (int)( this.distance - other.distance );
		}
		
	}
	
}
