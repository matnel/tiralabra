package datastructures;

import java.util.Set;

/** Set is a spesific kind of list where values can be stored only once
 *  Use the list structure to implement this.
 * */
public class MySet<E> extends MyList<E> implements Set<E> {

	
	public boolean add(E element) {
		if( this.contains( element ) ) {
			return false;
		}
		super.add(element);
		return true;
	}
	
	public void add(int index, E element) {
		throw new UnsupportedOperationException();
	}
	
}
