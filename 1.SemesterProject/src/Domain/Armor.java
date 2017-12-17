package Domain;

import Acq.IArmor;

/**
 * Armor class, a subclass of Item
 */
class Armor extends Item implements IArmor {

    /**
     * Armor at level 1
     */
    private int baseArmor;

    /**
     * Items armor value
     */
    private int armorValue;

    /**
     * Items level
     */
    private int itemLevel;

    /**
     * Constructor
     *
     * @param name {@link Item#name}
     * @param sellValue {@link Item#sellValue}
     * @param count {@link Item#count}
     * @param baseArmor {@link #baseArmor}
     * @param itemLevel {@link #itemLevel}
     */
    Armor(String name, int sellValue, int count, int baseArmor, int itemLevel) {
        super(name, sellValue, count, 1, 0);
        this.baseArmor = baseArmor;
        this.itemLevel = itemLevel;
        armorValue = baseArmor + itemLevel;
    }

    @Override
    public int getArmorValue() {
        return armorValue;
    }

    @Override
    public int getItemLevel() {
        return itemLevel;
    }

    @Override
    public int getBaseArmor() {
        return baseArmor;
    }

    @Override
    public String toString() {
        return super.toString() + " armorValue=" + armorValue;
    }
}
