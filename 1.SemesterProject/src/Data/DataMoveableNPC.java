package Data;

import Acq.IMoveableNPC;
import java.util.Set;

/**
 * DataMoveableNPc class
 */
class DataMoveableNPC extends DataNPC implements IMoveableNPC {

    private Set<String> allowedRooms;

    DataMoveableNPC(String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id, String talk, int expDrop, Set<String> allowedRooms, boolean hostile, boolean despawning) {
        super(name, health, armor, level, maxHealth, baseHealth, baseAttack, attack, id, talk, expDrop, hostile, despawning);
        this.allowedRooms = allowedRooms;
    }
    
    @Override
    public Set<String> getAllowedRooms() {
        return allowedRooms;
    }
}
