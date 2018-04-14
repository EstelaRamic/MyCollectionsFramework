/* MyList defines a common interface for abstract class MyAbstractList,and classes MyArrayList,
 * and MyLinkedList
 */

public interface MyList<E> extends java.lang.Iterable<E> {

	// Operations at a specified index position
	void add(int index, E element);

	// Operations inherited from Collection<E>
	
	void add(E element);

	int size();

	boolean isEmpty();

	boolean remove(E element);

	boolean contains(E element);

	void clear();

	E set(int index, E element); // replace

	E get(int index); // retrieve without remove

	E remove(int index); // remove last retrieved

	int indexOf(E element);

	int lastIndexOf(E element);

	// Operations on a range fromIndex (inclusive) toIndex (exclusive)
	MyList<E> subList(int fromIndex, int toIndex);

}
