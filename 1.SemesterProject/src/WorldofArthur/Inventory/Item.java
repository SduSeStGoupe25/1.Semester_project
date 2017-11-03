/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldofArthur.Inventory;

/**
 *
 * @author rasmusstamm
 */
public abstract class Item {
    private String name;
    private int sellValue;
    private int count;
    private final int MAX_COUNT;

    public Item(String name, int sellValue, int count, int MAX_COUNT) {
        this.name = name;
        this.sellValue = sellValue;
        this.count = count;
        this.MAX_COUNT = MAX_COUNT;
    }

    public int getMAX_COUNT() {
        return MAX_COUNT;
    }
    
    public String getName() {
        return name;
    }

    public int getSellValue() {
        return sellValue;
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
}
