package model;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import model.ShortestPathGraph.QueueNode;

import datastructures.*;

/**
 * Calculates the shortest path using A*-algorithm.
 * **/
public class AStarPathGraph extends ShortestPathGraph {
	
	/**
	 * Provides an heuristic estimation of the path length between from and to.
	 * 
	 * @param a the node from the estimation is started
	 * @param b the node where the estimation is done
	 * 
	 * @return the estimated value of the path length.
	 * */
	protected double estimate(Node a, Node b) {
		// FIXME: for testing only, makes this Dikstra's algorithm!
		return a.linkWeight(b);
	}

	@Override
	public List<Node> path(Node from, Node to) {
		// list of items that must be checked
		Queue<QueueNode> check = new MyHeap<QueueNode>();
		
		// initialize all nodes and distances
		Map<Node,Double> distances = new MyMap<Node, Double>();
		Map<Node,Node> paths = new MyMap<Node, Node>();
		
		for( Node n : edges() ) {
			distances.put( n , INFINITY );
			check.add( new QueueNode( n , INFINITY )  );
		}
		
		// set current node is zero distance
		distances.put( from, 0.0 );
		paths.put( from, null);
		
		Node current = from;

		
		while( ! check.isEmpty() ) {
			
			// check if located the target?
			if( current == to ) {
				break;
			}
			
			double currentDistance = distances.get( current );
			
			for( Map.Entry<Node, Double> neigbour : current.neighbors().entrySet() ) {
				
				Node n = neigbour.getKey();
				double d = estimate(current, n);

				double candidate = currentDistance + d;
				
				boolean update = false;
				
				// special case, there is no previous path to n, update!
				if( distances.get( n ) == INFINITY ) {
					update = true;
				}
				
				// we have a shorter path! update
				if( candidate < distances.get( n ) || update ) {
					// set new distance to candidate
					distances.put( n, candidate );
					// set new previous node to candidate
					paths.put( n , current );
					
					// remove from que && update back to new position
					check.remove( new QueueNode( n , -1 ) );
					check.add( new QueueNode(n, candidate) );
				}
				
			}
			
			// take new node to be checked
			current = check.poll().node();
		}
 		
		List<Node> backward = new MyList<Node>();
		Node previous = to;
		while( paths.containsKey( previous ) ) {
			backward.add( previous );
			previous = paths.get( previous );
		}
		
		// rotate list
		// todo: this could be done via a stack too! simpler that way
		List<Node> result = new MyList<Node>();
		for(int i = backward.size() -1; i >= 0; i-- ) {
			result.add( backward.get(i) );
		}
		return result;

	}

}
