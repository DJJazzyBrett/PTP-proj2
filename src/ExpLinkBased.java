/*
 * ExpLinkBased
 *
 * DJJazzyBrett
 * 
 * PTP-proj2
 *
 */

import java.util.NoSuchElementException;

/**
 * <h1>ExpLinkBased</h1>
 *
 * A singly-linked list implementation of a notation/expression
 * container; see ExpADT.java for interface details.
 * <p>
 * This implementation relies on class Node.
 *
 * @author DJJazzyBrett
 * @version 1.0 23 March 2021
 * @since 1.0
 */
public class ExpLinkBased<E> implements ExpADT<E> {
    private Node<E> bgn;

    public ExpLinkBased() {
        setBgn(null);
    } // END constructor

    private Node<E> getBgn() {
        return bgn;
    }

    private void setBgn(Node<E> bgn) {
        this.bgn = bgn;
    }

    public boolean isEmpty() {
        //return getTop() == null;
        return bgn == null;
    }

    public void prependOp(E op) {
        setBgn(new Node<E>(op, getBgn()));
    }

    public void appendOp(E op) {
        if (getBgn() == null) {
            prependOp(op);
        } else {
            Node<E> tmp = getBgn();
            while (tmp.getNext() != null) {
                tmp = tmp.getNext();
            }
            tmp.setNext(new Node<E>(op, null));
        }
    }

    public E removeOp() {
        if (isEmpty()) {
            throw new NoSuchElementException("Sorry, but ExpLinkedList object is empty!");
        } else {
            E result = getBgn().getItem();
            setBgn(getBgn().getNext());
            return result;
        }
    }

    public void removeAll() {
        while (!isEmpty()) {
            removeOp();
        }
    }

    public E checkOp() {
        if (isEmpty()) {
            throw new NoSuchElementException("Sorry, but ExpLinkedList object is empty!");
        } else {
            return getBgn().getItem();
        }
    }

    public int size() {
        return Node.numNodes(bgn);
    }

    public int countOp(E target) {
        Node<E> pointer;
        int num;
        num = 0;
        pointer = Node.search(bgn, target);
        while (pointer != null) {
            num++;
            pointer = pointer.getNext();
            pointer = Node.search(pointer, target);
        }
        return num;
    }

    public ExpLinkBased<E> cloneExp() {
        ExpLinkBased<E> clone = new ExpLinkBased<E>();
        Node<E> tmp = getBgn();
        while (tmp != null) {
            clone.appendOp(tmp.getItem());
            tmp = tmp.getNext();
        }
        return clone;
    }

    @Override
    public String toString() {
        Node<E> current = bgn;
        String prefixStr = ""; // "["
        while (current != null) {
            prefixStr = prefixStr + "" + current.getItem();
            current = current.getNext();
        }
        return prefixStr; // + "]";
    }
}
