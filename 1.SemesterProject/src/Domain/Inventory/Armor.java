/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Inventory;

//@author Mikkel Pedersen


public class Armor extends Item{
    
    private int baseArmor;
    private int armorValue;
    private int itemLevel;
    
    public Armor(String name, int sellValue, int count, int baseArmor, int itemLevel) {
        super(name, sellValue, count, 1);
        this.baseArmor = baseArmor;
        this.itemLevel = itemLevel;
        armorValue = baseArmor + itemLevel;
    }

    public int getArmorValue() {
        return armorValue;
    }

    public int getItemLevel() {
        return itemLevel;
    }
    

    @Override
    public String toString() {
        return getName() + "Weapon{" + "armorValue=" + armorValue + ", itemLevel=" + itemLevel + '}';
    }
    
    
}
