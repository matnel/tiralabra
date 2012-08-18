package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datastructures.MyMap;

public class MyMapTest {
	
	private MyMap<String, String> map;

	@Before
	public void setUp() throws Exception {
		map = new MyMap<String, String>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClear() {
		map.put("A", "A");
		assertFalse("Map should not be empty", map.isEmpty() );
		map.clear();
		assertTrue("Map should be empty", map.isEmpty() );
	}

	@Test
	public void testContainsKey() {
		map.put("a", "a");
		map.put("A", "A");
		assertFalse("Should not have B", map.containsKey("B") );
		assertTrue("Should have a", map.containsKey("a") );
		assertTrue("Should have A", map.containsKey("A") );
	}

	@Test
	public void testContainsValue() {
		map.put("a", "a");
		map.put("A", "A");
		assertFalse("Should not have B", map.containsValue("B") );
		assertTrue("Should have a", map.containsValue("a") );
		assertTrue("Should have A", map.containsValue("A") );
	}

	@Test
	public void testEntrySet() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		map.put("a", "a");
		map.put("A", "A");
		assertNull("Null must be returned for invalid key", map.get("B") );
		assertEquals("a should be in map", "a", map.get("a") );
		assertEquals("A should be in map", "A", map.get("A") );
	}

	@Test
	public void testIsEmpty() {
		assertTrue( "Map should me empty", map.isEmpty() );
		map.put("A", "A");
		map.put("B", "B");
		assertFalse("Map should not be empty", map.isEmpty() );
		map.remove("B");
		assertFalse("Map should not be empty", map.isEmpty() );
		map.clear();
		assertTrue("Map should be empty", map.isEmpty() );
	}

	@Test
	public void testKeySet() {
		fail("Not yet implemented");
	}

	@Test
	public void testPut() {
		assertNull("No value has been stored previously", map.put("Matti", "Meikäläinen") );
		assertEquals("Meikäläinen has been stored", "Meikäläinen", map.get("Matti") );
		assertEquals("Meikäläinen was stored previously", "Meikäläinen", map.put("Matti", "Mehiläinen") );
		assertEquals("Mehiläinen has been stored", "Mehiläinen", map.get("Matti") );
		
		// store in the same bucket chain
		assertNull("No value has been stored previously", map.put("3","3") );
		assertEquals("3 has been stored", "3", map.get("3") );
		
		// couse a rehash
		for(int i = -10; i < 10; i++) {
			String t = "" + i;
			map.put( t, t );
			assertEquals("Missing value for " + t, t, map.get(t) );
		}
		assertEquals("Missing value Mehiläinen", "Mehiläinen", map.get("Matti") );
	}

	@Test
	public void testPutAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		assertEquals( "Map should be empty", 0, map.size() );
		map.put("A", "A");
		assertEquals( "Map should have 1 element", 1, map.size() );
		map.put("B", "B");
		assertEquals( "Map should have 2 element", 2, map.size() );
		map.remove("B");
		assertEquals( "Map should have 1 element", 1, map.size() );
		map.clear();
		assertEquals( "Map should be empty", 0, map.size() );
	}

	@Test
	public void testValues() {
		fail("Not yet implemented");
	}

}
