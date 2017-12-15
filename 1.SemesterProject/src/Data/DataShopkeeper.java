package Data;

import Acq.IShopkeeper;
import java.util.Set;

/**
 * DataShopkeeper class
 */
class DataShopkeeper extends DataNPC implements IShopkeeper {

    private Set<String> itemstoSell;

    DataShopkeeper(String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id, String talk, int expDrop, boolean hostile, boolean despawning) {
        super(name, health, armor, level, maxHealth, baseHealth, baseAttack, attack, id, talk, expDrop, hostile, despawning);
    }

    @Override
    public Set<String> getItemsToSell() {
        return itemstoSell;
    }

}
