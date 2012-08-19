package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datastructures.MySet;

public class MySetTest {
	
	MySet<Integer> set = new MySet<Integer>();

	@Test
	public void testAddE() {
		// fill the set up
		for(int i = 0; i < 10; i++) {
			assertTrue( "Adding unique element to set failed", set.add(i) );
		}
		assertFalse( "Adding duplicate elements to set success", set.add(0) );
		assertEquals("There should be only one 0", 0, set.lastIndexOf(0) );
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testAddIntE() {
		set.add(0, 10);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testAddAllIntECollectionOfQextendsE() {
		Collection<Integer> c = new ArrayList<Integer>();
		c.add(10);
		c.add(0);
		set.addAll(0, c);
	}

	@Test
	public void testAddAllCollectionOfQextendsE() {
		set.add(0);
		
		Collection<Integer> c = new ArrayList<Integer>();
		c.add(10);
		c.add(0);
		assertTrue("Adding unique elements should be succesfull", set.addAll(c) );
		assertEquals("0 is in correct position", 0, set.lastIndexOf(0) );
		assertEquals("10 is in correct place", 1, set.lastIndexOf(10) );
		assertEquals("The set is correct size", 2, set.size() );
		assertFalse( "Adding duplicate elements should not be succesfull", set.addAll(c) );
	}

}
