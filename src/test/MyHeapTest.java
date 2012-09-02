package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import datastructures.*;

public class MyHeapTest {
	
	private MyHeap<Integer> test;
	
	@Before
	public void setUp() {
		test = new MyHeap<Integer>();
	}

	@Test
	public void testContains() {
		test.add(1);
		test.add(5);
		assertFalse( "Should not be stored", test.contains(10) );
		assertTrue( "Should be stored", test.contains(1) );
		assertTrue( "Should be stored", test.contains(5) );
	}

	@Test
	public void testRemoveObject() {
		test.add(1);
		test.add(5);
		assertTrue("Hash should be changed" , test.remove(1) );
		assertFalse("Hash should not be changed" , test.remove(1) );
		assertTrue("Hash should be changed" , test.remove(5) );
		
		assertFalse("Should not be in list", test.contains(1) );
		assertFalse("Should not be in list", test.contains(2) );
		assertFalse("Should not be in list", test.contains(5) );
	}
	
	// POLLING
	
	@Test
	public void testPoll() {
		test.add(5);
		test.add(1);
		test.add(7);
		test.add(9);
		test.add(2);
		test.add(3);

		
		assertEquals("Heap not correct", 1, test.poll().intValue() );
		assertEquals("Heap not correct", 2, test.poll().intValue() );
		assertEquals("Heap not correct", 3, test.poll().intValue() );
		assertEquals("Heap not correct", 5, test.poll().intValue() );
		assertEquals("Heap not correct", 7, test.poll().intValue() );
		assertEquals("Heap not correct", 9, test.poll().intValue() );
		assertEquals("Empty heap should return null", null, test.poll() );
	}
	
	@Test
	public void testRemove() {
		test.add(5);
		test.add(1);
		test.add(2);
		
		assertEquals("Heap not correct", 1, test.remove().intValue() );
		assertEquals("Heap not correct", 2, test.remove().intValue() );
		assertEquals("Heap not correct", 5, test.remove().intValue() );
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testRemoveError() {
		test.remove();
	}
	
	// PEEKING

	@Test
	public void testPeek() {
		test.add(5);
		test.add(1);
		test.add(2);
		
		assertEquals("Heap not correct", 1, test.peek().intValue() );
		assertEquals("Heap not correct", 1, test.peek().intValue() );
		test.remove();
		assertEquals("Heap not correct", 2, test.peek().intValue() );
		test.remove();
		test.remove();
		assertEquals("Empty heap should be null", null, test.peek() );
	}
	
	@Test
	public void testElement() {
		test.add(5);
		test.add(1);
		test.add(2);
		
		assertEquals("Heap not correct", 1, test.element().intValue() );
		assertEquals("Heap not correct",  1, test.element().intValue() );
		test.remove();
		test.remove();
		assertEquals("Heap not correct", 5, test.element().intValue() );
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testElementError() {
		test.element();
	}
	
	// ADDING
	
	@Test
	public void testAdd() {
		for(int i = 500; i > 0; i = i - 3) {
			test.add(i);
		}
		System.out.println("POLLS");
		for(int i = 2; i <= 500; i = i + 3) {
			assertEquals("Hash not correct", i, test.poll().intValue() );
		}
	}
	
	@Test
	public void testOffer() {
		test.offer(5);
		test.offer(1);
		test.offer(2);
		assertEquals("Heap not correct", 1, test.peek().intValue() );
		assertEquals("Heap not correct", 1, test.peek().intValue() );
		test.poll();
		assertEquals("Heap not correct", 2, test.peek().intValue() );
	}

	@Test
	public void testSize() {
		assertEquals( "Empty heap should be empty", 0, test.size() );
		test.add(1);
		assertEquals( "There should be content", 1, test.size() );
		test.add(2);
		assertEquals( "There should be content", 2, test.size() );
		test.remove();
		assertEquals( "There should be content", 1, test.size() );
		test.remove();
		assertEquals( "Empty heap should be empty", 0, test.size() );
	}

	@Test
	public void testIsEmpty() {
		assertTrue( "Empty heap should be empty", test.isEmpty() );
		test.add(1);
		test.add(2);
		assertFalse( "Heap now has content", test.isEmpty() );
		test.remove();
		assertFalse( "Heap now has content", test.isEmpty() );
		test.remove();
		assertTrue( "Empty heap should be empty", test.isEmpty() );
	}

	@Test
	public void testClear() {
		test.add(1);
		test.add(2);
		test.clear();
		assertTrue("Cleared list should be empty", test.isEmpty() );
	}

	@Test
	public void testAddAll() {
		Collection<Integer> c = new ArrayList<Integer>();
		c.add(0);
		c.add(1);
		c.add(2);
		test.addAll( c );
		for( Integer i : c ) {
			assertEquals("Heap not correct", i, test.poll() );
		}
	}

	@Test
	public void testRemoveAll() {
		Collection<Integer> c = new ArrayList<Integer>();
		c.add(0);
		c.add(1);
		c.add(2);
		test.addAll( c );
		c.remove( 2 );
		Collection<Integer> c1 = new ArrayList<Integer>();
		c1.add( 4 );
		assertFalse( test.removeAll( c1 ) );
		assertTrue( test.removeAll( c ) );
		assertEquals("Size not correct", 1, test.size() );
		assertEquals("Heap not correct", 2, test.poll().intValue() );
	}

	@Test
	public void testContainsAll() {
		Collection<Integer> c = new ArrayList<Integer>();
		c.add(0);
		c.add(1);
		c.add(2);
		test.addAll( c );
		assertTrue("Not all elements in heap", test.containsAll( c ) );
	}
	
	@Test
	public void testRetainAll() {
		Collection<Integer> c = new ArrayList<Integer>();
		c.add(0);
		c.add(1);
		c.add(2);
		test.addAll( c );
		assertFalse( test.retainAll( c ) );
		c.remove(1);
		assertTrue( test.retainAll( c ) );
		assertEquals("Heap not correct", 0, test.poll().intValue() );
		assertEquals("Heap not correct", 2, test.poll().intValue() );
	}

	@Test
	public void testToArray() {
		Integer[] values = { 0, 1, 2 };
		for( Integer i : values ){
			test.add( i );
		}
		assertArrayEquals( values, test.toArray() );
	}

	@Test
	public void testToArrayTArray() {
		Integer[] values = { 0, 1, 2 };
		for( Integer i : values ){
			test.add( i );
		}
		Integer[] target = new Integer[3];
		assertArrayEquals( values, test.toArray( target ) );
	}

	@Test
	public void testIterator() {
		Collection<Integer> c = new ArrayList<Integer>();
		c.add(0);
		c.add(2);
		c.add(1);
		test.addAll( c );
		Iterator i = test.iterator();
		assertEquals( 0 , i.next() );
		assertEquals( 1 , i.next() );
		assertEquals( 2 , i.next() );
		assertTrue("Heap should not be modified", test.containsAll( c ) );
	}

}
