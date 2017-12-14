package Domain;

import Acq.IInventory;
import Acq.IItem;
import java.util.ArrayList;

/**
 * Inventory class
 */
class Inventory implements IInventory {

    private ArrayList<IItem> inventory;
    private int maxSlots;

    Inventory(int maxSlots) {
        inventory = new ArrayList<>();
        this.maxSlots = maxSlots;
    }

    boolean addItem(Item item, int amount) {
        //Checks if there enough space in the inventory to the desired amount
        if (amount / item.getMAX_COUNT() + inventory.size() <= maxSlots) {
            //integer to keep track of the amount there are back
            int amountBack = amount;
            //Checks if the inventory is empty
            if (!inventory.isEmpty()) {
                //Goes through all the items in the inventory, to see if there 
                //are a item of the type of the item being added with available space. 
                for (IItem i : inventory) {
                    //Checks if item has the same name as the current item in the inventory
                    if (i.getName().equals(item.getName())) {
                        //Checks if there are space enough to add all the desired amount
                        if (i.getCount() + amountBack <= i.getMAX_COUNT()) {
                            //Adds amount to count
                            ((Item) i).addCount(amountBack);
                            //Returns true, all is good
                            return true;
                        } else {
                            //Checks if there are some free space to add a part of the amount
                            if (i.getCount() < i.getMAX_COUNT()) {
                                //Calulates the free space
                                int add = i.getMAX_COUNT() - i.getCount();
                                //Adds the calulatet amount
                                ((Item) i).addCount(add);
                                //Counts amountBack down
                                amountBack -= add;
                            }
                        }
                    }
                }
            }
            //Runnes until amountBack are zero
            while (amountBack > 0) {
                //Creates a new item
                Item itemToAdd = (Item) DomainData.getInstance().getItem(item.getName());
                System.out.println("ITEM " + itemToAdd);
                //Checks if the amountBack is bigger than the amount that can be in one item
                if (amountBack > item.getMAX_COUNT()) {
                    //Add the maxcount the item
                    itemToAdd.setCount(item.getMAX_COUNT());
                    //Add itemToAdd to inventory
                    inventory.add(itemToAdd);
                    //Counts amountBack down
                    amountBack -= item.getMAX_COUNT();
                } else { //If amountBack can be in one item
                    itemToAdd.setCount(amountBack);
                    inventory.add(itemToAdd);
                    //Sets amountBack to zero and breaks the loop
                    amountBack = 0;
                }
            }
            //Returns true, all is good
            return true;
        }
        //Returns false amount is to big
        return false;
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
                        ((Item) j).setCount(j.getCount() - amountBack);
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

    void setInventory(ArrayList<IItem> inventory) {
        this.inventory = inventory;
    }

    @Override
    public int getMaxSlots() {
        return maxSlots;
    }

    void setMaxSlots(int maxSlots) {
        this.maxSlots = maxSlots;
    }
}
