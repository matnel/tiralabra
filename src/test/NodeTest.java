package test;

import static org.junit.Assert.*;

import model.Node;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;

public class NodeTest {
	
	private Node nA;
	private Node nB;
	private Node nC;
	private Node nD;

	@Before
	public void setUp() throws Exception {
		nA = new Node("A");
		nB = new Node("B");
		nC = new Node("C");
		nD = new Node("D");
	}

	@After
	public void tearDown() throws Exception {
		nA = null;
		nB = null;
		nC = null;
		nD = null;
	}

	@Test
	public void testCreation() {
		Node n;
		
		// test default constructor;
		n = new Node("Test1");
		
		assertNotNull("Node not created correctly", n);
		assertEquals("Node has wrong name", "Test1", n.name() );
		
		n = new Node("doqdvbcd");
		assertEquals("Node has wrong name", "doqdvbcd", n.name() );
		
		n = new Node("661919");
		assertEquals("Node has wrong name", "661919", n.name() );
		
		Map<Node,Double> neighbors = new HashMap<Node,Double>();
		neighbors.put(nA, 1.0);
		neighbors.put(nB, 5.0);
		neighbors.put(nC, 10.0);
		
		n = new Node("", neighbors );
		assertEquals("Existing neighbors not stored correctly", neighbors, n.neighbors() );
	}
	
	@Test
	public void testLinking() {
		
		// default behavior
		Map<Node,Double> neighbors = new HashMap<Node,Double>();
		neighbors.put(nA, 0.0);
		neighbors.put(nB, 5.0);
		neighbors.put(nC, 10.0);
		
		Node n = new Node("");
		assertFalse( "Links not yet connected returned true", n.linkTo(nA, 0.0) );
		assertFalse( "Links not yet connected returned true", n.linkTo(nB, 5.0) );
		assertFalse( "Links not yet connected returned true", n.linkTo(nC, 7.0) );
		assertTrue( "Links already connected returned false", n.linkTo(nC, 10.0) );
		
		assertEquals("Links not set correctly", neighbors, n.neighbors() );
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSelfLink() {
		Node n = new Node("");
		n.linkTo(n, 10.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeLink() {
		Node n = new Node("");
		n.linkTo(nA, -10.0);
	}
	
	public void testLinkChecking() {
		Map<Node,Double> neighbors = new HashMap<Node,Double>();
		neighbors.put(nA, 1.0);
		neighbors.put(nB, 5.0);
		neighbors.put(nC, 10.0);
		
		Node n = new Node("", neighbors );
		
		for(Entry<Node,Double> entry : neighbors.entrySet() ) {
			assertTrue("Links doesn't exist between nodes", n.linkTo( entry.getKey() ) );
			assertEquals("The link weight is not correct", entry.getValue().doubleValue(), n.linkWeight( entry.getKey() ) );
		}
		
		assertFalse("Link exists between nodes", n.linkTo(nD) );
		assertEquals("The link weight is not correct", -1.0, n.linkWeight(nD) );
	}

}
