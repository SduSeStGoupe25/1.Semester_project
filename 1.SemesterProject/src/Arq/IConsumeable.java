package Arq;

/**
 * Interface for a item of the type armor
 *
 */
public interface IConsumeable extends IItem {

    /**
     * Called to get the use value of the item. The amount of health that will
     * be restored
     *
     * @return the use value of the item
     */
    int getUseValue();

    /**
     * Called to get hunger value of the item. The amount of hunger that will be
     * restored
     *
     * @return the hunger value of the item
     */
    int getHungerValue();
}
