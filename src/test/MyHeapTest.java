package test;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import datastructures.*;

public class MyHeapTest {
	
	private MyHeap<Integer,Integer> test;
	
	@Before
	public void setUp() {
		test = new MyHeap<Integer,Integer>();
	}

	@Test
	public void testContains() {
		test.add(1);
		test.add(5);
		assertTrue( "Should be stored", test.contains(1) );
		assertTrue( "Should be stored", test.contains(5) );
		assertFalse( "Should not be stored", test.contains(1) );
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
		test.add(2);
		
		assertEquals("Heap not correct", 1, test.poll().intValue() );
		assertEquals("Heap not correct",  2, test.poll().intValue() );
		assertEquals("Heap not correct", 5, test.poll().intValue() );
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
		for(int i = 500; i > 0; i = i - 3) {
			assertTrue("Element " + i + " should be in heap", test.contains( i ) );
		}
		for(int i = 500; i > 0; i = i - 3) {
			assertEquals("Hash not correct", i, test.remove().intValue() );
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
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsAll() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRetainAll() {
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

}
