package Acq;

/**
 *
 * Interface weapon
 */
public interface IWeapon extends IItem {

    /**
     * Called to get the attack value
     *
     * @return the attack value
     */
    int getAttackValue();

    /**
     * Called to get the item level
     *
     * @return the item level
     */
    int getItemLevel();

    /**
     * Called to get the base attack
     *
     * @return the base attack
     */
    int getBaseAttack();
}
