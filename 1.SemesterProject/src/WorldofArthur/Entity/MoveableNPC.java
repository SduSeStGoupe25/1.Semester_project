/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldofArthur.Entity;

import java.util.Random;
import java.util.Set;
import WorldofArthur.Room;

/**
 *
 * @author Victor Gram
 */
public class MoveableNPC extends NPC implements Moveable {

    private Set<Room> allowedRooms;

    public MoveableNPC(String name, int health, int armor, int attack, int level, int expDrop, String talk, Set<Room> allowedRooms) {
        super(name, health, armor, attack, level, expDrop, talk);
        this.allowedRooms = allowedRooms;
    }

    @Override
    public void move(Room currentRoom) {//Fix before deadline
        Random random = new Random();
        if (random.nextInt(2) > 0) {
            while (true) {
                switch (random.nextInt(4)) {
                    case 0:
                        if (moveNPC(currentRoom, "north")) {
                            return;
                        }
                        break;
                    case 1:
                        if (moveNPC(currentRoom, "south")) {
                            return;
                        }
                        break;
                    case 2:
                        if (moveNPC(currentRoom, "east")) {
                            return;
                        }
                        break;
                    case 3:
                        if (moveNPC(currentRoom, "west")) {
                            return;
                        }
                        break;
                }
            }
        }
    }

    private boolean moveNPC(Room currentRoom, String direction) {
        if (currentRoom.getExit(direction) != null && allowedRooms.contains(currentRoom.getExit(direction))) {
            currentRoom.removeCharacterFromRoom(this);
            currentRoom.getExit(direction).nextRoom(currentRoom).addCharacterToRoom(this);
            return true;
        }
        return false;
    }

}
