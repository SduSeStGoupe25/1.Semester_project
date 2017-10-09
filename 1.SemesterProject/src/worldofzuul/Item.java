/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author rasmusstamm
 */
public class Item {
    private String name;
    private int sellValue;
    private ItemType itemType;
    private int ItemValue;
    private int count;

    public Item(String name, int sellValue, ItemType itemType, int ItemValue, int count) {
        this.name = name;
        this.sellValue = sellValue;
        this.itemType = itemType;
        this.ItemValue = ItemValue;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getSellValue() {
        return sellValue;
    }

    public int getItemValue() {
        return ItemValue;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
    
}
