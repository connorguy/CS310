package data_structures;

import java.util.Iterator;

/**
 * RedBlackTree. A data structure that maintains a balanced
 * Red/Black Balanced Search Tree.
 *
 * @param <K>
 * @param <V>
 */
public class RedBlackTree<K, V> implements RedBlackI<K, V>
{

    private class Node<K, V>
    {
        K key;
        V value;
        Node<K, V> parent;
        Node<K, V> left;
        Node<K, V> right;
        boolean isBlack;
        boolean isLeft;

        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
            parent = left = right = null;
            isBlack = false;
            isLeft = false;
        }
    }

    public Node<K, V> root;
    public int currentSize;

    public RedBlackTree()
    {
        root = null;
        currentSize = 0;
    }

    /**
     * Tests whether the RBTree contains the key
     *
     * @param key the key to look for
     * @return whether the key is found
     */
    @Override
    public boolean contains(K key)
    {
        return contains(key, root);
    }

    private boolean contains(K key, Node<K, V> node)
    {
        if (node == null)
        {
            return false;
        }
        if (((Comparable<K>) key).compareTo(node.key) == 0)
        {
            return true;
        }
        if (((Comparable<K>) key).compareTo(node.key) > 0)
        {
            return contains(key, node.right);
        }
        return contains(key, node.left);
    }

    /**
     * Get the value associated with a given key
     *
     * @param key the key to get the value for
     * @return the current value
     */
    @Override
    public V getValue(K key)
    {
        return getValue(key, root);
    }

    private V getValue(K key, Node<K, V> node)
    {
        if (node == null)
        {
            return null;
        }
        if (((Comparable<K>) key).compareTo(node.key) == 0)
        {
            return node.value;
        }
        if (((Comparable<K>) key).compareTo(node.key) > 0)
        {
            return getValue(key, node.right);
        }
        return getValue(key, node.left);
    }

    /**
     * Returns the number of elements in the RBTree
     *
     * @return the number of elements in the tree
     */
    @Override
    public int size()
    {
        return currentSize;
    }

    /**
     * The height of the tree. Recall that a tree with
     * only a root node has height 0
     *
     * @return the height of the tree at the root node
     */
    @Override
    public int height()
    {
        if (root == null)
        {
            return 0;
        }
        return height(root) - 1;
    }

    private int height(Node<K, V> node)
    {
        if (node == null)
        {
            return 0;
        }
        int leftHeight = height(node.left) + 1;
        int rightHeight = height(node.right) + 1;
        if (leftHeight > rightHeight)
        {
            return leftHeight;
        }
        return rightHeight;
    }

    /**
     * Test whether the RBTree is empty
     *
     * @return <code>true</code> if the tree is empty
     *         <code>false</code> if the tree is not empty
     */
    @Override
    public boolean isEmpty()
    {
        if (currentSize == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * The method to add to the RBTree. It will not allow duplicate additions.
     *
     * @param key the key to add
     * @param value the value associated with the key
     */
    @Override
    public void add(K key, V value)
    {
        Node<K, V> newNode = new Node<K, V>(key, value);
        if (root == null)
        {
            root = newNode;
            newNode.isBlack = true;
            currentSize++;
            return;
        }
        // Add if not the root
        add(root, newNode);
        root.isBlack = true;
    }

    private void add(Node<K, V> parent, Node<K, V> newNode)
    {
        if (((Comparable<K>) newNode.key).compareTo(parent.key) > 0)
        {
            if (parent.right == null)
            {
                // Right side
                parent.right = newNode;
                newNode.parent = parent;
                newNode.isLeft = false;
                currentSize++;
            }
            else
            {
                add(parent.right, newNode);
            }
        }
        else
        {
            // Left side
            if (parent.left == null)
            {
                parent.left = newNode;
                newNode.parent = parent;
                newNode.isLeft = true;
                currentSize++;
            }
            else
            {
                add(parent.left, newNode);
            }
        }
        checkColor(newNode);
    }

    /**
     * An iterator for all the keys in the RBTree. This will
     * iterate over the keys using <b>InOrder Traversal</b>
     *
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<K> iterator()
    {
        return new IteratorHelper();
    }

    /**
     * Recursively print the tree. This method should print the
     * entire tree using <em>Inorder Traversal</em> to the standard
     * output (i.e. using System.out.println or System.out.print).
     * You can print the tree one node per line, and use periods to
     * note the hierarchy of the tree.
     */
    @Override
    public void print()
    {
        print(root);
    }

    private void print(Node<K, V> node)
    {
        if (node == null)
        {
            return;
        }
        if (node.left != null)
        {
            print(node.left);
        }
        System.out.println(node.value + " is black " + node.isBlack);
        if (node.right != null)
        {
            print(node.right);
        }
    }

    /**
     * Validates that the node passed in is the correct color and changed it if
     * not.
     *
     * @param node
     */
    private void checkColor(Node<K, V> node)
    {
        if (node == root)
        {
            return;
        }
        if (!node.isBlack && !node.parent.isBlack)
        {
            correctTree(node);
        }
        if (node.parent != null)
        {
            checkColor(node.parent);
        }
    }

    /**
     * Counts the number of black nodes given a starting position.
     *
     * @param node
     * @return
     */
    private int blackNodes(Node<K, V> node)
    {
        if (node == null)
        {
            return 1;
        }
        int rightBlackNodes = blackNodes(node.right);
        int leftBlackNodes = blackNodes(node.left);
        if (rightBlackNodes != leftBlackNodes)
        {
            if (node.isBlack)
            {
                leftBlackNodes++;
            }
        }
        return leftBlackNodes;
    }

    /**
     * Checks given a node whether any rotations or color swaps need to take
     * place.
     *
     * @param node
     */
    private void correctTree(Node<K, V> node)
    {
        if (node.parent.isLeft)
        {
            if (node.parent.parent.right == null || node.parent.parent.right.isBlack)
            {
                rotate(node);
            }
            else
            {
                if (node.parent.parent.right != null)
                {
                    node.parent.parent.right.isBlack = true;
                }
                node.parent.parent.isBlack = false;
                node.parent.isBlack = true;
            }
        }
        else
        {
            if (node.parent.parent.left == null || node.parent.parent.left.isBlack)
            {
                rotate(node);
            }
            else
            {
                if (node.parent.parent.left != null)
                {
                    node.parent.parent.left.isBlack = true;
                }
                node.parent.parent.isBlack = false;
                node.parent.isBlack = true;
            }
        }
    }

    /**
     * Given node, decides what rotation needs to take place.
     *
     * @param node
     */
    private void rotate(Node<K, V> node)
    {
        if (node.isLeft)
        {
            // parent is a left child
            if (node.parent.isLeft)
            {
                rightRotate(node.parent.parent);
                node.isBlack = false;
                node.parent.isBlack = true;
                if (node.parent.right != null)
                {
                    node.parent.right.isBlack = false;
                }
            }
            else
            {
                rightLeftRotate(node.parent.parent);
                node.isBlack = true;
                node.right.isBlack = false;
                node.left.isBlack = false;
            }
        }
        else
        {
            if (node.parent.isLeft)
            {
                leftRightRotate(node.parent.parent);
                node.isBlack = true;
                node.right.isBlack = false;
                node.left.isBlack = false;
            }
            else
            {
                leftRotate(node.parent.parent);
                node.isBlack = false;
                node.parent.isBlack = true;
                if (node.parent.left != null)
                {
                    node.parent.left.isBlack = false;
                }
            }
        }
    }

    /**
     * Left rotate operation for a passed in grandparent.
     *
     * @param node
     */
    private void leftRotate(Node<K, V> node)
    {
        Node<K, V> tmp = node.right;
        node.right = tmp.left;

        if (node.right != null)
        {
            node.right.parent = node;
            node.right.isLeft = false;
        }

        // Check if root
        if (node.parent == null)
        {
            root = tmp;
            tmp.parent = null;
        }
        else
        {
            tmp.parent = node.parent;
            if (node.isLeft)
            {
                tmp.isLeft = true;
                tmp.parent.left = tmp;
            }
            else
            {
                tmp.isLeft = false;
                tmp.parent.right = tmp;
            }
        }

        tmp.left = node;
        node.isLeft = true;
        node.parent = tmp;
    }

    /**
     * Right rotate operation for a passed in grandparent.
     *
     * @param node
     */
    private void rightRotate(Node<K, V> node)
    {
        Node<K, V> tmp = node.left;
        node.left = tmp.right;

        if (node.left != null)
        {
            node.left.parent = node;
            node.left.isLeft = true;
        }

        // Check if root
        if (node.parent == null)
        {
            root = tmp;
            tmp.parent = null;
        }
        else
        {
            tmp.parent = node.parent;
            if (node.isLeft)
            {
                tmp.isLeft = true;
                tmp.parent.left = tmp;
            }
            else
            {
                tmp.isLeft = false;
                tmp.parent.right = tmp;
            }
        }

        tmp.right = node;
        node.isLeft = false;
        node.parent = tmp;
    }

    /**
     * Left right rotate operation for a passed in grandparent.
     *
     * @param node
     */
    private void leftRightRotate(Node<K, V> node)
    {
        leftRotate(node.left);
        rightRotate(node);
    }

    /**
     * Right left rotate operation for a passed in grandparent.
     *
     * @param node
     */
    private void rightLeftRotate(Node<K, V> node)
    {
        rightRotate(node.right);
        leftRotate(node);
    }

    /**
     * Moves through the tree in order traversal.
     *
     * @return next object in the list
     */
    private class IteratorHelper implements Iterator<K>
    {
        K[] keys;
        int position = 0;

        public IteratorHelper()
        {
            keys = (K[]) new Object[currentSize];
            traverse(root);
        }

        private void traverse(Node<K, V> node)
        {
            if (node == null)
            {
                return;
            }
            traverse(node.left);
            keys[position++] = node.key;
            traverse(node.right);
        }

        @Override
        public boolean hasNext()
        {
            return position < keys.length;
        }

        @Override
        public K next()
        {
            if (!hasNext())
            {
                return null;
            }
            return keys[position++];
        }
    }
}
