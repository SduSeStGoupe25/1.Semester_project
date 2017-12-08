/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Acq.IArmor;

/**
 *
 * @author madsd
 */
class DataArmor extends DataItem implements IArmor {

    private int baseArmor;
    private int armorValue;
    private int itemLevel;

    DataArmor(String name, int sellValue, int count, int MAX_COUNT, int id, int baseArmor, int armorValue) {
        super(name, sellValue, count, MAX_COUNT, id);
        this.baseArmor = baseArmor;
        this.armorValue = armorValue;
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
}
