/*
 * The Shoopkeper class, used by the games shopkeeper, for the player to buy and sell tems
 */
package WorldofArthur.Entity;

import java.util.HashMap;
import java.util.Map;
import WorldofArthur.Inventory.Item;

/**
 *
 * @author Victor Gram
 */
public class Shopkeeper extends NPC {

    private Map<String, Item> itemstoSell = new HashMap<>();

    public Shopkeeper(String name, int health, int armor, int attack, int level, int expDrop, String talk) {
        super(name, health, armor, attack, level, expDrop, talk);

    }

    public boolean buy(Item item, int amount, Player player) {
        System.out.println("gold " + player.getGold());
        if ((item.getSellValue()*amount) * 2 > player.getGold()) { //Checks if the player has enough gold to purchase the item
            return false;
        }
        if (player.getItemInventory().addItem(item, amount)){ //Adds the item(s) to the players inventory
            player.removeGold(item.getSellValue()* amount * 2); //Removes gold from player
            return true;

        }
        return false;
        
    }

    public boolean sell(Item item, int amount, Player player) {
        if (player.getItemInventory().removeItem(item, amount)){
            player.addGold(item.getSellValue()*amount);
            System.out.println("gold " + player.getGold());
            return true;
        }
        return false;
    }
}
