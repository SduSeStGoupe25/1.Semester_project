/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Arq.IMoveableNPC;
import Domain.DomainGame;
import Domain.DomainRoom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Victor Gram
 */
class DomainMoveableNPC extends DomainNPC implements Moveable, IMoveableNPC {

    private Set<String> allowedRooms;
    
    public DomainMoveableNPC() {
        allowedRooms = new HashSet();
    }
    
    public DomainMoveableNPC(String name, int health, int armor, int attack, int level, int expDrop, String talk, Set<String> allowedRooms) {
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
        DomainRoom currentRoom = (DomainRoom)DomainGame.getInstance().getRoomMap().get(nameCurrentRoom);
        if (currentRoom.getExit(direction) != null && allowedRooms.contains(((DomainExit) currentRoom.getExit(direction)).nextRoom(currentRoom.getName()))) {
            currentRoom.removeCharacterFromRoom(this);
            ((DomainRoom)DomainGame.getInstance().getRoomMap().get(((DomainExit) currentRoom.getExit(direction)).nextRoom(currentRoom.getName()))).addCharacterToRoom(this);
            return true;
        }
        return false;
    }

    @Override
    public Set<String> getAllowedRooms() {
        return allowedRooms;
    }

    public void setAllowedRooms(Set<String> allowedRooms) {
        this.allowedRooms = allowedRooms;
    }

}
