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
class DataNPC extends DataCharactorEntity implements INPC {

    private String talk;
    private int expDrop;

    DataNPC(String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id, String talk, int expDrop) {
        super(name, health, armor, level, maxHealth, baseHealth, baseAttack, attack, id);
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

}
