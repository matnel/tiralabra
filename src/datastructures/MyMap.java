package datastructures;

import java.util.*;

/**
 * Implelemts a hash table.
 * */
public class MyMap<K, V> implements Map<K, V> {
	
	/**
	 * Initial size of the map
	 * */
	private static int INITIAL_SIZE = 10;
	
	/**
	 * At what stage should the system be rehased
	 * */
	private static double REHASH_TRESHOLD = 0.9;
	
	/**
	 * Keeper, to store the number of elements in this datastructure
	 * */
	private int count = 0;
	
	/**
	 * The map storing all items.
	 * */
	private Object[] map = new Object[ INITIAL_SIZE ];
	
	// PRIVATE help functions
	
	/**
	 * Returns the index where key should be stored.
	 * 
	 * @return index where k should be stored in map
	 * */
	private int hash(K key) {
		if( key == null ) return 0;
		return key.hashCode() % map.length;
	}
	
	/**
	 * Returns all buckets stored in the map. I.e. all keys and values stored in the system.
	 * 
	 * @return all buckets stored in the map
	 * */
	private List<Bucket> allBuckets() {
		List<Bucket> buckets = new MyList<Bucket>();
		for( int i = 0; i < map.length; i++ ) {
			Bucket b = (Bucket) map[i];
			while( b != null ) {
				buckets.add(b);
				b = b.next;
			}
		}	
		return buckets;
	}
	
	/**
	 * Rehashes the map. Increases the map's size by factor of two and stores all values stored in the map
	 * to the new larged map.
	 * */
	private void rehash() {
		// get all data
		List<Bucket> buckets = allBuckets();
		
		// increase size
		map = new Object[ map.length * 2 ];
		
		for(Bucket b : buckets ) {
			put( b.key, b.value );
		}
	}
	
	/**
	 * Gets the Bucket for key. A bucket stores information of key, value and potentially next linked item.
	 * 
	 * @param key key searched.
	 * */
	private Bucket getBucket(K key) {
		// check which bucket to return
		Bucket current = (Bucket) map[ hash(key) ];
		if( current == null ) return null;
		return current.getBucket(key);
	}

	// OVERRIDED, actual implementation
	
	@Override
	public void clear() {
		map = new Object[ INITIAL_SIZE ];
		count = 0;
	}
	
	@Override
	public V get(Object key) {
		Bucket correct = getBucket( (K) key );
		if( correct != null ) {
			return getBucket( (K) key ).value;
		}
		// throw exception?
		return null;
	}
	
	@Override
	public V put(K key, V value) {
		// check if we need to rehash
		if( count / map.length > REHASH_TRESHOLD  ) {
			rehash();
		}
		
		// special case, do now allow null as key!
		if( key == null ) {
			throw new NullPointerException();
		}
		
		Bucket b = getBucket(key);
		int index = hash( key );
		// check if there's any value we could add
		if( map[index] == null ) {
			map[index] = new Bucket(key, value);
			count++;
			return null;
		}
		// chain if needed
		if( b == null ) {
			Bucket head = (Bucket) map[index];
			Bucket newBucket = new Bucket(key, value);
			newBucket.next = head.next;
			head.next = newBucket;
			count++;
			return null;
		}
		// we already have the value, just store
		V old = b.value;
		b.value = value;
		return old;
	}
	
	@Override
	public V remove(Object key) {
		Bucket b = getBucket( (K) key);
		if( b == null ) {
			// we do not have value, thow ex
			return null;
		}
		Bucket bb = b.getBucket( (K) key );
		if( bb == null ) {
			// we do not have value, thow ex
			return null;
		}
		// remove count 
		count--;
		
		// we have value, remove nicely
		// we have an only one value in this bucket, remove from map
		if( b == bb ) {
			map[ hash( (K) key ) ] = null;
		} else {
			// search for the bucket that's just before bb
			while( b.next != bb ) {
				b = b.next;
			}
			// connect the two chains and drop bb
			b.next = bb.next;
		}
		return bb.value;
		
	}
	
	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		// TODO: implement Set
		Set<Map.Entry<K, V>> set = new MySet<Map.Entry<K, V>>();
		for( Bucket b : allBuckets() ) {
			set.add( b );
		}
		return set;
	}
	
	@Override
	public boolean containsKey(Object key) {
		K k = (K) key;
		return getBucket( k ) != null;
	}

	// OVERRIDED, not special implementations
	
	@Override
	public Collection<V> values() {
		Collection<V> values = new MyList<V>();
		for( Map.Entry<K, V> entry : entrySet() ) {
			values.add( entry.getValue() );
		}
		return values;
		/* could be, but no significant improvment in O(n)
		Collection<V> values = new MyList<V>();
		for(int i = 0; i < map.length; i++) {		
			// map[i] is a bucket
			Bucket b = (Bucket) map[i];
			if( b != null ) {
				values.addAll( b.values() );
			}
		}
		return values;
		*/
	}
	
	@Override
	public Set<K> keySet() {
		Set<K> keys = new MySet<K>();
		for( Map.Entry<K, V> entry : entrySet() ) {
			keys.add( entry.getKey() );
		}
		return keys;
	}

	@Override
	public boolean containsValue(Object value) {
		return values().contains(value);
		/* could bem but this is O(n) anyway)
		V v = (V) value;
		for( int i = 0; i < map.length; i++ ) {
			if( map[i] != null ) {
				Bucket b = (Bucket) map[i];
				return b.hasValue(v);
			}
		}
		return false;
		*/
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for( Map.Entry<? extends K, ? extends V> e : m.entrySet() ) {
			put( e.getKey(), e.getValue() );
		}
	}

	@Override
	public int size() {
		return values().size();
		/* could be, but this is also O(n)
		int size = 0;
		for(int i = 0; i < map.length; i++) {		
			// map[i] is a bucket
			Bucket b = (Bucket) map[i];
			if( b != null ) {
				size += b.values().size();
			}
		}
		return size;
		*/
	}
	
	/**
	 * Map contains Buckets. Each bucket is a linked list, i.e. it has a value and next item of the list.
	 * It also stores the key of each entry in a bucket.
	 * */
	private class Bucket implements Map.Entry {
		
		K key;
		V value;
		Bucket next = null;
		
		Bucket(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		/**
		 * Returns list of values connected to this bucket
		 * */
		List<V> values() {
			List<V> list = new MyList<V>();
			
			Bucket current = this;
			while( current != null ) {
				list.add( current.value );
				current = current.next;
			}
			
			return list;
		}
		
		/***
		 * Checks if value belongs to this bucket set
		 * */
		boolean hasValue(V v) {
			return values().contains(v);
		}
		
		/***
		 * Get's bucket with correct key if it's connected to this list
		 * */
		Bucket getBucket(K key) {
			Bucket current = this;
			while( current != null ) {
				if( current.key == key ) {
					return current;
				}
				current = current.next;
			}
			return null;
		}

		@Override
		public Object getKey() {
			return this.key;
		}

		@Override
		public Object getValue() {
			return this.value;
		}

		@Override
		public Object setValue(Object value) {
			Object old = this.value;
			this.value = (V) value;
			return old;
		}
		
	}

}
