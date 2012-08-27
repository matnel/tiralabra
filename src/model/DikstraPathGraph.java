package model;
import java.util.*;

import datastructures.*;

public class DikstraPathGraph extends ShortestPathGraph {

	@Override
	public double distance(Node from, Node to) {

		// initialize all nodes and distances
		Map<Node,Double> distances = new HashMap<Node, Double>();
		
		
		PriorityQueue<QueueNode> check = new PriorityQueue<QueueNode>();
		
		for( Node n : edges() ) {
			distances.put( n , INFINITY );
			check.add( new QueueNode( n , INFINITY )  );
		}
		
		// set current node is zero distance
		distances.put( from, 0.0 );
		
		
		Node current = from;
		QueueNode qCurrent = null;
		while( ! check.isEmpty() ) {
			double currentDistance = distances.get(current);
			for( Map.Entry<Node, Double> neigbour : current.neighbors().entrySet() ) {
				Node n = neigbour.getKey();
				double d = neigbour.getValue();
				double candidate = currentDistance + d;
				// System.exit(-1);
				
				// System.out.println( n.name() );
				
				// we have a shorter path! update
				if( candidate < distances.get( n ) ) {
					distances.put( n , candidate);
					
					// remove from que && update back with new value
					check.remove( new QueueNode( n , -1 ) );
					check.add( new QueueNode(n, candidate) );
				}
				
				// special case, there is no previous path to n, update!
				if( distances.get( n ) == INFINITY ) {
					distances.put( n, candidate );
					
					// remove from que && update back
					check.remove( new QueueNode( n , -1 ) );
					check.add( new QueueNode(n, candidate) );
					// System.out.println( check.size() );
					
				}
			}
			// take new node to be checked
			current = check.poll().node();
		}
 		
		return distances.get( to );
	}

	@Override
	public List<Node> path(Node from, Node to) {
		// TODO Auto-generated method stub
		return new MyList<Node>();
	}

}
