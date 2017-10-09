/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Inventory;

import java.util.ArrayList;

/**
 *
 * @author rasmusstamm
 */
public class Inventory {

    private ArrayList<Item> inventory;
    private int maxSlots;

    public Inventory(int maxSlots) {
        inventory = new ArrayList<>();
        this.maxSlots = maxSlots;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public boolean addItem(Item item) {
        if (inventory.size() < maxSlots) {
            inventory.add(item);
            return true;
        }
        return false;
    }
    
    public void dropItem(int item){
        inventory.remove(item);
    }

}