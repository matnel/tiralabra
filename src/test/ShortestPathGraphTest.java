package test;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import model.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ShortestPathGraphTest {
	
	ShortestPathGraph graph = new AStarPathGraph();
	
	HashMap<String,Node> nodes = new HashMap<String,Node>();

	@Before
	public void setUp() throws Exception {
		
		for(int i = 0; i < 13; i++) {
			String name = "" + i;
			nodes.put( name, new Node(name) );
		}
		
		// TODO: move this code to Graph, addAll()
		for( Node n : nodes.values() ) {
			graph.addNode(n);
		}
	}

	@After
	public void tearDown() throws Exception {
	}
	
	public void testUnconnected() {	
		// element 0 is the unconnected
		Node zero = nodes.get("0");
		Collection<Node> others = nodes.values();
		others.remove(zero);
		for(Node n : others ) {
			assertEquals("Node 0 is not connected", Integer.MIN_VALUE, graph.distance(zero, n) );
			assertEquals("Node 0 is not connected", Integer.MIN_VALUE, graph.distance(n, zero) );
			assertTrue("There can not be connection between 0 and " + n, graph.path(zero, n).isEmpty() );
			assertTrue("There can not be connection between " + n + " and 0", graph.path(n, zero).isEmpty() );
		}
	}

}
