package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.tools.jdi.EventSetImpl.Itr;

import datastructures.*;

public class MyIteratorTest {
	
	ListIterator<Integer> iterator;
	List<Integer> values;

	@Before
	public void setUp() throws Exception {
		
		int[] temp = {5,7,9,1,2};
		values = new MyList<Integer>();
		
		for(int v : temp) {
			values.add( v );
		}
		
		iterator = new MyIterator<Integer>( values );
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIterator() {
		int[] values = {5,7,9,1,2};
		List<Integer> list = new MyList<Integer>();
		
		for(int v : values) {
			list.add( v );
		}
		
		Iterator iter = new MyIterator<Integer>(list);
		
		for( int v : values ) {
			System.out.println( v );
			assertEquals("Value incorrect from iterator", v, iter.next() );
		}
		
		// check that we have not modified the list given in param
		for( int i = 0; i < values.length; i++ ) {
			assertEquals("The original list has been changed!", values[i], list.get(i).intValue() );
		}
		
	}

	@Test
	public void testHasNext() {
		for( int i = 0; i < values.size(); i++ ) {
			assertTrue( "There should be a new value", iterator.hasNext() );
			iterator.next();
		}
		assertFalse( "There should not be any values", iterator.hasNext() );
	}

	@Test
	public void testNext() {
		for( int i = 0; i < values.size(); i++ ) {
			assertEquals( "Correct value should come from the iterator", values.get(i), iterator.next() );
		}
	}
	
	@Test
	public void testNextIndex() {
		for( int i = 0; i < values.size(); i++ ) {
			assertEquals( "Correct value should come from the iterator", i + 1, iterator.nextIndex() );
			iterator.next();
		}
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testNextException() {
		for( int i = 0; i < values.size(); i++ ) {
			iterator.next();
		}
		iterator.next();
	}

	@Test
	public void testRemove() {
		iterator.remove();
		assertEquals("After removing, a correct value should be given", values.get(1), iterator.next() );
		iterator.remove();
		iterator.remove();
		assertEquals("After removing, a correct value should be given", values.get(4), iterator.next() );
	}

	@Test
	public void testHasPrevious() {
		assertFalse("When not iterated, there should not be a previous value", iterator.hasPrevious() );
		iterator.next();
		assertFalse("When not iterated, there should not be a previous value", iterator.hasPrevious() );
		iterator.next();
		assertTrue("There shold be a previous value", iterator.hasPrevious() );
	}

	@Test
	public void testPrevious() {
		iterator.next();
		for( int i = 0; i < values.size() - 1; i++ ) {
			iterator.next();
			assertEquals( "Correct value should come from the iterator", values.get(i), iterator.previous() );
		}
	}

	@Test
	public void testPreviousIndex() {
		for( int i = 0; i < values.size(); i++ ) {
			iterator.next();
			assertEquals( "Correct value should come from the iterator", i, iterator.previousIndex() );
		}
	}

	@Test
	public void testSet() {
		iterator.next();
		iterator.set( 3 );
		assertEquals(3, iterator.next().intValue() );
		assertEquals(7, iterator.next().intValue() );
	}
	
	@Test
	public void testAdd() {
		iterator.add( 1 );
		for( int i = 0; i < 5; i++ ) {
			iterator.next();
		}
		assertEquals(1, iterator.next().intValue() );
	}

}
