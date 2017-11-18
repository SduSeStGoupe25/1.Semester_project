/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Inventory;

/**
 *
 * @author rasmusstamm
 */
public abstract class Item {
    private String name;
    private int sellValue;
    private int count;
    private final int MAX_COUNT;
    private int id; // 0 = armor, 1 = comsum, 2 = key, 3 = normal, 4 = weapon

    public Item(String name, int sellValue, int count, int MAX_COUNT, int id) {
        this.name = name;
        this.sellValue = sellValue;
        this.count = count;
        this.MAX_COUNT = MAX_COUNT;
        this.id = id;
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

    public int getId() {
        return id;
    }
}
