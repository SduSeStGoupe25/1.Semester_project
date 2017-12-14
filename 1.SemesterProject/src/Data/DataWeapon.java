package Data;

import Acq.IWeapon;

/**
 * DataWeapon
 */
class DataWeapon extends DataItem implements IWeapon {

    private int baseAttack;
    private int attackValue;
    private int itemLevel;

    DataWeapon(String name, int sellValue, int count, int MAX_COUNT, int id, int baseAttack, int itemLevel) {
        super(name, sellValue, count, MAX_COUNT, id);
        this.baseAttack = baseAttack;
        this.itemLevel = itemLevel;
        attackValue = baseAttack + itemLevel;
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
    public int getBaseAttack() {
        return baseAttack;
    }
}
