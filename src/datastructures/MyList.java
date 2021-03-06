package datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Provides a one-way linked list.
 * **/
public class MyList<E> implements List<E> {
	
	/**
	 * The tunnussolmu of this list.
	 * */
	private Element<E> first = new Element<E>(null);
	
	/***
	 * The last element stored in this list.
	 * Helps to achive O(1) for add(E) operation.
	 */
	private Element<E> last = null;
	
	/**
	 * Gets the nth element of this list. Causes IndexOutOfBoundsException
	 * if n is larger than the list size.
	 * 
	 * @param n the element searched
	 * @return The element strored in given position.
	 * 
	 * **/
	private Element<E> getElement(int n) {
		int index = 0;
		Element<E> current = first.next;
		while( current != null ) {
			if( index == n ) {
				return current;
			}
			index++;
			current = current.next;
		}
		throw new IndexOutOfBoundsException();
	}
	
	/**
	 * Adds element after the given position and updates the list order accordinlfy.
	 * 
	 * @param target the node after which new node is linked
	 * @param object the new object to be added
	 * **/
	private void addAfter(Element target, Element object) {
		object.next = target.next;
		target.next = object;
	}

	@Override
	public boolean add(E value) {
		Element<E> e = new Element<E>(value);
		// adding first element
		if( last == null ) {
			first.next = e;
			last = e;
			return true;
		}
		// otherwise, always take the last element
		addAfter( last, e);
		// update the location of last element
		last = e;
		return true;
	}

	@Override
	public void add(int index, E value) {
		index = index - 1;
		Element<E> newValue = new Element<E>(value);
		
		if( index == -1 ) {
			// set as first element
			newValue.next = first.next;
			first.next = newValue;
			return;
		}
		Element target = getElement(index);
		addAfter(target, newValue);
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO: fixme, when list is empty, this won't work!
		// int last = size() - 1;
		// return addAll( last, arg0 );
		// could be, but same O(n)
		for(E e : arg0) {
			this.add(e);
		}
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> elements) {
		Element<E> previous = getElement(index);
		for(E e : elements) {
			Element<E> temp = new Element<E>( e );
			addAfter(previous, temp);
			previous = temp;
		}
		return false;
	}

	@Override
	public void clear() {
		first.next = null;
		last = null;
	}

	@Override
	public boolean contains(Object arg0) {
		return indexOf(arg0) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		for(Object o : arg0) {
			if( ! contains( o ) ) {
				return false;
			}
		}
		return true;
	}

	@Override
	public E get(int index) {
		return getElement(index).value;
	}

	@Override
	public int indexOf(Object arg0) {
		int index = 0;
		Element<E> current = first.next;
		while( current != null ) {
			if( current.value.equals(arg0) ) {
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return first.next == null;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyIterator<E>(this);
	}

	@Override
	public int lastIndexOf(Object arg0) {
		int index = 0;
		int found = -1;
		Element<E> current = first.next;
		while( current != null ) {
			if( current.value.equals(arg0) ) {
				found = index;
			}
			current = current.next;
			index++;
		}
		return found;
	}

	@Override
	public ListIterator<E> listIterator() {
		return new MyIterator<E>(this);
	}

	@Override
	public ListIterator<E> listIterator(int first) {
		return new MyIterator<E>( subList( first, size() ) );
	}

	@Override
	public boolean remove(Object arg0) {
		// search for object
		Element<E> previous = first;
		Element<E> current = first.next;
		while( current != null ) {
			if( current.value.equals(arg0) ) {
				previous.next = current.next;
				return true;
			}
			previous = current;
			current = current.next;
		}
		return false;
	}

	@Override
	public E remove(int index) {
		E target = get(index);
		remove(target);
		return target;
		/* could also be, but same in O(n)
		int index = 0;
		Element<E> previous = first;
		Element<E> current = first.next;
		while( current != null ) {
			if( index == arg0 ) {
				previous.next = current.next;
				return current.value;
			}
			index++;
			previous = current;
			current = current.next;
		}
		throw new IndexOutOfBoundsException();
		*/
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		boolean state = false;
		for(Object o : arg0) {
			if( remove(o) ) {
				state = true;
			}
		}
		return state;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		boolean state = false;
		for( E o : this ) {
			if( ! collection.contains( o ) ) {
				state = true;
				remove( o );
			}
		}
		return state;
	}

	@Override
	public E set(int index, E value) {
		E old = remove(index);
		add(index, value);
		return old;
		/* could also be, but no difference in O(n)
		int i = 0;
		Element<E> current = first.next;
		while( current != null ) {
			if( i == index ) {
				E old = current.value;
				current.value = value;
				return old;
			}
			current = current.next;
			i++;
		}
		throw new IndexOutOfBoundsException();
		*/
	}

	@Override
	public int size() {
		int size = 0;
		Element<E> current = first.next;
		while( current != null ) {
			size++;
			current = current.next;
		}
		return size;
	}

	@Override
	public List<E> subList(int min, int max) {
		List<E> newList = new MyList<E>();
		int index = 0;
		Element<E> current = first.next;
		while( current != null ) {
			if( index >= min && index < max ) {
				newList.add(current.value);
			}
			index++;
			current = current.next;
		}
		return newList;
		/* could also be, but this is O(n) = n^2, as get(i) is O(n)
		 for(int i = min; i < max; i++ ) {
		 	newList.add( get( i ) );
		 }
		 */
	}

	@Override
	public Object[] toArray() {
		Object[] list = new Object[ size() ];
		return toArray( list );
	}

	@Override
	public <T> T[] toArray(T[] table) {
		if( table.length < size() ) {
			table = (T[]) new Object[ size() ];
		}
		Element<E> current = first.next;
		for(int i = 0; i < table.length; i++ ) {
			table[i] =  (T) current.value;
			current = current.next;
		}
		return table;
	}


	/***
	 * Support structure for linked list.
	 * Stores the value and the next element in a container class.
	 * **/
	private class Element<T> {
		
		/***
		 * The value stored in this element.
		 **/
		private T value;
		
		/**
		 * The next linked element.
		 * **/
		private Element next = null;
		
		/**
		 * Creates a new Element.
		 * 
		 * @param value value stored in this element.
		 * **/
		Element(T value) {
			this.value = value;
		}
		
	}
	
}
