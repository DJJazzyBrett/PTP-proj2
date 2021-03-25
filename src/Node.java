/*
 * Node
 *
 * DJJazzyBrett
 * 
 * PTP-proj2
 *
 */

/**
 * <h1>Node</h1>
 *
 * A separate class that is used in the implementation
 * of linked-list data structures.
 *
 * @author DJJazzyBrett
 * @version 2.0 23 March 2021
 * @since 1.0
 */
public class Node<E> {
    private E item;
    private Node<E> next;

    public Node() {
        item = null;
        next = null;
    } // END constructor

    public Node(E dataItem) {
        item = dataItem;
        next = null;
    } // END constructor

    public Node(E dataItem, Node<E> nodeNext) {
        item = dataItem;
        next = nodeNext;
    } // END constructor

    /**
     * Accessor method for item retrieval
     *
     * @param none
     * @return E Return item associated with node
     */
    public E getItem() {
        return item;
    }

    /**
     * Mutator method for item
     *
     * @param E dataItem
     * @return none
     */
    public void setItem(E dataItem) {
        item = dataItem;
    }

    /**
     * Accessor method for node retrieval
     *
     * @param none
     * @return Node<E> Node to which current is pointing
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * Mutator method for node
     *
     * @param Node<E> nodeNext Node to which current is pointing
     * @return none
     */
    public void setNext(Node<E> nodeNext) {
        next = nodeNext;
    }

    /**
     * Number of nodes in linked structure
     *
     * @param Node<E> node Starting node of interest
     * @return int Number of nodes
     */
    public static <E> int numNodes(Node<E> top) {
        Node<E> pointer;
        int num;
        num = 0; // initialize target variable
        for (pointer = top; pointer != null; pointer = pointer.next)
            num++;
        return num;
    }

    /**
     * Search method for linked data structures
     *
     * @param Node<E> top Starting node of interest
     * @param E target The element for which you're searching
     * @return none
     */
    public static <E> Node<E> search(Node<E> top, E target) {
        Node<E> pointer;

        if (target != null) {
            for (pointer = top; pointer != null; pointer = pointer.next) {
                if (target.equals(pointer.item)) {
                    return pointer;
                }
            }
        }
        return null;
    }
}
