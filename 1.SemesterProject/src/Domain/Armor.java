package Domain;

import Acq.IArmor;

class Armor extends Item implements IArmor {

    private int baseArmor;
    private int armorValue;
    private int itemLevel;

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
        return super.toString() + "Armor{" + "baseArmor=" + baseArmor + ", armorValue=" + armorValue + ", itemLevel=" + itemLevel + '}';
    }


}
