package datastructures;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyMap<K, V> implements Map<K, V> {
	
	private static int INITIAL_SIZE = 50;
	private static double REHASH_TRESHOLD = 0.9;
	
	/**
	 * Keeper, to store the number of elements in this datastructure
	 * */
	private int count = 0;
	
	private Object[] map = new Object[ INITIAL_SIZE ];
	
	private int hash(K key) {
		return -1;
	}
	
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
	
	private void rehash() {
		// get all data
		List<Bucket> buckets = allBuckets();
		
		// increase size
		map = new Object[ map.length * 2 ];
		
		for(Bucket b : buckets ) {
			put( b.key, b.value );
		}
	}

	@Override
	public void clear() {
		map = new Object[ INITIAL_SIZE ];
		count = 0;
	}
	
	private Bucket getBucket(K key) {
		// check which bucket to return
		Bucket current = (Bucket) map[ hash(key) ];
		return current.getBucket(key);
	}

	@Override
	public boolean containsKey(Object key) {
		try {
			get(key);
			return true;
		}
		// todo: check correct type of exception from getBucket 
		catch(Exception E){
			return false;
		}
	}

	@Override
	public boolean containsValue(Object value) {
		V v = (V) value;
		for( int i = 0; i < map.length; i++ ) {
			if( map[i] != null ) {
				Bucket b = (Bucket) map[i];
				return b.hasValue(v);
			}
		}
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
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
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) {
		// check if we need to rehash
		if( count / map.length < REHASH_TRESHOLD  ) {
			rehash();
		}
		Bucket b = getBucket(key);
		// check if there's any value we could add
		if( b == null ) {
			int index = hash( key );
			Bucket newBucket = new Bucket(key, value);
			count++;
			return null;
		}
		// check that there's no chained values there
		Bucket bucket = b.getBucket(key);
		// chain if needed
		if( bucket == null ) {
			Bucket temp = b.next;
			b.next = new Bucket(key, value);
			b.next.next = temp;
			count++;
			return null;
		}
		// we already have the value, just store
		V old = bucket.value;
		bucket.value = value;
		return old;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for( java.util.Map.Entry<? extends K, ? extends V> e : m.entrySet() ) {
			put( e.getKey(), e.getValue() );
		}
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
	public int size() {
		int size = 0;
		for(int i = 0; i < map.length; i++) {		
			// map[i] is a bucket
			Bucket b = (Bucket) map[i];
			if( b != null ) {
				size += b.values().size();
			}
		}
		return size;
	}

	@Override
	public Collection<V> values() {
		Collection<V> values = new MyList<V>();
		for(int i = 0; i < map.length; i++) {		
			// map[i] is a bucket
			Bucket b = (Bucket) map[i];
			if( b != null ) {
				values.addAll( b.values() );
			}
		}
		return values;
	}
	
	private class Bucket {
		
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
		
	}

}
