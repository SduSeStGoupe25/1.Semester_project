/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldofArthur.Entity;

import WorldofArthur.Inventory.*;
import WorldofArthur.Room;

/**
 *
 * @author Victor Gram
 */
public class Monster extends CharacterEntity {
    private int expDrop; 
    
    public Monster(String name, int health, int armor, int attack, int level, int expDrop) {
        super(name, health, armor, attack, level);
        this.expDrop = expDrop; 
    }

    @Override
    public void onDeath(Room currentRoom) {
        currentRoom.addItemToRoom(new Consumeable("dfghjkl",1,1,1,1));
    }
    
}
