package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

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
		assertEquals("List should have 103 elements", 103, list.size() );
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testAddIntEInvalid() {
		list.add(1, "Kissa");
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testAddIntEInvalid2() {
		list.add(-1, "Kissa");
	}

	@Test
	public void testAddAllCollectionOfQextendsE() {
		ArrayList<String> c = new ArrayList<String>();
		c.add("Kissa");
		c.add("Koira");
		list.add("Kirahvi");
		list.addAll( c );
		assertEquals("List should have 3 elements", 3, list.size() );
		for(int i = 0; i < c.size(); i++ ) {
			assertEquals("Value is not in correct position",  c.get(i) , list.get( 1 + i ) );
		}
	}

	@Test
	public void testAddAllIntCollectionOfQextendsE() {
		ArrayList<String> c = new ArrayList<String>();
		c.add("Kissa");
		c.add("Koira");
		list.add("Kirahvi");
		list.add("Karahvi");
		list.addAll(0, c);
		assertEquals("List should have 3 elements", 4, list.size() );
		for(int i = 0; i < c.size(); i++ ) {
			assertEquals("Value is not in correct position",  c.get(i) , list.get( 1 + i ) );
		}
		assertEquals("Karahvi should still be on the list", "Karahvi", list.get(3) );
	}

	@Test
	public void testClear() {
		// fill list with stuff
		for( int i = 0; i < 100; i++ ) {
			list.add("Kissa");
		}
		list.clear();
		assertFalse("List should be empty", list.contains("Kissa"));
		assertEquals("List should be empty", 0, list.size() );
	}

	@Test
	public void testContains() {
		assertFalse("Kissa is not in the list" , list.contains("Kissa") );
		list.add("Kissa");
		list.add("Koira");
		assertFalse("kissa is not in the list" , list.contains("kissa") );
		assertFalse("Kirahvi is not in the list" , list.contains("Kirahvi") );
		assertTrue("Kissa is on the list" , list.contains("Kissa") );
		assertTrue("Koira is on the list" , list.contains("Koira") );
	}

	@Test
	public void testContainsAll() {
		ArrayList<String> test = new ArrayList<String>();
		test.add("Kissa");
		test.add("Koira");
		assertFalse("List does not contain Kissa and Koira", list.containsAll( test ) );
		list.add("Kissa");
		assertFalse("List does not contain Kissa and Koira", list.containsAll( test ) );
		list.add("Koira");
		assertTrue("List contains Kissa and Koira", list.containsAll( test ) );
	}

	@Test
	public void testGet() {
		for(int i = 0; i < 100; i++) {
			list.add("" + i);
		}
		for(int i = 0; i < 100; i++) {
			assertEquals("List should have " + i, "" + i, list.get(i) );
		}
		list.add("Kissa");
		list.add("101");
		assertEquals("List should have 101", "101", list.get(101) );
	}

	@Test
	public void testIndexOf() {
		list.add("Kissa");
		list.add("Koira");
		list.add("Koira");
		list.add("Koira");
		list.add("Kirahvi");
		list.add("Kissa");
		assertEquals("Kameli is not part of the list", -1, list.indexOf("Kameli") );
		assertEquals("First instance of Kissa is at 0", 0, list.indexOf("Kissa") );
		assertEquals("First instance of Koira is at 1", 1, list.indexOf("Koira") );
		assertEquals("First instance of Kirahvi is at 4", 4, list.indexOf("Kirahvi") );		
	}

	@Test
	public void testIsEmpty() {
		assertTrue("List should be empty", list.isEmpty() );
		list.add("Kissa");
		assertFalse("List should not be empty", list.isEmpty() );
		list.remove("Kissa");
		assertTrue("List should be empty", list.isEmpty() );
	}

	@Test
	public void testLastIndexOf() {
		list.add("Kissa");
		list.add("Koira");
		list.add("Koira");
		list.add("Koira");
		list.add("Kirahvi");
		list.add("Kissa");
		assertEquals("Kameli is not part of the list", -1, list.lastIndexOf("Kameli") );
		assertEquals("Last instance of Kissa is at 5", 5, list.lastIndexOf("Kissa") );
		assertEquals("First instance of Koira is at 3", 3, list.lastIndexOf("Koira") );
		assertEquals("First instance of Kirahvi is at 4", 4, list.lastIndexOf("Kirahvi") );
	}


	@Test
	public void testRemoveObject() {
		list.add("Kissa");
		list.add("Koira");
		list.add("Koira");
		list.add("Koira");
		list.add("Kirahvi");
		list.add("Kissa");
		
		// remove element not in the list
		assertFalse("Removing element not in the list should return false", list.remove("Karahvi") );
		assertEquals("Removing karahvi changed something", 6, list.size() );
		
		assertTrue("Removing Kissa should be ok", list.remove("Kissa") );
		assertEquals("Other instances of kissa should still be", 4, list.indexOf("Kissa") );
		
		assertTrue("Removing Kirahvi should be ok", list.remove("Kirahvi") );
		assertFalse("Kirahvi should not be in the list anymore", list.contains("Kirahvi") );
	}

	@Test
	public void testRemoveInt() {
		list.add("Kissa");
		list.add("Koira");
		list.add("Koira");
		list.add("Koira");
		list.add("Kirahvi");
		list.add("Kissa");
		
		
		list.remove(0);
		assertEquals("New zero should be Koira", "Koira", list.get(0) );
		assertEquals("Other instances of kissa should still be", 4, list.indexOf("Kissa") );
		
		list.remove(3);
		assertEquals("New 3 should be Kissa", "Kissa", list.get(3) );
	}

	@Test
	public void testRemoveAll() {
		list.add("Kissa");
		list.add("Koira");
		list.add("Koira");
		list.add("Koira");
		list.add("Kirahvi");
		list.add("Kissa");
		
		ArrayList<String> test = new ArrayList<String>();
		test.add("Kissa");
		test.add("Karahvi");
		
		assertTrue("List has been changed", list.removeAll( test ) );
		assertEquals("Kissa is now at 4", 4, list.indexOf("Kissa") );
		assertTrue("List has been changed", list.removeAll( test ) );
		assertFalse("List has not been changed", list.removeAll( test ) );
		
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
	
	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRetainAll() {
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

}
