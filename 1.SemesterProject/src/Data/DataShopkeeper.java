/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Acq.IItem;
import Acq.IShopkeeper;
import java.util.Map;

/**
 *
 * @author madsd
 */
class DataShopkeeper extends DataNPC implements IShopkeeper {

    private Map<String, IItem> itemstoSell;

    public DataShopkeeper(String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id, String talk, int expDrop, boolean hostile, boolean despawning) {
        super(name, health, armor, level, maxHealth, baseHealth, baseAttack, attack, id, talk, expDrop, hostile, despawning);
    }

    @Override
    public Map<String, IItem> getItemsToSell() {
        return itemstoSell;
    }

}
