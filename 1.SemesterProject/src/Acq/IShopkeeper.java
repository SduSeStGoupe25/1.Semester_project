package Acq;

import java.util.Set;

/**
 *
 * Interface shopkeeper
 */
public interface IShopkeeper extends INPC {

    /**
     * Called to get the item the shopkeeper can sell
     *
     * @return a Set with the name of item to sell
     */
    Set<String> getItemsToSell();
}
