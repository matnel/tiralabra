package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datastructures.MyList;

public class MyListTest {
	
	MyList<String> list;

	@Before
	public void setUp() throws Exception {
		list = new MyList<String>();
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}

	@Test
	public void testAddE() {
		list.add("Kissa");
		assertTrue("Kissa should be in list", list.contains("Kissa"));
		assertTrue("Adding to list should always return true", list.add("Koira") );
		assertTrue("Adding to list should always return true", list.add("Kissa") );
		assertEquals("Kissa should be the first item", "Kissa", list.get(0) );
		assertEquals("Koira should be the second item", "Koira", list.get(1) );
		assertEquals("Kissa should be the third item", "Kissa", list.get(2) );
	}

	@Test
	public void testAddIntE() {
		// fill the list
		for( int i = 0; i < 100; i++ ) {
			list.add("Kissa");
		}
		list.add(0, "Koira");
		list.add(5, "Koira");
		list.add(10, "Koira");
		assertEquals("Koira should be at 0", "Koira", list.get(0) );
		assertEquals("Koira should be at 5", "Koira", list.get(5) );
		assertEquals("Koira should be at 10", "Koira", list.get(10) );
		// assertEquals("List should have 103 elements", 103, list.size() );
	}

	@Test
	public void testAddAllCollectionOfQextendsE() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAllIntCollectionOfQextendsE() {
		fail("Not yet implemented");
	}

	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

	@Test
	public void testContains() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexOf() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmpty() {
		fail("Not yet implemented");
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	public void testLastIndexOf() {
		fail("Not yet implemented");
	}

	@Test
	public void testListIterator() {
		fail("Not yet implemented");
	}

	@Test
	public void testListIteratorInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetainAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testSet() {
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubList() {
		fail("Not yet implemented");
	}

	@Test
	public void testToArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToArrayTArray() {
		fail("Not yet implemented");
	}

}
