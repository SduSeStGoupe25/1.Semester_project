/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.IItem;
import Arq.IShopkeeper;
import java.util.Map;

/**
 *
 * @author madsd
 */
public class DataShopkeeper extends DataCharactorEntity implements IShopkeeper {

    private Map<String, DataItem> itemstoSell;

    public DataShopkeeper(Map<String, DataItem> itemstoSell, String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id) {
        super(name, health, armor, level, maxHealth, baseHealth, baseAttack, attack, id);
        this.itemstoSell = itemstoSell;
    }

    
    
    @Override
    public Map<String, IItem> getItemsToSell() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
