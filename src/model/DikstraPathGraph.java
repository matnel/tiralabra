package model;
import java.util.*;

import datastructures.*;

public class DikstraPathGraph extends ShortestPathGraph {

	@Override
	public List<Node> path(Node from, Node to) {
		// list of items that must be checked
		PriorityQueue<QueueNode> check = new PriorityQueue<QueueNode>();
		
		// initialize all nodes and distances
		Map<Node,Double> distances = new HashMap<Node, Double>();
		// TODO: note! this can also include just the shortest path graph and the list could be
		// in the end, on the fly. improves the memory consumption!
		Map<Node,List<Node>> paths = new HashMap<Node, List<Node>>();
		
		for( Node n : edges() ) {
			distances.put( n , INFINITY );
			paths.put( n , new MyList<Node>() );
			check.add( new QueueNode( n , INFINITY )  );
		}
		
		// set current node is zero distance
		distances.put( from, 0.0 );
		paths.get( from ).add( from );
		
		Node current = from;
		
		while( ! check.isEmpty() ) {
			
			double currentDistance = distances.get(current);
			List<Node> currentPath = paths.get(current);
			
			for( Map.Entry<Node, Double> neigbour : current.neighbors().entrySet() ) {
				
				Node n = neigbour.getKey();
				double d = neigbour.getValue();
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
					
					// set new path
					List<Node> newPath = new MyList<Node>();
					newPath.addAll( currentPath );
					newPath.add( n );
					paths.put( n , newPath );
					
					// remove from que && update back to new position
					check.remove( new QueueNode( n , -1 ) );
					check.add( new QueueNode(n, candidate) );
				}
				
			}
			
			// take new node to be checked
			current = check.poll().node();
		}
 		
		return paths.get( to );
	}

}
