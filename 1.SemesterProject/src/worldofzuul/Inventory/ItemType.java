/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Inventory;

/**
 *
 * @author rasmusstamm
 */
public enum ItemType {
    ARMOR(1), WEAPON(1), CONSUMEABLE(20), NORMAL(10); 
    private final int MAX_COUNT;
    ItemType (int MAX_COUNT){
        this.MAX_COUNT = MAX_COUNT;
        
    }

    public int getMAX_COUNT() {
        return MAX_COUNT;
    }

}
