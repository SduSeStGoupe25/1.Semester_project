package Domain;

import Acq.IWeapon;

/**
 * The weapon class
 */
class Weapon extends Item implements IWeapon {

    private int baseAttack;
    private int attackValue;
    private int itemLevel;

    Weapon(String name, int sellValue, int count, int baseAttack, int itemLevel) {
        super(name, sellValue, count, 1, 4);
        this.baseAttack = baseAttack;
        this.itemLevel = itemLevel;
        attackValue = baseAttack + itemLevel;
        super.setName(name);
    }

    @Override
    public int getBaseAttack() {
        return baseAttack;
    }

    void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    @Override
    public int getAttackValue() {
        return attackValue;
    }

    void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    @Override
    public int getItemLevel() {
        return itemLevel;
    }

    void setItemLevel(int itemLevel) {
        this.itemLevel = itemLevel;
    }

    @Override
    public String toString() {
        return super.toString() + " attackValue=" + attackValue;
    }
}
