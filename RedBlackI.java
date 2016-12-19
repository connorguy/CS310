package data_structures;

import java.util.Iterator;


/**
 * RedBlackTree.  A data structure that maintains a balanced 
 * Red/Black Balanced Search Tree.
 */

public interface RedBlackI<K, V> extends Iterable<K> {
	
	
	/**
	 * The method to add to the RBTree.  It will not allow duplicate additions.
	 * @param key the key to add
	 * @param value the value associated with the key
	 */
	public void add(K key, V value);

	/**
	 * Tests whether the RBTree contains the key
	 * @param key the key to look for
	 * @return whether the key is found
	 */
	public boolean contains(K key);

	/**
	 * Get the value associated with a given key
	 * @param key the key to get the value for
	 * @return the current value
	 */
	public V getValue(K key);

	/**
	 * Returns the number of elements in the RBTree
	 * @return the number of elements in the tree
	 */
	public int size();

	/**
	 * Test whether the RBTree is empty
	 * @return <code>true</code> if the tree is empty
	 * 		   <code>false</code> if the tree is not empty 
	 */
	public boolean isEmpty();

	/**
	 * The height of the tree. Recall that a tree with 
	 * only a root node has height 0 
	 * @return the height of the tree at the root node
	 */
	public int height();
		
	/**
	 * An iterator for all the keys in the RBTree. This will
	 * iterate over the keys using <b>InOrder Traversal</b>
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<K> iterator();

	
	/**
	 * Recursively print the tree. This method should print the
	 * entire tree using <em>Inorder Traversal</em> to the standard
	 * output (i.e. using System.out.println or System.out.print).
	 * You can print the tree one node per line, and use periods to
	 * note the hierarchy of the tree.
	 */
	public void print();
}
