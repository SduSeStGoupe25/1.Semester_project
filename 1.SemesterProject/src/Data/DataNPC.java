package Data;

import Acq.INPC;
import java.util.Map;

/**
 * DataNPC class
 */
class DataNPC extends DataCharacterEntity implements INPC {

    private String talk;
    private int expDrop;
    private Map<String, Integer> itemMap;
    
    DataNPC(String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id, String talk, int expDrop, boolean hostile, boolean despawning) {
        super(name, health, armor, level, maxHealth, baseHealth, baseAttack, attack, id, hostile, despawning);
        this.talk = talk;
        this.expDrop = expDrop;
    }

    @Override
    public String getTalk() {
        return talk;
    }

    @Override
    public int getExpDrop() {
        return expDrop;
    }

    @Override
    public Map<String, Integer> getItemDropMap() {
        return itemMap;
    }
}
