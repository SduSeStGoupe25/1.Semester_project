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
public class Monster extends Entity {
    private int expDrop; 
    
    public Monster(String name, int health, int armor, int attack, int level, int expDrop) {
        super(name, health, armor, attack, level);
        this.expDrop = expDrop; 
    }
    
    public void onDeath() { 
          
    }
}
