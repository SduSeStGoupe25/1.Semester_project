/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author Victor Gram
 */
public class MoveableNPC extends NPC implements Moveable {
    private Room currentRoom;

    public MoveableNPC(String name, int health, int armor, int attack, int level, int expDrop, String talk, Room currentRoom) {
        super(name, health, armor, attack, level, expDrop, talk);
        this.currentRoom = currentRoom;
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet.");  
    }
    
    
}
