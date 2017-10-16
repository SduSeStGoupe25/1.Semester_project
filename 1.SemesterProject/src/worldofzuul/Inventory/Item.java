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
public class Item {
    private String name;
    private int sellValue;
    private ItemType itemType;
    private int itemValue;
    private int count;

    public Item(String name, int sellValue, ItemType itemType, int itemValue, int count) {
        this.name = name;
        this.sellValue = sellValue;
        this.itemType = itemType;
        this.itemValue = itemValue;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getSellValue() {
        return sellValue;
    }

    public int getItemValue() {
        return itemValue;
    }
    
    public int getCount(){
        return count;
    }
    
    public void reduceCount(int amountUsed){
        this.count -= amountUsed;
    }

    public ItemType getItemType() {
        return itemType;
    }
    
}
