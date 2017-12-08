/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Acq.IInventory;
import Acq.IItem;
import java.util.ArrayList;

/**
 *
 * @author madsd
 */
class DataInventory implements IInventory {

    private ArrayList<IItem> inventory;
    private int maxSlots;

    @Override
    public ArrayList<IItem> getInventory() {
        return inventory;
    }

    @Override
    public int getMaxSlots() {
        return maxSlots;
    }
}
