/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.IMoveableNPC;
import java.util.Set;

/**
 *
 * @author madsd
 */
class DataMoveableNPC extends DataNPC implements IMoveableNPC {

    private Set<String> allowedRooms;

    DataMoveableNPC(String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id, String talk, int expDrop, Set<String> allowedRooms) {
        super(name, health, armor, level, maxHealth, baseHealth, baseAttack, attack, id, talk, expDrop);
        this.allowedRooms = allowedRooms;
    }
    
    @Override
    public Set<String> getAllowedRooms() {
        return allowedRooms;
    }

}
