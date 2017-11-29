/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Arq.IItem;

/**
 *
 * @author rasmusstamm
 */
abstract class DomainItem implements IItem{
    private String name;
    private int sellValue;
    private int count;
    private int MAX_COUNT;
    private int id; // 0 = armor, 1 = comsum, 2 = key, 3 = normal, 4 = weapon

    public DomainItem(){};
    public DomainItem(String name, int sellValue, int count, int MAX_COUNT, int id) {
        this.name = name;
        this.sellValue = sellValue;
        this.count = count;
        this.MAX_COUNT = MAX_COUNT;
        this.id = id;
    }
    
    void addCount(int countToAdd) {
        countToAdd += countToAdd;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", sellValue=" + sellValue + ", count=" + count + ", MAX_COUNT=" + MAX_COUNT + ", id=" + id + '}';
    }
    
    

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getSellValue() {
        return sellValue;
    }

    public void setSellValue(int sellValue) {
        this.sellValue = sellValue;
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }  

    @Override
    public int getMAX_COUNT(){
        return MAX_COUNT;
    }
    
    public void setMAX_COUNT(int max) {
        MAX_COUNT = max;
    }
}
