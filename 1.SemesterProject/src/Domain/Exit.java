package Domain;

import Acq.IExit;
import Acq.IInventory;
import Acq.IItem;

/**
 * 
 *  
 * 
 */
class Exit implements IExit {

    private final String name1;
    private final String name2;
    private boolean locked;
    private final int lockID;

    Exit(String room1, String room2) {
        this.name1 = room1;
        this.name2 = room2;
        locked = false;
        lockID = 0;
    }

    Exit(String room1, String room2, boolean lock, int lockID) {
        this.name1 = room1;
        this.name2 = room2;
        this.locked = lock;
        this.lockID = lockID;
    }

    String nextRoom(String currentRoom) {
        if (currentRoom.equals(name1)) {
            return name2;
        } else {
            return name1;
        }
    }

    boolean isLocked(IInventory inventory) {
        if (!locked) {
            return false;
        } else {
            for (IItem item : inventory.getInventory()) {
                if (item instanceof Key) {
                    if (((Key) item).getKeyID() == lockID) {
                        locked = false;
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String getName1() {
        return name1;
    }

    @Override
    public String getName2() {
        return name2;
    }

    @Override
    public boolean getLocked() {
        return locked;
    }

    @Override
    public int getlockID() {
        return lockID;
    }
}
