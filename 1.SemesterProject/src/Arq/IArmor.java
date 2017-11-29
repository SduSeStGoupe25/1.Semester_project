package Arq;

/**
 * Interface for a item of the type armor
 *
 */
public interface IArmor extends IItem {

    /**
     * Called to get the armorValue of the item
     *
     * @return Returns the armorValue as a integer
     */
    int getArmorValue();

    /**
     * Called to get the level of the item
     *
     * @return Returns the level as a integer
     */
    int getItemLevel();

    /**
     * Called to get the baseArmor (armor at level one) of the item
     *
     * @return Returns the basearmor as a integer
     */
    int getBaseArmor();
}
