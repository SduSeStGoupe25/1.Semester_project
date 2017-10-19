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
    
    public boolean buy (Item item, int amount, Player player) { 
        if (item.getItemValue() > player.getGold()){ //Checks if the player has enough gold to purchase the item
            return false;
        }
        player.removeGold(item.getItemValue()); //Removes gold from player
        player.getItemInventory().addItem(item, amount); //Adds the item(s) to the players inventory
        return true; 
    }
    
    public boolean sell (Item item, int amount, Player player) {
        return false;
    }
}
