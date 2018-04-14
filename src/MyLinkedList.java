import java.util.Iterator;

public class MyLinkedList<E> extends MyAbstractList<E> {
	
	 Node<E> head = null;
	 Node<E> tail = null;
	 
	 
	public MyLinkedList(){
		
	}
	public MyLinkedList(E[] objects){
		for (int i = 0; i < objects.length; i++)
			add(objects[i]); 
	}
	
	/** creates a new node to store the element  and inserts the node at the beginning of the list*/
	public void addFirst(E element) {
		Node<E> newNode = new Node<>(element); 
		newNode.next = head; 
		head = newNode; 
		size++; 

		if (tail == null) // The new node is the only node in list
			tail = head;

	}
	
	/** creates a node to hold the element and appends the node at the end of the list. */
	public void addLast(E element) {
		Node<E> newNode = new Node<>(element); 

		if (tail == null) {
			head = newNode;
			tail = newNode; 
		} else {
			tail.next = newNode; 
			tail = tail.next; 
		}
		size++;

	}

	@Override
	public void add(int index, E element) {
		if (index == 0)
			addFirst(element); 
		else if (index >= size)
			addLast(element); 
		else { // Insert in the middle
			Node<E> current = head;
			for (int i = 1; i < index; i++)
				current = current.next;
			Node<E> temp = current.next;
			current.next = new Node<E>(element);
			(current.next).next = temp;
			size++;
		}
	}
	
	/** removes the first element from the list*/
	public E removeFirst() {
		if (size == 0)
			return null; 
		else {
			Node<E> temp = head; 
			head = head.next; 
			size--;
			if (head == null)
				tail = null; 
			return temp.element; 
		}
	}

	/** removes the last element from the list */
	public E removeLast() {
		if (size == 0)
			return null; 
		else if (size == 1) { 
			Node<E> temp = head;
			head = tail = null; 
			size = 0;
			return temp.element;
		} else {
			Node<E> current = head;

			for (int i = 0; i < size - 2; i++)
				current = current.next;

			Node<E> temp = tail;
			tail = current;
			tail.next = null;
			size--;
			return temp.element;
		}
	}

	@Override
	public boolean contains(E element) {
		Node<E> temp = head;
		boolean finded=(temp.element.equals(element));
		while ((temp.next != tail) && (!finded)){
			temp = temp.next;
			finded=(temp.element.equals(element));
		}
		return finded;
	}
	@Override
	public void clear() {
		size = 0;
		head = null;
		tail = null;
		
	}
	@Override /** Replace the element at the specified position
	 			*in this list with the specified element. */
	public E set(int index, E element) {
		Node<E> current = head;
		for (int i = 1; i <= index; i++)
			current = current.next;
		current.element= element;
		return current.element;
	}
	
	@Override
	public E get(int index) {
		if (index == 0)
			return head.element; 
		else if (index >= size)
			return tail.element; 
		else { 
			Node<E> current = head;
			for (int i = 1; i < index; i++)
				current = current.next;
			return current.element;
		}
	}

	/** Return the head element in the list */
	public E getFirst() {
		if (size == 0) {
			return null;
		} else {
			return head.element;
		}
	}

	/** Return the last element in the list */
	public E getLast() {
		if (size == 0) {
			return null;
		} else {
			return tail.element;
		}
	}

	@Override  /** finds the node at the specified index and then removes it*/
	public E remove(int index) {
		if (index < 0 || index >= size)
			return null; 
		else if (index == 0)
			return removeFirst(); 
		else if (index == size - 1)
			return removeLast(); 
		else {
			Node<E> previous = head;

			for (int i = 1; i < index; i++) {
				previous = previous.next;
			}

			Node<E> current = previous.next;
			previous.next = current.next;
			size--;
			return current.element;
		}

	}
	
	@Override
	public int indexOf(E element) {
		Node<E> temp = head;
		int index=0;
		do{			
			if (temp.element.equals(element)) 
				return index;
			temp = temp.next;
			index++;
		}while (temp.next != null) ;
		
		return -1;
	}
	@Override
	public int lastIndexOf(Object element) {
		Node<E> temp = head;
		int index=0;
		int elementIndex=-1;
		do{			
			if (temp.element.equals(element)) 
				elementIndex= index;
			temp = temp.next;
			index++;
		}while (temp.next != null) ;
		
		return elementIndex;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");

		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != null) {
				result.append(", "); 
			} else {
				result.append("]"); 
			}
		}

		return result.toString();
	}

	@Override
	public MyList<E> subList(int fromIndex, int toIndex) {
		MyLinkedList<E> newList =  new MyLinkedList<>();
		Node<E> previous = head;

		for (int i = 1; i < fromIndex; i++) {
			previous = previous.next;
		}

		newList.head = previous.next;
		
		for (int i = fromIndex; i < toIndex; i++) {
			previous = previous.next;
			newList.add(previous.element);
		}
		
		newList.tail = previous;
		size = toIndex - fromIndex;
		
		return newList;
	}

	
	@Override /** Override iterator() defined in Iterable */
	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements java.util.Iterator<E> {
		private Node<E> current = head;

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public E next() {
			E e = current.element;
			current = current.next;
			return e;
		}
	}

}
