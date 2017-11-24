/*
 * The Shoopkeper class, used by the games shopkeeper, for the player to buy and sell tems
 */
package Domain;

import Arq.IItem;
import Arq.IShopkeeper;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Victor Gram
 */
class Shopkeeper extends NPC implements IShopkeeper {

    private Map<String, IItem> itemstoSell = new HashMap<>();

    Shopkeeper(String name, int health, int armor, int attack, int level, int expDrop, String talk) {
        super(name, health, armor, attack, level, expDrop, 3, talk);

    }

    public boolean buy(Item item, int amount, Player player) {
        System.out.println("gold " + player.getGold());
        if ((item.getSellValue() * amount) * 2 > player.getGold()) { //Checks if the player has enough gold to purchase the item
            return false;
        }
        if (((Inventory) player.getItemInventory()).addItem(item, amount)) { //Adds the item(s) to the players inventory
            player.removeGold(item.getSellValue() * amount * 2); //Removes gold from player
            return true;

        }
        return false;

    }

    public boolean sell(Item item, int amount, Player player) {
        if (((Inventory) player.getItemInventory()).removeItem(item, amount)) {
            player.addGold(item.getSellValue() * amount);
            System.out.println("gold " + player.getGold());
            return true;
        }
        return false;
    }

    @Override
    public Map<String, IItem> getItemsToSell() {
        return itemstoSell;
    }

    void setItemsToSell(Map<String, IItem> itemstoSell) {
        this.itemstoSell = itemstoSell;
    }

}
