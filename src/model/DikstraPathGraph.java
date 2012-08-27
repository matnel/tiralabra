package model;
import java.util.*;

import datastructures.*;

public class DikstraPathGraph extends ShortestPathGraph {

	@Override
	public double distance(Node from, Node to) {
		
		List<Node> path = path(from, to);
		
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

		/*
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
		while( ! check.isEmpty() ) {
			double currentDistance = distances.get(current);
			for( Map.Entry<Node, Double> neigbour : current.neighbors().entrySet() ) {
				Node n = neigbour.getKey();
				double d = neigbour.getValue();
				double candidate = currentDistance + d;
				
				// special case, there is no previous path to n, update!
				if( distances.get( n ) == INFINITY ) {
					distances.put( n, candidate );
					
					// remove from que && update back
					check.remove( new QueueNode( n , -1 ) );
					check.add( new QueueNode(n, candidate) );
					// System.out.println( check.size() );
					
				}
				
				// we have a shorter path! update
				if( candidate < distances.get( n ) ) {
					distances.put( n , candidate);
					
					// remove from que && update back with new value
					check.remove( new QueueNode( n , -1 ) );
					check.add( new QueueNode(n, candidate) );
				}
				
			}
			// take new node to be checked
			current = check.poll().node();
		}
 		
		return distances.get( to );
		*/
	}

	@Override
	public List<Node> path(Node from, Node to) {
		// initialize all nodes and distances
		Map<Node,Double> distances = new HashMap<Node, Double>();
		Map<Node,List<Node>> paths = new HashMap<Node, List<Node>>();
		
		
		PriorityQueue<QueueNode> check = new PriorityQueue<QueueNode>();
		
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
				
				// special case, there is no previous path to n, update!
				if( distances.get( n ) == INFINITY ) {
					distances.put( n, candidate );
					
					List<Node> newPath = new MyList<Node>();
					newPath.addAll( currentPath );
					newPath.add( n );
					paths.put( n , newPath );
					
					// remove from que && update back
					check.remove( new QueueNode( n , -1 ) );
					check.add( new QueueNode(n, candidate) );
					// System.out.println( check.size() );
					
				}
				
				// we have a shorter path! update
				if( candidate < distances.get( n ) ) {
					distances.put( n, candidate );
					
					List<Node> newPath = new MyList<Node>();
					newPath.addAll( currentPath );
					newPath.add( n );
					paths.put( n , newPath );
					
					// remove from que && update back
					check.remove( new QueueNode( n , -1 ) );
					check.add( new QueueNode(n, candidate) );
				}
				
			}
			// take new node to be checked
			current = check.poll().node();
		}
 		
		System.out.println( paths.get( to ).size() );
		return paths.get( to );
	}

}
