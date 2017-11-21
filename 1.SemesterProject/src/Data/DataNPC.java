/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.INPC;

/**
 *
 * @author madsd
 */
public class DataNPC extends DataCharactorEntity implements INPC {

    private String talk;
    private int expDrop;

    public DataNPC(String talk, int expDrop, String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id) {
        super(name, health, armor, level, maxHealth, baseHealth, baseAttack, attack, id);
        this.talk = talk;
        this.expDrop = expDrop;
    }

    @Override
    public String getTalk() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
