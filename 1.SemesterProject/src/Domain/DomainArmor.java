/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

//@author Mikkel Pedersen
import Arq.IArmor;

class DomainArmor extends DomainItem implements IArmor {

    private int baseArmor;
    private int armorValue;
    private int itemLevel;

    public DomainArmor(){};
    
    DomainArmor(String name, int sellValue, int count, int baseArmor, int itemLevel) {
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
