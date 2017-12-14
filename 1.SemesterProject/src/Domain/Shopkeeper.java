package Domain;

import Acq.IShopkeeper;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * The Shoopkeper class, used by the games shopkeeper, for the player to buy and sell tems
 */
class Shopkeeper extends NPC implements IShopkeeper {

    private Set<String> itemstoSell = new HashSet<>();

    Shopkeeper(String name, int health, int armor, int attack, int level, int expDrop, String talk, Map<String, Integer> itemMap, boolean hostile, boolean despawning) {
        super(name, health, armor, attack, level, expDrop, 3, talk, itemMap, hostile, despawning);

    }

    public boolean buy(Item item, int amount) {
        Player player = (Player) DomainGame.getInstance().getPlayer();
        if ((item.getSellValue() * amount) * 2 > player.getGold()) { //Checks if the player has enough gold to purchase the item
            return false;
        }
        if (((Inventory) player.getItemInventory()).addItem(item, amount)) { //Adds the item(s) to the players inventory
            player.removeGold(item.getSellValue() * amount * 2); //Removes gold from player
            return true;

        }
        return false;

    }

    public boolean sell(Item item, int amount) {
        Player player = (Player) DomainGame.getInstance().getPlayer();
        if (((Inventory) player.getItemInventory()).removeItem(item, amount)) {
            player.addGold(item.getSellValue() * amount);
            return true;
        }
        return false;
    }

    @Override
    public Set<String> getItemsToSell() {
        return itemstoSell;
    }

    void setItemsToSell(Set<String> itemstoSell) {
        this.itemstoSell = itemstoSell;
    }

}
