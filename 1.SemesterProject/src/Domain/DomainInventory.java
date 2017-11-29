/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Arq.IInventory;
import Arq.IItem;
import java.util.ArrayList;

/**
 *
 * @author rasmusstamm
 */
class DomainInventory implements IInventory{

    private ArrayList<IItem> inventory;
    private int maxSlots;

    public DomainInventory(){
        inventory = new ArrayList();
    }
    public DomainInventory(int maxSlots) {
        inventory = new ArrayList<>();
        this.maxSlots = maxSlots;
    }

    boolean addItem(DomainItem item, int amount) {
        if (inventory.size() != 0) {
            for (IItem i : inventory) {
                if (i.getName().equals(item.getName())) {
                    if (i.getCount() + amount <= i.getMAX_COUNT()) {
                        ((DomainItem) i).addCount(amount);
                        return true;
                    } else {
                        return addInv(item, amount);
                    }
                }
            }
        } 
            return addInv(item, amount);
    }

    private boolean addInv(DomainItem item, int amount) {
        if (amount / item.getMAX_COUNT() + inventory.size() <= maxSlots) {
            int amountBack = amount;
            for (IItem j : inventory) {
                if (j.getName().equals(item.getName())) {
                    if (j.getCount() < j.getMAX_COUNT()) {
                        int add = j.getMAX_COUNT() - j.getCount();
                        ((DomainItem) j).addCount(add);
                        amountBack -= add;
                        break;
                    }
                }
            }
            while (amountBack > 0) {
                DomainItem itemToAdd = item;
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

    boolean removeItem(IItem item, int amount) {
        int totalAmount = 0;
        for (IItem i : inventory) {
            if (i.getName().equals(item.getName())) {
                totalAmount += i.getCount();
            }

        }
        if (totalAmount >= amount) {
            int amountBack = amount;
            for (int i = inventory.size() - 1; i >= 0; i--) {
                IItem j = inventory.get(i);
                if (j.getName().equals(item.getName())) {
                    if (amountBack >= j.getCount()) {
                        amountBack -= j.getCount();
                        inventory.remove(i);
                        return true;
                    } else {
                        ((DomainItem) j).setCount(j.getCount() - amountBack);
                        return true;
                    }
                }

            }
        }
        return false;
    }

    @Override
    public ArrayList<IItem> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<IItem> inventory) {
        this.inventory = inventory;
    }

    @Override
    public int getMaxSlots() {
        return maxSlots;
    }

    public void setMaxSlots(int maxSlots) {
        this.maxSlots = maxSlots;
    }
}
