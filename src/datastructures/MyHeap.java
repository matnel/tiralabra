package datastructures;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Implements a minimun heap (priority queue).
 * **/
public class MyHeap<E extends Comparable> implements Queue<E> {
	
	/**
	 * The initial size of the heap.
	 * **/
	private static int INITIAL_SIZE = 50;
	
	/**
	 * All data is stored in this table.
	 * **/
	private Object[] data = new Object[ INITIAL_SIZE ];
	
	/**
	 * The current size of the heap.
	 * */
	private int size = 0;
	
	/**
	 * After removing elements from the heap, the heap needs to be reorganized.
	 * This method maintains the feature that each parent is smaller than it's children
	 * by swapping elements when needed.
	 * */
	private void heapify(int i) {
		int leftIndex = 2 * i + 1;
		int rightIndex = 2 * (i + 1);
		int currentIndex = i;
		
		// tree structure
		E left  =  leftIndex < size ? (E) data[ leftIndex  ] : null;
		E right = rightIndex < size ? (E) data[ rightIndex ] : null;
		E current = (E) data[ currentIndex ];
		
		boolean swap = false;
		E min = current;
		int minIndex = -1;
		
		// check smaller children children
		if( left != null && left.compareTo( current ) < 0 ) {
			swap = true;
			minIndex = leftIndex;
			min = left;
		}
		if( right != null && right.compareTo( min ) < 0 ) {
			swap = true;
			minIndex = rightIndex;
			min = right;
		}
		
		// swap positions if needed
		if( swap ) {
			data[ minIndex ] = current;
			data [currentIndex ] = min;
			heapify( minIndex );
		}
	}

	@Override
	public boolean contains(Object o) {
		// E target = (E) o;
		/* No, it's not!
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
		*/
		for( int i = 0; i < size; i++ ) {
			if( o.equals( data[i] ) ) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(Object value) {
		for(int i = 0; i < size; i++) {
			if( data[i].equals(value) ) {
				// remove the last index to this position
				size --;
				data[i] = data[size];
				data[size] = null;
				// reorder
				heapify(0);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		boolean state = false;
		for( int i = 0; i < size; i++ ) {
			if( ! collection.contains( data[i] ) ) {
				state = true;
				remove( data[i] );
			}
		}
		return state;
	}

	@Override
	public Object[] toArray() {
		return toArray( new Object[ size ] );
	}

	@Override
	public <T> T[] toArray(T[] table) {
		if( table.length < size ) {
			table = (T[]) new Object[ size ];
		}
		for(int i = 0; i < table.length; i++ ) {
			table[i] =  (T) data[i];
		}
		return table;
	}
	
	@Override
	public Iterator<E> iterator() {
		MyList<E> list = new MyList<E>();
		// copy heap
		while( peek() != null ) {
			list.add( poll() );
		}
		// add everytihing back to heap too
		for( E e : list ) {
			add( e );
		}
		return new MyIterator<E>( list );
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
		// save value!
		data[ size ] = value;
		// maintain!
		
		int currentIndex = size;
		int parentIndex = ( currentIndex - 1) / 2;
		
        E current = (E) data[ currentIndex ];
        E parent = (E) data[ parentIndex ];

        while ( current.compareTo( parent ) < 0 ) {
        	// swap!
            data[ parentIndex ] = current;
            data[ currentIndex ] = parent;
            // update
            currentIndex = parentIndex;
            parentIndex = ( currentIndex - 1) / 2;
            current = (E) data[currentIndex];
            parent = (E) data[parentIndex];
        }
		// increase heap size
		size++;
		
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
