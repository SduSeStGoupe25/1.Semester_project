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

    public boolean addItem(Item item, int amount) {
        if (inventory.size() < maxSlots) {      //Checks if there is room for the new
            for (Item i : inventory) {          //Checks if the item should be added as a count or as a new item
                if (i.getName().equals(item.getName())) {
                    i.addCount(amount);
                    return true;
                }
            }
            inventory.add(item);
            return true;
        }
        return false;
    }

    public void dropItem(int item) {
        inventory.remove(item);
    }

    public boolean useItem(int item) {
        if (!(inventory.get(item).getItemType().equals(ItemType.CONSUMEABLE))) { //Checks if the item is useable
            return false;
        } else {
            if (inventory.get(item).getCount() > 1) {
                inventory.get(item).reduceCount(1);
            } else {
                dropItem(item);
            }
        }
        return true;
    }

}
