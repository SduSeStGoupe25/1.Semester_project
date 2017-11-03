/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldofArthur.Inventory;

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
        if (inventory.size() != 0) {
            for (Item i : inventory) {
                if (i.getName().equals(item.getName())) {
                    if (i.getCount() + amount <= i.getMAX_COUNT()) {
                        i.addCount(amount);
                        return true;
                    } else {
                        return addInv(amount, item);
                    }
                }
            }
        } 
            return addInv(amount, item);
    }

    private boolean addInv(int amount, Item item) {
        if (amount / item.getMAX_COUNT() + inventory.size() <= maxSlots) {
            int amountBack = amount;
            for (Item j : inventory) {
                if (j.getName().equals(item.getName())) {
                    if (j.getCount() < j.getMAX_COUNT()) {
                        int add = j.getMAX_COUNT() - j.getCount();
                        j.addCount(add);
                        amountBack -= add;
                        break;
                    }
                }
            }
            while (amountBack > 0) {
                Item itemToAdd = item;
                if (amountBack > item.getMAX_COUNT()) {
                    itemToAdd.setCount(item.getMAX_COUNT());
                    inventory.add(itemToAdd);
                    amountBack -= item.getMAX_COUNT();
                } else {
                    itemToAdd.setCount(amountBack);
                    inventory.add(itemToAdd);
                    amountBack = 0;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean removeItem(Item item, int amount) {
        int totalAmount = 0;
        for (Item i : inventory) {
            if (i.getName().equals(item.getName())) {
                totalAmount += i.getCount();
            }

        }
        if (totalAmount >= amount) {
            int amountBack = amount;
            for (int i = inventory.size() - 1; i >= 0; i--) {
                Item j = inventory.get(i);
                if (j.getName().equals(item.getName())) {
                    if (amountBack >= j.getCount()) {
                        amountBack -= j.getCount();
                        inventory.remove(i);
                        return true;
                    } else {
                        j.setCount(j.getCount() - amountBack);
                        return true;
                    }
                }

            }
        }
        return false;
    }

}
