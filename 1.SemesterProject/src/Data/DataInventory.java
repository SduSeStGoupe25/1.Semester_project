package Data;

import Acq.IInventory;
import Acq.IItem;
import java.util.ArrayList;

/**
 * DataInventory class
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
