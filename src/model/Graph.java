package model;

import java.util.*;
import datastructures.*;

/**
 * Represents one graph including all nodes in the graph.
 * 
 * @version alpha
 * @author Matti Nelimarkka
 **/
public class Graph {
	
	private List<Node> edges;
	private List<String> edgeNames;
	
	/**
	 * Default constructor, takes no parameters and sets the graph as empty.
	 **/
	public Graph() {
		edges = new MyList<Node>();
		edgeNames = new MyList<String>();
	}
	

	/**
	 * Adds a new node to the graph.
	 * Confirms, that there is no node with same name before doing the addition.
	 * 
	 * @return true if successful
	 * */
	public boolean addNode(Node n) {
		
		// only add nodes with unique name
		if( edgeNames.contains( n.name() )  ) {
			return false;
		}
		
		edges.add(n);
		edgeNames.add(n.name());
		
		return true;
	}
	
	/** 
	 * Returns the number of nodes in this graph.
	 * 
	 * @return number of nodes
	 **/
	public int size() {
		return edges.size();
	}
	
	/**
	 * Returns the graph in a matrix format, by starting to cound from the first element in the node list.
	 **/
	public double[][] matrix() {
		int size = size();
		double[][] res = new double[size][size];
		
		List<Node> edges = edges();
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				res[i][j] = edges.get(i).linkWeight( edges.get(j) );
				// check if checking the diagonal
				if( i == j) {
					res[i][j] = 0;
				}
			}
		}
		
		return res;
	}
	
	/**
	 * Checks if n is part of this graph.
	 * 
	 * @retun true if n is part of the graph and f otherwise
	 * */
	public boolean has(Node n) {
		return edges.contains(n);
	}

	/**
	 * Checks if node with name name is part of this graph.
	 * 
	 * @retun true if n is part of the graph and f otherwise
	 * */
	public boolean has(String name) {
		return edgeNames.contains(name);
	}
	
	/**
	 * Returns the edges of this graph.
	 * 
	 * @return edges
	 * **/
	public List<Node> edges() {
		return edges;
	}
	
	/**
	 * Adds all nodes in collection to this graph.
	 * Assumes that nodes can be added, does not notify about errors.
	 * 
	 * @param collection nodes to be added
	 * */
	public void addAll(Collection<Node> collection) {
		for(Node n : collection ) {
			addNode( n );
		}
	}

}
