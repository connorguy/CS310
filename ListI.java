package data_structures;

import java.util.Iterator;

/**
 * A list of data.
 * 
 * The list interface, ListI, describes the interface that must
 * be implemented by lists conforming to this interface. This interface
 * does not explicitly say how you must implement these methods.
 * 
 * This interface provides stipulations on what you must implement,
 * and what they are called. The interface includes methods
 * for adding and removing elements. Efficient methods should be 
 * provided for determining if the list is empty, and for 
 * determining the current size of the list. Methods for determining
 * whether the list is full will depend on the implementation, but
 * should be efficiently encoded.
 * 
 * @author CS310
 * @param <E> the type of elements in this Linked List
 */

public interface ListI<E> extends Iterable<E> {

	/**
	 * Adds an object to the beginning of the list.
	 * 
	 * @param obj the object to be added to the list.
	 */
	public void addFirst(E obj);

	/**
	 * Adds an object to the end of the list.
	 * @param obj the object to be added to the list.
	 */
	public void addLast(E obj);

	/**
	 * Removes the first Object in the list and returns it.
	 * Returns null if the list is empty.
	 * @return the object removed.
	 */
	public E removeFirst();

	/**
	 * Removes the last Object in the list and returns it.
	 * Returns null if the list is empty.
	 * @return the object removed.
	 */
	public E removeLast();

	/**
	 * Returns the first Object in the list, but does not remove it.
	 * Returns null if the list is empty.
	 * @return the object at the beginning of the list.
	 */
	public E peekFirst();

	/**
	 * Returns the last Object in the list, but does not remove it. 
	 * Returns null if the list is empty.
	 * @return the object at the end of the list.
	 */
	public E peekLast();

	/**
	 * Return the list to an empty state.
	 * This should generally be a constant time operation.
	 */
	public void makeEmpty();
	
	/**
	 * Test whether the list is empty.
	 * @return true if the list is empty, otherwise false
	 */
	public boolean isEmpty();

	/**
	 * Test whether the list is full.
	 * @return true if the list is full, otherwise false
	 */
	public boolean isFull();

	/**
	 * Returns the number of Objects currently in the list.
	 * @return the number of Objects currently in the list.
	 */
	public int size();

	/**
	 * Test whether the list contains an object. This will use the object's
	 * compareTo method to determine whether two objects are the same.
	 * 
	 * @param obj The object to look for in the list
	 * @return true if the object is found in the list, false if it is not found
	 */
	public boolean contains(E obj);

	/**
	 * Returns an Iterator of the values in the list, presented in
	 * the same order as the list.
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<E> iterator();

}

