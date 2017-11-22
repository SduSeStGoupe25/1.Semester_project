/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.IItem;

/**
 *
 * @author madsd
 */
abstract class DataItem implements IItem {

    private String name;
    private int sellValue;
    private int count;
    private int MAX_COUNT;
    private int id; // 0 = armor, 1 = comsum, 2 = key, 3 = normal, 4 = weapon

    DataItem(String name, int sellValue, int count, int MAX_COUNT, int id) {
        this.name = name;
        this.sellValue = sellValue;
        this.count = count;
        this.MAX_COUNT = MAX_COUNT;
        this.id = id;
    }

    @Override
    public int getMAX_COUNT() {
        return MAX_COUNT;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSellValue() {
        return sellValue;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getId() {
        return id;
    }
}
