package model;

import java.util.*;
import datastructures.*;

/**
 * Represents one edge in the graph.
 * 
 * @version alpha
 * @author Matti Nelimarkka
 **/
public class Node {
	
	private Map<Node,Double> neighbor;
	private String name;
	
	/**
	 * Default constructor, creates an single node.
	 * 
	 * @param name name of this node
	 * */
	public Node(String name) {
		this.name = name;
		neighbor = new MyMap<Node,Double>();
	}

	/**
	 * Constructor, creates a list with already linked connections.
	 * 
	 * @param name name of this node
	 * @param neighbors weighted list of connections
	 * */
	public Node(String name, Map<Node, Double> neighbors) {
		this(name);
		neighbor = neighbors;
	}
	
	/**
	 * Returns name.
	 * */
	public String name() {
		return this.name;
	}
	
	/**
	 * Set n connected this node with the weight weight.
	 * 
	 * If the nodes were already connected, returns true and updates the weight, otherwise returns false.
	 * 
	 * @param n the node where connection is made
	 * @param weight weight of the connection
	 * @return true if the nodes were already connected, false otherwise
	 **/
	public boolean linkTo(Node n, double weight) {
		// validate input
		if( this.equals(n) ) {
			throw new IllegalArgumentException("Can not link with one self");
		}
		if( weight < 0 ) {
			throw new IllegalArgumentException("Negative ties are not allowed");
		}
		
		Double value = neighbor.put(n, weight);

		return value != null;
	}
	
	
	/**
	 * Checks if this node and n are connected, i.e. if n is node's neighbor.
	 * 
	 * @param n the node tested
	 * @return true if n is node's neighbor
	 **/
	public boolean linkTo(Node n) {
		return neighbor.containsKey(n);
	}
	
	/**
	 * Provides the weight from this node to n.
	 * 
	 * @param n the node tested
	 * @return the link weight or -1
	 * */
	public double linkWeight(Node n) {
		if( linkTo(n) ) {
			return neighbor.get(n);
		}
		return -1;
	}
	
	/**
	 * Returns node's neighbors.
	 * 
	 * @return map of node's neigbbors and the weights.
	 * */
	public Map<Node,Double> neighbors() {
		return neighbor;
	}

}
