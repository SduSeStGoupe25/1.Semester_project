/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

//@author Mikkel Pedersen
import Domain.Inventory.Inventory;
import Domain.Inventory.Item;
import Domain.Inventory.Key;

public class Exit {

    private String name1;
    private String name2;

//    private Room room1;
//    private Room room2;
    private boolean locked;
    private int lockID;

//    public Exit(Room room1, Room room2) {
//        this.room1 = room1;
//        this.room2 = room2;
//        locked = false;
//        lockID = 0;
//    }
    public Exit(String room1, String room2) {
        this.name1 = room1;
        this.name2 = room2;
        locked = false;
        lockID = 0;
    }

    public Exit(String room1, String room2, boolean lock, int lockID) {
        this.name1 = room1;
        this.name2 = room2;
        this.locked = lock;
        this.lockID = lockID;
    }

    public String nextRoom(String currentRoom) {
        if (currentRoom.equals(name1)) {
            return name2;
        } else {
            return name1;
        }
    }

    public boolean isLocked(Inventory inventory) {
        if (!locked) {
            return false;
        } else {
            for (Item item : inventory.getInventory()) {
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
}
