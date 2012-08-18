package datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyList<E> implements List<E> {
	
	private Element<E> first = new Element<E>(null);
	
	// this will help in add(E) case
	private Element<E> last = null;
	
	private Element<E> getElement(int arg0) {
		int index = 0;
		Element<E> current = first.next;
		while( current != null ) {
			if( index == arg0 ) {
				return current;
			}
			index++;
			current = current.next;
		}
		throw new IndexOutOfBoundsException();
	}
	
	private void addAfter(Element target, Element object) {
		object.next = target.next;
		target.next = object;
	}

	@Override
	public boolean add(E value) {
		// TODO: check return values
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
		int last = size() - 1;
		return addAll( last, arg0 );
		/* could be, but same O(n)
		for(E e : arg0) {
			this.add(e);
		}
		return false;
		*/
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
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
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
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
			if( index > min && max < index ) {
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
		Element<E> current = first.next;
		for(int i = 0; i < list.length; i++ ) {
			list[i] = current;
			current = current.next;
		}
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	private class Element<T> {
		
		private T value;
		
		Element(T value) {
			this.value = value;
		}
		
		private Element next = null;
		
	}
	
}
