package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ShortestPathGraphTest {
	
	ShortestPathGraph graph = new DikstraPathGraph();
	
	HashMap<Integer,Node> nodes = new HashMap<Integer,Node>();

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
	
	
	@Test
	public void testUnconnected() {	
		// element 0 is the unconnected
		Node zero = nodes.get(0);
		Collection<Node> others = nodes.values();
		others.remove(zero);
		for(Node n : others ) {
			assertEquals("Node 0 is not connected", ShortestPathGraph.INFINITY , graph.distance(zero, n) , 0.01 );
			assertEquals("Node 0 is not connected", ShortestPathGraph.INFINITY, graph.distance(n, zero) , 0.01 );
			assertTrue("There can not be connection between 0 and " + n, graph.path(zero, n).isEmpty() );
			assertTrue("There can not be connection between " + n + " and 0", graph.path(n, zero).isEmpty() );
		}
	}
	
	@Test
	public void testUnidirected() {
		Node from = nodes.get(12);
		Node to = nodes.get(11);
		
		assertEquals("Path from 12 to 11 should be there", 1 , graph.distance(from, to) , 0.01 );
		assertEquals("Path from 11 to 12 should not be there", ShortestPathGraph.INFINITY , graph.distance(to, from) , 0.01 );
	}
	
	@Test
	public void testNextTo() {
		List<Node> links;
		for( Node central : nodes.values() ) {
			for( Map.Entry<Node, Double> target : central.neighbors().entrySet() ) {
				assertEquals("The distance between neigbour nodes is incorrect", target.getValue().doubleValue(), graph.distance( central, target.getKey() ) , 0.01 );
				links = new ArrayList<Node>();
				links.add( central );
				links.add( target.getKey() );
				
				assertEquals("The shortest path between neigbours goes troug them", links, graph.path(central, target.getKey() ) );
				
			}
		}
		
	}
	
	@Test
	public void testPaths1() {
		
		List<Node> correct = new ArrayList<Node>();
		Node from = nodes.get(1);
		Node to = nodes.get(5);
		
		assertEquals("Distance from 1 to 5 incorrect", 4, graph.distance(from, to) , 0.01 );
		
		correct.add( from );
		correct.add( nodes.get(4) );
		correct.add( nodes.get(8) );
		correct.add( to );
		
		assertEquals("The path from 1 to 5 incorrect", correct, graph.path(from, to) );
		
	}
	
	@Test
	public void testPaths2() {
		
		List<Node> correct = new ArrayList<Node>();
		Node from = nodes.get(5);
		Node to = nodes.get(6);
		
		assertEquals("Distance from 5 to 6 incorrect", 8, graph.distance(from, to) , 0.01 );
		
		correct.add( from );
		correct.add( nodes.get(2) );
		correct.add( nodes.get(3) );
		correct.add( to );
		
		assertEquals("The path from 5 to 6 incorrect", correct, graph.path(from, to) );
		
	}
	
	@Test
	public void testPaths3() {
		
		List<Node> correct = new ArrayList<Node>();
		Node from = nodes.get(1);
		Node to = nodes.get(11);
		
		assertEquals("Distance from 1 to 11 incorrect", 6, graph.distance(from, to) , 0.01 );
		
		correct.add( from );
		correct.add( nodes.get(2) );
		correct.add( nodes.get(3) );
		correct.add( nodes.get(5) );
		correct.add( to );
		
		assertEquals("The path from 1 to 11 incorrect", correct, graph.path(from, to) );
		
	}

}
