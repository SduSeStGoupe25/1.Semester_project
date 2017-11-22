/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Arq.IMoveableNPC;
import Domain.DomainGame;
import Domain.Room;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Victor Gram
 */
class MoveableNPC extends NPC implements Moveable, IMoveableNPC {

    private Set<String> allowedRooms;
    
    MoveableNPC(String name, int health, int armor, int attack, int level, int expDrop, String talk, Set<String> allowedRooms) {
        super(name, health, armor, attack, level, expDrop, 4, talk);
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
        Room currentRoom = (Room)DomainGame.getInstance().getRoomMap().get(nameCurrentRoom);
        if (currentRoom.getExit(direction) != null && allowedRooms.contains(((Exit) currentRoom.getExit(direction)).nextRoom(currentRoom.getName()))) {
            currentRoom.removeCharacterFromRoom(this);
            ((Room)DomainGame.getInstance().getRoomMap().get(((Exit) currentRoom.getExit(direction)).nextRoom(currentRoom.getName()))).addCharacterToRoom(this);
            return true;
        }
        return false;
    }

    @Override
    public Set<String> getAllowedRooms() {
        return allowedRooms;
    }

    void setAllowedRooms(Set<String> allowedRooms) {
        this.allowedRooms = allowedRooms;
    }

}
