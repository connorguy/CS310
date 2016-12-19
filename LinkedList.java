package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a linked list using generic arguments.
 *
 * @author Connor Guy
 * @param <E> the type of elements in this Linked List
 */

public class LinkedList<E> implements ListI<E>
{
    /** * @param <E> */
    private class Node<E>
    {
        E data;
        Node<E> next;

        public Node(final E newData)
        {
            data = newData;
            next = null;

        }
    }

    private Node<E> head, tail;
    private int currentSize;

    public LinkedList()
    {
        head = tail = null;
        currentSize = 0;
    }

    /**
     * Adds an object to the beginning of the list.
     *
     * @param obj the object to be added to the list.
     */
    @Override
    public void addFirst(final E obj)
    {
        final Node<E> newNode = new Node<E>(obj);
        if (isEmpty())
        {
            tail = newNode;
        }
        newNode.next = head;
        head = newNode;
        currentSize++;
        return;
    }

    /**
     * Adds an object to the end of the list.
     *
     * @param obj the object to be added to the list.
     */
    @Override
    public void addLast(final E obj)
    {
        final Node<E> node = new Node<E>(obj);
        if (head == null)
        {
            head = tail = node;
            currentSize++;
            return;
        }
        tail.next = node;
        tail = node;
        currentSize++;
        return;
    }

    /**
     * Removes the first Object in the list and returns it.
     * Returns null if the list is empty.
     *
     * @return the object removed.
     */
    @Override
    public E removeFirst()
    {
        if (isEmpty())
        {
            return null;
        }

        final Node<E> tmp = head;
        head = head.next;
        currentSize--;
        return tmp.data;

    }

    /**
     * Removes the last Object in the list and returns it.
     * Returns null if the list is empty.
     *
     * @return the object removed.
     */
    @Override
    public E removeLast()
    {
        if (isEmpty())
        {
            return null;
        }
        if (head == tail)
        {
            return removeFirst();
        }

        final E returnData = tail.data;
        Node<E> previous = null, current = head;
        while (current != tail)
        {
            previous = current;
            current = current.next;
        }
        current = null;
        tail = previous;
        currentSize--;
        return returnData;
    }

    /**
     * Returns the first Object in the list, but does not remove it.
     * Returns null if the list is empty.
     *
     * @return the object at the beginning of the list.
     */
    @Override
    public E peekFirst()
    {
        if (head == null)
        {
            return null;
        }
        return head.data;
    }

    /**
     * Returns the last Object in the list, but does not remove it.
     * Returns null if the list is empty.
     *
     * @return the object at the end of the list.
     */
    @Override
    public E peekLast()
    {
        if (tail == null)
        {
            return null;
        }
        return tail.data;
    }

    /**
     * Return the list to an empty state.
     * This should generally be a constant time operation.
     */
    @Override
    public void makeEmpty()
    {
        if (isEmpty())
        {
            return;
        }
        currentSize = 0;
        head = tail = null;
        return;
    }

    /**
     * Test whether the list is empty.
     *
     * @return true if the list is empty, otherwise false
     */
    @Override
    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * Test whether the list is full.
     *
     * @return true if the list is full, otherwise false
     */
    @Override
    public boolean isFull()
    {
        return false;
    }

    /**
     * Returns the number of Objects currently in the list.
     *
     * @return the number of Objects currently in the list.
     */
    @Override
    public int size()
    {
        return currentSize;
    }

    /**
     * Test whether the list contains an object. This will use the object's
     * compareTo method to determine whether two objects are the same.
     *
     * @param obj The object to look for in the list
     * @return true if the object is found in the list, false if it is not found
     */
    @Override
    public boolean contains(final E obj)
    {
        Node<E> current = head;
        while (current != null)
        {
            if (((Comparable<E>) obj).compareTo(current.data) == 0)
            {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns an Iterator of the values in the list, presented in
     * the same order as the list.
     *
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<E> iterator()
    {
        return new IteratorHelper();

    }

    /**
     * Moves through the list in the order that it is created in.
     *
     * @return next object in the list
     */
    private class IteratorHelper implements Iterator<E>
    {
        Node<E> index;

        public IteratorHelper()
        {
            index = head;
        }

        @Override
        public boolean hasNext()
        {
            return index != null;
        }

        @Override
        public E next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }

            final E tmp = index.data;
            index = index.next;
            return tmp;
        }

    }
}
