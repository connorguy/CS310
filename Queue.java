package data_structures;

import java.util.Iterator;

/**
 * Implementation of a queue using generic arguments, and a linked list
 * structure.
 *
 * @author Connor Guy
 * @param <E> the type of elements in this queue
 */

public class Queue<E>
{
    private ListI<E> list;

    public Queue()
    {
        list = new LinkedList<E>();
    }

    /**
     * Adds an object to the queue.
     *
     * @param obj the object to be added to the list.
     */
    public void enqueue(final E obj)
    {
        list.addFirst(obj);
    }

    /**
     * Removes the last item in the queue.
     *
     * @return the object removed.
     */
    public E dequeue()
    {
        return list.removeLast();
    }

    /**
     * Returns the current size of the queue.
     *
     * @return size of the list.
     */
    public int size()
    {
        return list.size();
    }

    /**
     * Returns true if the list is empty.
     *
     * @return boolean
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    /**
     * Returns what is next to be dequeued
     *
     * @return last object in the queue
     */
    public E peek()
    {
        return list.peekLast();
    }

    /**
     * Test whether the queue contains an object. This will use the object's
     * compareTo method to determine whether two objects are the same.
     *
     * @param obj The object to look for in the queue
     * @return true if the object is found in the queue, false if it is not
     *         found
     */
    public boolean contains(final E obj)
    {
        return list.contains(obj);
    }

    /**
     * Return the queue to an empty state.
     * This should generally be a constant time operation.
     */
    public void makeEmpty()
    {
        list.makeEmpty();
    }

    /**
     * Returns an Iterator of the values in the queue, presented in
     * the same order as the queue.
     *
     * @see java.lang.Iterable#iterator()
     */
    public Iterator iterator()
    {
        return list.iterator();
    }

}
