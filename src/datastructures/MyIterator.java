package datastructures;

import java.util.*;

public class MyIterator<E> implements ListIterator<E>  {
	
	private List<E> list = new MyList<E>();
	private int index = 0;
	private E previous;
	private E current;
	
	public MyIterator(List <E> list) {
		// copy list for this iterator
		for(int i = 0; i < list.size(); i++ ) {
			this.list.add( list.get(i) );
		}
	}

	@Override
	public boolean hasNext() {
		return ! list.isEmpty();
	}

	@Override
	public E next() {
		if( ! hasNext() ) {
			throw new NoSuchElementException();
		}
		previous = current;
		current = list.remove(0);
		index++;
		return current;
	}

	@Override
	public void remove() {
		// TODO: bucnh of exceptions to be thrown?
		list.remove(0);
	}

	@Override
	public void add(E arg0) {
		list.add(arg0);
	}

	@Override
	public boolean hasPrevious() {
		System.out.println( previous );
		return previous != null;
	}

	@Override
	public int nextIndex() {
		return index + 1;
	}

	@Override
	public E previous() {
		return previous;
	}

	@Override
	public int previousIndex() {
		return index - 1;
	}

	@Override
	public void set(E arg0) {
		// TODO: add me
	}

}
