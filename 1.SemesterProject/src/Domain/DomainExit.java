/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

//@author Mikkel Pedersen
import Arq.IExit;
import Arq.IInventory;
import Arq.IItem;

class DomainExit implements IExit {

    private String name1;
    private String name2;
    private boolean locked;
    private int lockID;

    public DomainExit(){};
    public DomainExit(String room1, String room2) {
        this.name1 = room1;
        this.name2 = room2;
        locked = false;
        lockID = 0;
    }

    public DomainExit(String room1, String room2, boolean lock, int lockID) {
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
                if (item instanceof DomainKey) {
                    if (((DomainKey) item).getKeyID() == lockID) {
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
    
    public void setName1(String name) {
        name1 = name;
    }

    @Override
    public String getName2() {
        return name2;
    }
    
    public void setName2(String name) {
        name2 = name;
    }

    @Override
    public boolean getLocked() {
        return locked;
    }
    
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public int getlockID() {
        return lockID;
    }
    
    public void setlockID(int lockID) {
        this.lockID = lockID;
    }
}
