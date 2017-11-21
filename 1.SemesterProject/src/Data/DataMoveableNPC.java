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
public class DataMoveableNPC extends DataCharactorEntity implements IMoveableNPC {

    private Set<String> allowedRooms;

    public DataMoveableNPC(Set<String> allowedRooms, String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id) {
        super(name, health, armor, level, maxHealth, baseHealth, baseAttack, attack, id);
        this.allowedRooms = allowedRooms;
    }

    @Override
    public String getTalk() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
