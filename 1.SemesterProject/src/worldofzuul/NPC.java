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
public class NPC extends Entity {
    private String talk; 
    private int expDrop;
    
    public NPC(String name, int health, int armor, int attack, int level, int expDrop, String talk) {
        super(name, health, armor, attack, level);
        this.talk = talk; 
        this.expDrop = expDrop; 
    }
    
    public String getTalk () { 
        return talk;
    }
    
    public void onDeath () {
        
    }
}
