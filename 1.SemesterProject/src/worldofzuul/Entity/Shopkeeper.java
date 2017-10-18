/*
 * The Shoopkeper class, used by the games shopkeeper, for the player to buy and sell tems
 */
package worldofzuul.Entity;

import java.util.HashMap;
import java.util.Map;
import worldofzuul.Inventory.Item;

/**
 *
 * @author Victor Gram
 */
public class Shopkeeper extends NPC {
    private Map<String, Item> itemstoSell = new HashMap<>(); 
    
    public Shopkeeper(String name, int health, int armor, int attack, int level, int expDrop, String talk) {
        super(name, health, armor, attack, level, expDrop, talk);
        
    }
    
    public boolean buy (Item item, int amount) { 
        return false; 
        
    }
    
    public boolean sell (Item item, int amount) {
        return false;
    }
    
    
}
