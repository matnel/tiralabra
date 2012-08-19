package test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datastructures.MyList;
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
	public void testPut() {
		assertNull("No value has been stored previously", map.put("Matti", "Meik�l�inen") );
		assertEquals("Meik�l�inen has been stored", "Meik�l�inen", map.get("Matti") );
		assertEquals("Meik�l�inen was stored previously", "Meik�l�inen", map.put("Matti", "Mehil�inen") );
		assertEquals("Mehil�inen has been stored", "Mehil�inen", map.get("Matti") );
		
		// store in the same bucket chain
		assertNull("No value has been stored previously", map.put("3","3") );
		assertEquals("3 has been stored", "3", map.get("3") );
		
		// couse a rehash
		for(int i = -10; i < 10; i++) {
			String t = "" + i;
			map.put( t, t );
			assertEquals("Missing value for " + t, t, map.get(t) );
		}
		assertEquals("Missing value Mehil�inen", "Mehil�inen", map.get("Matti") );
	}

	@Test
	public void testPutAll() {
		Map<String,String> m = new HashMap<String,String>();
		
		map.put("A", "1");
		
		m.put("A", "2");
		m.put("B", "1");
		
		map.putAll( m );
		
		assertEquals( "Map should have 2 elements", 2, map.size() );
		assertEquals( "A should have value 2", "2", map.get("A") );
		assertEquals( "B should have value 1", "1", map.get("B") );
	}

	@Test
	public void testRemove() {
		assertEquals("No changes has been made to the list", null, map.remove("Kissa") );
		map.put("Matti", "Meik�l�inen");
		assertEquals("Meik�l�inen was the old value", "Meik�l�inen", map.remove("Matti") );
		assertFalse("Matti should no longer be in list", map.containsKey("Matti") );
		assertFalse("Meik�l�inen should no longer be in list", map.containsValue("Meik�l�inen") );
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
	public void testEntrySet() {
		String[] k = { "Matti", "Maija", "Mikko"};
		String[] v = { "Meik�l�inen", "Mattila", "Meik�l�inen" };
		
		for(int i = 0; i < k.length; i++) {
			map.put( k[i], v[i] );
		}
		
		map.put("Matti", "Mattila");
		map.put("Matti", "Meik�l�inen");
		
		List<String> keys = new MyList<String>();
		List<String> values = new MyList<String>();
		
		for( Map.Entry<String, String> entry : map.entrySet() ) {
			keys.add( entry.getKey() );
			values.add( entry.getValue() );
		}
		
		assertEquals("There should be three values stored", 3, keys.size() );
		
		for( int i = 0; i < k.length; i++) {
			assertTrue( k[i] + " should be a key", keys.contains( k[i] ) );
			assertTrue( v[i] + " should be a value", values.contains( v[i] ) );
		}
		
	}

	@Test
	public void testValues() {
		map.put("Matti", "Meik�l�inen");
		map.put("Maija", "Mattila");
		map.put("Mikko", "Meik�l�inen");
		Collection c = map.values();
		assertTrue("Meik�l�inen should be value", c.contains("Meik�l�inen") );
		assertTrue("Mattila should be value", c.contains("Mattila") );
		assertEquals("There should be 3 values", 3, c.size() );
		
	}
	
	@Test
	public void testKeySet() {
		map.put("Matti", "Meik�l�inen");
		map.put("Maija", "Mattila");
		Collection c = map.keySet();
		assertTrue("Matti should be value", c.contains("Matti") );
		assertTrue("Maija should be value", c.contains("Maija") );
		assertEquals("There should be 2 values", 2, c.size() );
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


}
