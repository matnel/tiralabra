package model;

import java.util.Collection;
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
		int networkSize = this.size();
		// calculate number of same nodes
		Collection<Node> neighbors = a.neighbors().keySet();
		Collection<Node> bNeighbors = b.neighbors().keySet();
		neighbors.retainAll( bNeighbors );
		int similarity = networkSize - neighbors.size();
		double weight = 0;
		for( Double d : a.neighbors().values() ) {
			weight += d;
		}
		// return similarity; // first heuristic
		return weight * similarity;
		// return 0; // use this when testing algorithm!
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
		}
		
		// set current node is zero distance
		distances.put( from, 0.0 );
		paths.put( from, null);
		check.add( new QueueNode( from, Double.NEGATIVE_INFINITY ) );
		
		Node current = null;

		while( ! check.isEmpty() ) {
			current = check.poll().node();
			
			// check if located the target?
			if( current == to ) {
				break;
			}
			
			double currentDistance = distances.get( current );
			
			for( Map.Entry<Node, Double> neigbour : current.neighbors().entrySet() ) {
				
				Node n = neigbour.getKey();

				double candidate = currentDistance + current.linkWeight( n );
				
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
					check.add( new QueueNode(n, candidate + estimate(n, to) ) );
				}
				
			}
			
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
