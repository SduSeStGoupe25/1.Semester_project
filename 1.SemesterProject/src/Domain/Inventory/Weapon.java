/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Inventory;

//@author Mikkel Pedersen


public class Weapon extends Item{
    
    private int baseAttack;
    private int attackValue;
    private int itemLevel;
    
    public Weapon(String name, int sellValue, int count, int baseAttack, int itemLevel) {
        super(name, sellValue, count, 1, 4);
        this.baseAttack = baseAttack;
        this.itemLevel = itemLevel;
        attackValue = baseAttack + itemLevel;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public int getItemLevel() {
        return itemLevel;
    }
    

    @Override
    public String toString() {
        return getName() + "Weapon{" + "attackValue=" + attackValue + ", itemLevel=" + itemLevel + '}';
    }
    
    
}
