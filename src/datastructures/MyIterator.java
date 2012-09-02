package datastructures;

import java.util.*;

public class MyIterator<E> implements ListIterator<E>  {
	
	private List<E> list = new MyList<E>();
	private int index = 0;
	private E previous;
	private E current;
	
	private enum LAST { NO, NEXT, PREVIOUS };
	
	private LAST state = LAST.NO;
	
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
		
		state = LAST.NEXT;
		
		return current;
	}

	@Override
	public void remove() {
		state = LAST.NO;
		list.remove(0);
	}

	@Override
	public void add(E arg0) {
		state = LAST.NO;
		list.add(arg0);
	}

	@Override
	public boolean hasPrevious() {
		return previous != null;
	}

	@Override
	public int nextIndex() {
		return index + 1;
	}

	@Override
	public E previous() {
		state = LAST.PREVIOUS;
		return previous;
	}

	@Override
	public int previousIndex() {
		return index - 1;
	}

	@Override
	public void set(E o) {
		if( state == LAST.NO ) {
			throw new IllegalStateException();
		}
		if( state == LAST.NEXT || state == LAST.PREVIOUS ) {
			list.add( index - 1, o );
		}
		
	}

}
