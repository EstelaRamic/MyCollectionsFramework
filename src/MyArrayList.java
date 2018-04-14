import java.util.Iterator;

public class MyArrayList<E> extends MyAbstractList<E> {
	public static final int INITIAL_CAPACITY = 10;
	//@SuppressWarnings("unchecked")
	private E[] members = (E[]) (new Object[INITIAL_CAPACITY]);
	
	/** Create a default list */
	public MyArrayList() {
		
	}
	
	/** Create a list from an array of objects */
	public MyArrayList(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]); 
	}
	
	/* (non-Javadoc)
	 * @see MyList#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		// Move the elements to the right after the specified index
		// Insert new element to members[index], and increase size by 1
		
		ensureCapacity();
		
		for ( int i = size-1 ; i >= index ; i--){
			members[i+1] = members[i];
		};
		members[index] = element;
		
		size++;	
	}
	
	/** Create a new larger array,  current size + 2 */
	private void ensureCapacity() {
		if (size >= members.length) {
			//@SuppressWarnings("unchecked")
			E[] newData = (E[]) (new Object[size*2  + 1]);
			System.arraycopy(members, 0, newData, 0, size);
			members = newData;
		}
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("index " + index + " out of bounds");
	}
	
	/* (non-Javadoc)
	 * @see MyList#contains(java.lang.Object)
	 */
	@Override /** Return true if this list contains the element */
	public boolean contains(E element) {
		for (int i = 0; i < size; i++) {
			if (element.equals(members[i])) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see MyList#clear()
	 */
	@SuppressWarnings("unchecked")
	@Override  /** Clear the list */
	public void clear() {
		members = (E[]) new Object[INITIAL_CAPACITY];
		size = 0;
		
	}
	/* (non-Javadoc)
	 * @see MyList#set(int, java.lang.Object)
	 */
	@Override  /** Replace the element at the specified position
	 			 * in this list with the specified element. */
	public E set(int index, E element) {
		checkIndex(index);

		E old = members[index];
		members[index] = element;
		return old;

	}

	
	/* (non-Javadoc)
	 * @see MyList#get(int)
	 */
	@Override
	public E get(int index) {
		checkIndex(index);
		return members[index];
	}
	
	/* (non-Javadoc)
	 * @see MyList#remove(int)
	 */
	@Override /** Remove the element at the specified position
	 			* in this list. Shift any subsequent elements to the left.
	 			* Return the element that was removed from the list. */
	public E remove(int index) {
		checkIndex(index);
		E element = members[index];
		for (int i = index; i < size - 1; i++) {
			members[i] = members[i + 1];
		}
		members[size - 1] = null;
		size--;
		return element;
	}
	/* (non-Javadoc)
	 * @see MyList#indexOf(java.lang.Object)
	 */
	@Override  /** Return the index of the first matching element
	 			 * in this list. Return -1 if no match. */
	public int indexOf(E element) {
		for (int index = 0; index<size; index++){
			if (element.equals(members[index])) {
				return index;
			}
		}
		return -1;
	}
	/* (non-Javadoc)
	 * @see MyList#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(E element) {
		for (int index = size - 1; index >= 0; index--) {
			if (element.equals(members[index])) {
				return index;
			}
		}
		return -1;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");

		for (int i = 0; i < size; i++) {
			result.append(members[i]);
			if (i < size - 1)
				result.append(", ");
		}

		return result.toString() + "]";
	}

	/* (non-Javadoc)
	 * @see MyList#subList(int, int)
	 */
	@Override
	public MyList<E> subList(int fromIndex, int toIndex) {
		
		MyArrayList<E> newList =  new MyArrayList<>();
		for (int i=fromIndex; i<toIndex; i++){
			newList.add(members[i]);
		}
		return newList;
	}
	
	
	@Override  /** Override iterator() defined in Iterable */
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}
	
	
	/** Iterator class */
	private class ArrayListIterator implements java.util.Iterator<E> {
	 private int current = 0; // Current index
	
	 @Override
	 public boolean hasNext() {
		 return (current < size);
	 }
	
	 @Override
	 public E next() {
		 return members[current++];
	 }

	 @Override
	 public void remove() {
		 MyArrayList.this.remove(current);
	 }
	}    // private class

}
