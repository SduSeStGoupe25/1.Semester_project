/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

//@author Mikkel Pedersen

import Arq.IWeapon;



class DomainWeapon extends DomainItem implements IWeapon{
    
    private int baseAttack;
    private int attackValue;
    private int itemLevel;

    public DomainWeapon() {
    }
    
    
    public DomainWeapon(String name, int sellValue, int count, int baseAttack, int itemLevel) {
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

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    @Override
    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    @Override
    public int getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(int itemLevel) {
        this.itemLevel = itemLevel;
    }
    
    
    
    
}
