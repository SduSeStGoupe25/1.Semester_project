/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Entity;

import Domain.Game;
import Domain.Room;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Victor Gram
 */
public class MoveableNPC extends NPC implements Moveable {

    private Set<String> allowedRooms;

    public MoveableNPC(String name, int health, int armor, int attack, int level, int expDrop, String talk, Set<String> allowedRooms) {
        super(name, health, armor, attack, level, expDrop, talk);
        this.allowedRooms = allowedRooms;
    }

    @Override
    public void move(String nameCurrentRoom) {//Fix before deadline
        Random random = new Random();
        if (random.nextInt(2) > 0) {
            while (true) {
                switch (random.nextInt(4)) {
                    case 0:
                        if (moveNPC(nameCurrentRoom, "north")) {
                            return;
                        }
                        break;
                    case 1:
                        if (moveNPC(nameCurrentRoom, "south")) {
                            return;
                        }
                        break;
                    case 2:
                        if (moveNPC(nameCurrentRoom, "east")) {
                            return;
                        }
                        break;
                    case 3:
                        if (moveNPC(nameCurrentRoom, "west")) {
                            return;
                        }
                        break;
                }
            }
        }
    }

    private boolean moveNPC(String nameCurrentRoom, String direction) {
        Room currentRoom = Game.getInstance().getRoomMap().get(nameCurrentRoom);
        if (currentRoom.getExit(direction) != null && allowedRooms.contains(currentRoom.getExit(direction).nextRoom(currentRoom.getName()))) {
            currentRoom.removeCharacterFromRoom(this);
            Game.getInstance().getRoomMap().get(currentRoom.getExit(direction).nextRoom(currentRoom.getName())).addCharacterToRoom(this);
            return true;
        }
        return false;
    }

}
