package model;
import java.util.List;
import java.util.Map;

import datastructures.MyMap;



public class DikstraPathGraph extends ShortestPathGraph {

	@Override
	public double distance(Node from, Node to) {
		
		// initialize all nodes and distances
		int graphSize = size();
		Map<Node,Double> distances = new MyMap<Node, Double>();
		
		for( Node n : edges() ) {
			distances.put( n, INFINITY );
		}
		
		// set current node is zero distance
		distances.put(from, 0.0 );
		
		List<Node> check = edges();
		Node current = from;
		check.remove( current );
		while( ! check.isEmpty() ) {
			
			double currentDistance = distances.get(current);
			for( Map.Entry<Node, Double> neigbour : current.neighbors().entrySet() ) {
				Node n = neigbour.getKey();
				double d = neigbour.getValue();
				double candidate = currentDistance + d;
				
				// we have a shorter path! update
				if( candidate < distances.get( n ) ) {
					distances.put( n , candidate);
				}
				
				// special case, there is no previous path to n, update!
				if( distances.get( n ) == INFINITY ) {
					distances.put( n, candidate );
				}
			}
			// take new node to be checked
		}
 		
		return distances.get( to );
	}

	@Override
	public List<Node> path(Node from, Node to) {
		// TODO Auto-generated method stub
		return null;
	}

}
