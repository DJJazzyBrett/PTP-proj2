/*
 * ExpADT
 *
 * DJJazzyBrett
 * 
 * PTP-proj2
 *
 */

/**
 * <h1>ExpADT</h1>
 *
 * Interface for notation/expression structure.
 *
 * @author DJJazzyBrett
 * @version 1.0 23 March 2021
 * @since 1.0
 */
public interface ExpADT<E> {

    /**
     * @param none
     * @return boolean True if empty, otherwise false
     */
    public boolean isEmpty();

    /**
     * @param E op The element to prepend to Exp
     * @return none
     */
    public void prependOp(E op);

    /**
     * @param none
     * @return E The element at the bgn of Exp
     * @throws NoSuchElementException if Exp is empty
     */
    public E removeOp();

    /**
     * @param none
     * @return E The item at the bgn of Exp
     * @throws NoSuchElementException if Exp is empty
     */
    public E checkOp();

    /**
     * @param E op The element to append to Exp
     * @return none
     */
    public void appendOp(E op);

    /**
     * @param none
     * @return int The number of elements in Exp
     */
    public int size();

    /**
     * @param none
     * @return String The elements in the Exp
     */
    public String toString();
}
