/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.HashMap;

/**
 *
 * @author Victor Gram
 */

public class Player extends Entity implements Moveable {
    private Inventory itemInventory; 
    private Inventory equipableInventory; 
    private QuestHandler questHandler;
    private int gold; 
    private Room currentRoom; 
    private int exp; 
    private HashMap<Integer, String> questLog; 
    
    public Player(String name, int health, int armor, int attack, int level, int gold, Room currentRoom, int exp) {
        super(name, health, armor, attack, level);
        this.gold = gold; 
        this.currentRoom = currentRoom;
        this.exp = exp;
        questLog = new HashMap<>(); 
        itemInventory = new Inventory(); 
        equipableInventory = new Inventory();
        questHandler = new QuestHandler(); 
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}