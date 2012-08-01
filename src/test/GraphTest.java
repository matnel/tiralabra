package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GraphTest {

	private Graph g;
	private Node nA;
	private Node nB;
	private Node nC;
	private Node nD;
	
	
	@Before
	public void setUp() throws Exception {
		g = new Graph();
		nA = new Node("A");
		nB = new Node("B");
		nC = new Node("C");
		nD = new Node("D");
	}

	@After
	public void tearDown() throws Exception {
		g = null;
		nA = null;
		nB = null;
		nC = null;
		nD = null;
	}

	@Test
	public void testAddNode() {
		assertTrue("Adding node to list should return true", g.addNode(nA) );
		
		assertFalse("Adding same node to list should return false", g.addNode(nA) );
		
		Node nX = new Node("A");
		assertFalse("Adding node with same name should return false", g.addNode(nX) );
		
		assertEquals("Too many elements in the graph", 1, g.size() );
	}

	@Test
	public void testSize() {
		assertEquals("Empty graph should be size 0", 0, g.size() );
		
		g.addNode(nA);
		assertEquals("Graph should have 1 node", 1, g.size() );
		
		g.addNode(nB);
		assertEquals("Graph should have 2 nodes", 2, g.size() );
		
		g.addNode(nC);
		assertEquals("Graph should have 3 nodes", 3, g.size() );
	}
	
	@Test
	public void testEdges() {
		List<Node> list = new ArrayList<Node>();
		
		assertEquals("Empty graph should return empty list", list, g.edges() );
		
		g.addNode(nA);
		list.add(nA);
		assertEquals("List not correct", list, g.edges() );
		
		g.addNode(nB);
		list.add(nB);
		assertEquals("List not correct", list, g.edges() );
		
		g.addNode(nC);
		list.add(nC);
		assertEquals("List not correct", list, g.edges() );
	}

	@Test
	public void testMatrix() {
		assertEquals("Empty graph should return empty matrix", 0, g.matrix().length );
		
		nA.linkTo( nB, 10 );
		nA.linkTo( nC, 100 );
		g.addNode( nA );
		
		assertEquals("Graph with just one node should return 1x1 graph", 1, g.matrix().length );
		assertEquals("Graph with just one node should return 1x1 graph", 1, g.matrix()[0].length );
		assertEquals("Value to one self should be 0", 0, g.matrix()[0][0], 0.001 );
		
		nB.linkTo(nA, 5);
		g.addNode( nB );
		g.addNode( nC );
		
		double[][] result = { {0,10,100}, {5,0,-1}, {-1,-1,0} };
		assertArrayEquals("Matrix not generated correctly", result, g.matrix() );
	}

	@Test
	public void testHasNode() {
		
		g.addNode(nA);
		g.addNode(nB);
		
		assertTrue("Node should be in graph", g.has(nA) );
		assertTrue("Node should be in graph", g.has(nB) );
		
		assertFalse("Node should not be in graph", g.has(nC) );
		
		Node nX = new Node("A");
		
		assertFalse("Node with given name is in graph, but it's not this node", g.has(nX) );
		
	}

	@Test
	public void testHasString() {
		g.addNode(nA);
		g.addNode(nB);
		
		assertTrue("Node should be in graph", g.has("A") );
		assertTrue("Node should be in graph", g.has("B") );
		
		assertFalse("Node should not be in graph", g.has("C") );
	}

}
