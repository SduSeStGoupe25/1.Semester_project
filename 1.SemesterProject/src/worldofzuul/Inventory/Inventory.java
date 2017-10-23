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
        for (Item i : inventory) {
            if (i.getName().equals(item.getName())){
                if (i.getCount()+ amount <= i.getItemType().getMAX_COUNT()){
                    i.addCount(amount);
                    return true;
                }
                else {
                    if(amount/i.getItemType().getMAX_COUNT()+inventory.size()<=maxSlots){
                        int amountBack = amount;
                        for (Item j : inventory) {
                            if (j.getName().equals(item.getName())){
                                if (j.getCount()<j.getItemType().getMAX_COUNT()){
                                    int add = j.getItemType().getMAX_COUNT() - j.getCount();
                                            j.addCount(add);
                                    amountBack -= add;
                                    break;
                                }
                            }
                            
                        }
                        while(amountBack > 0){
                            Item itemToAdd = Stash.getItem(item.getName().toLowerCase());
                            if (amountBack>item.getItemType().getMAX_COUNT()){
                                itemToAdd.setCount(item.getItemType().getMAX_COUNT());
                                inventory.add(itemToAdd);
                                amountBack-=item.getItemType().getMAX_COUNT();
                            }else {
                                itemToAdd
                            }
                            
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
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
