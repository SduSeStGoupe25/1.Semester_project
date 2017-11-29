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
class DomainShopkeeper extends DomainNPC implements IShopkeeper {

    private Map<String, IItem> itemstoSell = new HashMap<>();

    public DomainShopkeeper() {
        
    }

    
    public DomainShopkeeper(String name, int health, int armor, int attack, int level, int expDrop, String talk) {
        super(name, health, armor, attack, level, expDrop, 3, talk);

    }

    public boolean buy(DomainItem item, int amount, DomainPlayer player) {
        System.out.println("gold " + player.getGold());
        if ((item.getSellValue() * amount) * 2 > player.getGold()) { //Checks if the player has enough gold to purchase the item
            return false;
        }
        if (((DomainInventory) player.getItemInventory()).addItem(item, amount)) { //Adds the item(s) to the players inventory
            player.removeGold(item.getSellValue() * amount * 2); //Removes gold from player
            return true;

        }
        return false;

    }

    public boolean sell(DomainItem item, int amount, DomainPlayer player) {
        if (((DomainInventory) player.getItemInventory()).removeItem(item, amount)) {
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

    public void setItemsToSell(Map<String, IItem> itemstoSell) {
        this.itemstoSell = itemstoSell;
    }

}
