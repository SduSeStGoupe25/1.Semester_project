/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Acq.IItem;

/**
 *
 * @author rasmusstamm
 */
abstract class Item implements IItem{
    private String name;
    private int sellValue;
    private int count;
    private final int MAX_COUNT;
    private int id; // 0 = armor, 1 = comsum, 2 = key, 3 = normal, 4 = weapon

    Item(String name, int sellValue, int count, int MAX_COUNT, int id) {
        this.name = name;
        this.sellValue = sellValue;
        this.count = count;
        this.MAX_COUNT = MAX_COUNT;
        this.id = id;
    }
    
    void addCount(int countToAdd) {
        count += countToAdd;
    }

    @Override
    public String toString() {
        return name + " \n" + "Sells for: " + sellValue + " gold coins." + "\t" + "Can be bought for: " + (sellValue * 2) + " gold coins " + " AMOUNT " + count;
    }
    
    

    @Override
    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public int getSellValue() {
        return sellValue;
    }

    void setSellValue(int sellValue) {
        this.sellValue = sellValue;
    }

    @Override
    public int getCount() {
        return count;
    }

    void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }  

    @Override
    public int getMAX_COUNT(){
        return MAX_COUNT;
    }
}
