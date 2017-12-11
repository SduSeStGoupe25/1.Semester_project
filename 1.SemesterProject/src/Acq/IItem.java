package Acq;

/**
 *
 * Interface item
 */
public interface IItem {

    /**
     * Called to get the max amount this item can contain
     *
     * @return the max count as a integer
     */
    int getMAX_COUNT();

    /**
     * Called to get the name of the item
     *
     * @return the name as a String
     */
    String getName();

    /**
     * Called to get the value the item can bee sold for
     *
     * @return the sell value as a integer
     */
    int getSellValue();

    /**
     * Called to get the amount of items this item contains
     *
     * @return the counts as a integer
     */
    int getCount();

    /**
     * Called to get the id of the item. This is used to determine type of item
     * it is
     *
     * @return the id as a integer
     */
    int getId();
}
