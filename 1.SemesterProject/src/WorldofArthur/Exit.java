/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldofArthur;

//@author Mikkel Pedersen

import WorldofArthur.Inventory.Inventory;
import WorldofArthur.Inventory.Item;
import WorldofArthur.Inventory.Key;



public class Exit {
    private Room room1;
    private Room room2;
    private boolean locked;
    private int lockID;

    public Exit(Room room1, Room room2) {
        this.room1 = room1;
        this.room2 = room2;
        locked = false;
        lockID = 0;
    }
    
    public Exit(Room room1, Room room2, boolean lock, int lockID) {
        this.room1 = room1;
        this.room2 = room2;
        this.locked = lock;
        this.lockID = lockID;
    }
    
    public Room nextRoom(Room currentRoom){
        if (currentRoom.equals(room1)) {
            return room2;
        }else{
            return room1;
        }
    }
    
    public boolean isLocked(Inventory inventory){
        if (!locked) {
            return false;
        }else {
            for (Item item : inventory.getInventory()) {
                if (item instanceof Key) {
                    if (((Key)item).getKeyID() == lockID) {
                        locked = false;
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
