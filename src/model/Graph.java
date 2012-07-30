package model;

import java.util.*;

/**
 * Represents one graph including all nodes in the graph.
 * 
 * @version alpha
 * @author Matti Nelimarkka
 **/
public class Graph {
	
	private List<Node> edges;
	
	/**
	 * Default constructor, takes no parameters and sets the graph as empty.
	 **/
	public Graph() {
		// TODO: change to my own list implementation
		edges = new ArrayList<Node>();
	}
	

	/**
	 * Adds a new node to the graph.
	 * Confirms, that there is no node with same name before doing the addition.
	 * 
	 * @return true if successful
	 * */
	public boolean addNode(Node n) {
		return true;
	}
	
	/** 
	 * Returns the number of nodes in this graph.
	 * 
	 * @return number of nodes
	 **/
	public int size() {
		return -1;
	}
	
	/**
	 * Returns the graph in a matrix format, by starting to cound from the first element in the node list.
	 **/
	public int[][] matrix() {
		return null;
	}
	
	/**
	 * Checks if n is part of this graph.
	 * 
	 * @retun true if n is part of the graph and f otherwise
	 * */
	public boolean has(Node n) {
		return false;
	}

	/**
	 * Checks if node with name name is part of this graph.
	 * 
	 * @retun true if n is part of the graph and f otherwise
	 * */
	public boolean has(String name) {
		return false;
	}
	
	/**
	 * Returns the edges of this grap.
	 * **/
	public List<Node> edges() {
		return null;
	}
	
	// abstract Map shortestPaths();

}
