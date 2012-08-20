package test;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ShortestPathGraphTest {
	
	ShortestPathGraph graph = new AStarPathGraph();
	
	HashMap<Integer,Node> nodes = new HashMap<integer,Node>();

	@Before
	public void setUp() throws Exception {
		
		for(int i = 0; i < 13; i++) {
			String name = "" + i;
			nodes.put( i, new Node(name) );
		}
		
		// TODO: move this code to Graph, addAll()
		for( Node n : nodes.values() ) {
			graph.addNode(n);
		}
		
		// connect nodes
		nodes.get(1).linkTo( nodes.get(2), 1 );
		nodes.get(1).linkTo( nodes.get(4), 2 );
		nodes.get(2).linkTo( nodes.get(3), 1 );
		nodes.get(2).linkTo( nodes.get(5), 4 );
		nodes.get(3).linkTo( nodes.get(6), 3 );
		nodes.get(4).linkTo( nodes.get(5), 4 );
		nodes.get(4).linkTo( nodes.get(8), 1 );
		nodes.get(5).linkTo( nodes.get(8), 1 );
		nodes.get(5).linkTo( nodes.get(9), 1 );
		nodes.get(6).linkTo( nodes.get(7), 3 );
		nodes.get(6).linkTo( nodes.get(10), 2 );
		nodes.get(6).linkTo( nodes.get(11), 1 );
		nodes.get(7).linkTo( nodes.get(11), 3 );
		nodes.get(9).linkTo( nodes.get(10), 6 );
		nodes.get(10).linkTo( nodes.get(11), 4 );
		
		// make graph symmetric
		for( Node node : nodes.values() ) {
			for( Map.Entry<Node, Double> n : node.neighbors().entrySet() ) {
				n.getKey().linkTo( node , n.getValue() );
			}
		}
		
		// one unidirected connection
		nodes.get(12).linkTo( nodes.get(11), 1 );
	}

	@After
	public void tearDown() throws Exception {
	}
	
	public void testUnconnected() {	
		// element 0 is the unconnected
		Node zero = nodes.get(0);
		Collection<Node> others = nodes.values();
		others.remove(zero);
		for(Node n : others ) {
			assertEquals("Node 0 is not connected", Integer.MIN_VALUE, graph.distance(zero, n) );
			assertEquals("Node 0 is not connected", Integer.MIN_VALUE, graph.distance(n, zero) );
			assertTrue("There can not be connection between 0 and " + n, graph.path(zero, n).isEmpty() );
			assertTrue("There can not be connection between " + n + " and 0", graph.path(n, zero).isEmpty() );
		}
	}
	
	public void testUnidirected() {
		Node from = nodes.get(12);
		Node to = nodes.get(11);
		
		assertEquals("Path from 12 to 11 should be there", 1, graph.distance(from, to) );
		assertEquals("Path from 11 to 12 should not be there", 1, graph.distance(to, from) );
	}
	
	public void testNextTo() {
		
	}

}
