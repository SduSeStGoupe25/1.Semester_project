/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

//@author Mikkel Pedersen

import Arq.IWeapon;



class Weapon extends Item implements IWeapon{
    
    private int baseAttack;
    private int attackValue;
    private int itemLevel;
    
    Weapon(String name, int sellValue, int count, int baseAttack, int itemLevel) {
        super(name, sellValue, count, 1, 4);
        this.baseAttack = baseAttack;
        this.itemLevel = itemLevel;
        attackValue = baseAttack + itemLevel;
    }
    
    @Override
    public String toString() {
        return getName() + "Weapon{" + "attackValue=" + attackValue + ", itemLevel=" + itemLevel + '}';
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
    
    
    
    
}
