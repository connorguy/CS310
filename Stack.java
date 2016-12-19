package data_structures;

import java.util.Iterator;

/**
 * Implementation of a stack using generic arguments, and a linked list
 * structure.
 *
 * @author Connor Guy
 * @param <E> the type of elements in this Stack
 */

public class Stack<E>
{
    private ListI<E> list;

    public Stack()
    {
        list = new LinkedList<E>();
    }

    /**
     * Adds an object to the stack.
     *
     * @param obj the object to be added to the stack.
     */
    public void push(final E obj)
    {
        list.addFirst(obj);
    }

    /**
     * Removes the first item in the stack.
     *
     * @return the object removed.
     */
    public E pop()
    {
        return list.removeFirst();
    }

    /**
     * Returns the number of Objects currently in the stack.
     *
     * @return the number of Objects currently in the stack.
     */
    public int size()
    {
        return list.size();
    }

    /**
     * Return the stack to an empty state.
     * This should generally be a constant time operation.
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    /**
     * Test whether the stack is full.
     *
     * @return true if the stack is full, otherwise false
     */
    public boolean isFull()
    {
        return false;
    }

    /**
     * Returns what is on top of the stack
     *
     * @return first item in on the stack
     */
    public E peek()
    {
        return list.peekFirst();
    }

    /**
     * Test whether the stack contains an object. This will use the object's
     * compareTo method to determine whether two objects are the same.
     *
     * @param obj The object to look for in the stack
     * @return true if the object is found in the stack, false if it is not
     *         found
     */
    public boolean contains(final E obj)
    {
        return list.contains(obj);
    }

    /**
     * Return the stack to an empty state.
     * This should generally be a constant time operation.
     */
    public void makeEmpty()
    {
        list.makeEmpty();
    }

    /**
     * Returns an Iterator of the values in the stack, presented in
     * the same order as the stack.
     *
     * @see java.lang.Iterable#iterator()
     */
    public Iterator iterator()
    {
        return list.iterator();
    }

}
