/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.IArmor;

/**
 *
 * @author madsd
 */
public class DataArmor extends DataItem implements IArmor {

    private int baseArmor;
    private int armorValue;
    private int itemLevel;

    public DataArmor(String name, int sellValue, int count, int MAX_COUNT, int id, int baseArmor, int armorValue) {
        super(name, sellValue, count, MAX_COUNT, id);
        this.baseArmor = baseArmor;
        this.armorValue = armorValue;
    }

    @Override
    public int getArmorValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getItemLevel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
