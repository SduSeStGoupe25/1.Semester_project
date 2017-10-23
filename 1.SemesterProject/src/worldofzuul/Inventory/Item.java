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
public class Item implements Cloneable{
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
    public Object clone() {
      Object clone = null;
      
      try {
         clone = super.clone();
         
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
      
      return clone;
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
    
    public void addCount(int amountAdded){
        this.count += amountAdded;
    }
    
    public void setCount(int count){
        this.count = count;
    }

    public ItemType getItemType() {
        return itemType;
    }
    
}
