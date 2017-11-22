/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.IInventory;
import Arq.IItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author madsd
 */
class DataInventory implements IInventory {

    private ArrayList<IItem> inventory;
    private int maxSlots;

    @Override
    public List<IItem> getInventory() {
        return inventory;
    }

    @Override
    public int getMaxSlots() {
        return maxSlots;
    }
}
