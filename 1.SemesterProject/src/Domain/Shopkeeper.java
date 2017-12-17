package Domain;

import Acq.IShopkeeper;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * The Shoopkeper class, used by the games shopkeeper, for the player to buy and sell tems
 */
class Shopkeeper extends NPC implements IShopkeeper {

    /**
     * The names of the items to sell
     */
    private Set<String> itemstoSell = new HashSet<>();

    /**
     * Constructor
     *
     * @param name {@link CharacterEntity#name}
     * @param health {@link CharacterEntity#health}
     * @param armor {@link CharacterEntity#armor}
     * @param attack {@link CharacterEntity#attack}
     * @param level {@link CharacterEntity#level}
     * @param expDrop {@link #expDrop}
     * @param talk {@link #talk}
     * @param itemMap {@link #itemMap}
     * @param hostile {@link CharacterEntity#hostile}
     * @param despawning {@link CharacterEntity#despawning}
     */
    Shopkeeper(String name, int health, int armor, int attack, int level, int expDrop, String talk, Map<String, Integer> itemMap, boolean hostile, boolean despawning) {
        super(name, health, armor, attack, level, expDrop, 3, talk, itemMap, hostile, despawning);

    }

    /**
     * Called to buy a item
     *
     * @param item the item to buy
     * @param amount the amount to buy
     * @return true if the item was successful bought
     */
    boolean buy(Item item, int amount) {
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

    /**
     * Called to sell a item
     *
     * @param item the item to sell
     * @param amount the amount to sell
     * @return true if the item was successful sold
     */
    boolean sell(Item item, int amount) {
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

    /**
     * Called to set the items to sell
     *
     * @param itemstoSell the names of the items to sell
     */
    void setItemsToSell(Set<String> itemstoSell) {
        this.itemstoSell = itemstoSell;
    }
}
