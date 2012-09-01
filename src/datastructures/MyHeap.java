package datastructures;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyHeap<E extends Comparable<E>, T> implements Queue<E> {
	
	private static int INITIAL_SIZE = 50;
	
	private Object[] data = new Object[ INITIAL_SIZE ];
	
	private int size = 0;
	
	private void heapify(int i) {
		int leftI = 2 * i + 1;
		int rightI = 2 * (i + 1);
		int currentI = i;
		
		// tree structure
		E left = (E) data[ leftI ];
		E right = (E) data[ rightI ];
		E current = (E) data[ currentI ];
		
		int newI = -1;
		
		// check children
		if( left != null && left.compareTo( current ) < 0 ) {
			newI = leftI;
		}
		if( right != null && right.compareTo( current ) < 0 ) {
			newI = rightI;
		}
		
		// swap positions if needed
		if( newI > -1 ) {
			Object temp = data[ newI ];
			data[ newI ] = data[currentI];
			data [currentI ] = temp;
			heapify( newI );
		}
	}

	@Override
	public boolean contains(Object o) {
		E target = (E) o;
		// as this heap is a min-heap, the data should always be sorted
		// therefore, binary search can be used for this (log n)
		int upper = size - 1;
		int lower = 0;
		while( lower <= upper ) {
			
			int currentI = lower + (upper - lower) / 2;

			E current = (E) data[currentI];
			
			if( target.equals( current ) ) {
				return true;
			}
			if( target.compareTo( current ) > 0 ) {
				lower = currentI + 1;
			} else {
				upper = currentI - 1;
			}
		}
		return false;
	}

	@Override
	public boolean remove(Object value) {
		for(int i = 0; i < size; i++) {
			if( data[i].equals(value) ) {
				data[i] = null;
				heapify(0);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		boolean state = false;
		for( Object o : data ) {
			if( collection.contains( o ) ) {
				state = true;
				remove( o );
			}
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	// HEAP related implementation

	@Override
	public boolean offer(E value) {
		// when needed, increase the capacity
		if( size == data.length ) {
			Object[] newData = new Object[ 2 * size ];
			for(int i = 0; i < size; i++ ) {
				newData[i] = data[i];
			}
			data = newData;
		}
		
		data[ size++ ] = value;
		heapify( 0 );
		
		return true;
	}


	@Override
	public E element() {
		if( size == 0 ) {
			throw new NoSuchElementException();
		}
		return peek();
	}
	
	@Override
	public E peek() {
		return (E) data[0];
	}

	@Override
	public E poll() {
		
		if( size == 0) {
			return null;
		}
		
		E temp = (E) data[0];
		
		size--;
		
		data[0] = data[ size ];
		data[size ] = null;
		
		heapify(0);
		
		return temp;
	}
	
	@Override
	public E remove() {
		if( size == 0 ) {
			throw new NoSuchElementException();
		}
		return poll();
	}
	
	// trivial implementations
	
	@Override
	public boolean add(E value) {
		return offer(value);
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public void clear() {
		size = 0;
		data = new Object[ INITIAL_SIZE ];
	}
	
	// OVERRIDED, using this class
	
	@Override
	public boolean addAll(Collection<? extends E> collection) {
		boolean state = false;
		for( E e : collection ) {
			if( add( e ) ) {
				state = true;
			}
		}
		return state;
	}
	
	
	@Override
	public boolean removeAll(Collection<?> collection) {
		boolean state = false;
		for( Object e : collection ) {
			if( remove( e ) ) {
				state = true;
			}
		}
		return state;
	}
	
	@Override
	public boolean containsAll(Collection<?> collection) {
		for( Object e : collection ) {
			if( ! contains( e ) ) {
				return false;
			}
		}
		return true;
	}
	
}
