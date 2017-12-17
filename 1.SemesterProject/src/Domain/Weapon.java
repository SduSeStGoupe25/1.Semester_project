package Domain;

import Acq.IWeapon;

/**
 * The weapon class
 */
class Weapon extends Item implements IWeapon {

    /**
     * The attack at level 1
     */
    private int baseAttack;

    /**
     * The attack value
     */
    private int attackValue;

    /**
     * The item level
     */
    private int itemLevel;

    /**
     * Constructor
     *
     * @param name {@link Item#name}
     * @param sellValue {@link Item#sellValue}
     * @param count {@link Item#count}
     * @param baseAttack {@link #baseAttack}
     * @param itemLevel {@link #itemLevel}
     */
    Weapon(String name, int sellValue, int count, int baseAttack, int itemLevel) {
        super(name, sellValue, count, 1, 4);
        this.baseAttack = baseAttack;
        this.itemLevel = itemLevel;
        attackValue = baseAttack + itemLevel;
    }

    @Override
    public int getBaseAttack() {
        return baseAttack;
    }

    @Override
    public int getAttackValue() {
        return attackValue;
    }

    @Override
    public int getItemLevel() {
        return itemLevel;
    }

    @Override
    public String toString() {
        return super.toString() + " attackValue=" + attackValue;
    }
}
