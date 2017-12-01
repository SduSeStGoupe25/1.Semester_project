package Arq;

import java.util.ArrayList;

/**
 *
 * Interface for Inventory
 */
public interface IInventory {

    /**
     * Called to get a list that contains the items in the inventory
     *
     * @return a list with IItem
     */
    ArrayList<IItem> getInventory();

    /**
     * Called to get the max slots of the inventory
     *
     * @return the max amount of slots as a integer
     */
    int getMaxSlots();
}
