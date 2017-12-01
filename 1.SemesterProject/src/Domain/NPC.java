/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

//import Domain.Game;

import Arq.INPC;

/**
 *
 * @author Victor Gram
 */
class NPC extends CharacterEntity implements INPC{

    private String talk;
    private int expDrop;

    NPC(String name, int health, int armor, int attack, int level, int expDrop, String talk) {
        super(name, health, armor, attack, level, 1);
        this.talk = talk;
        this.expDrop = expDrop;
    }

    NPC(String name, int health, int armor, int attack, int level, int expDrop, int id, String talk) {
        super(name, health, armor, attack, level, id);
        this.talk = talk;
        this.expDrop = expDrop;
    }

    @Override
    public String getTalk() {
        return talk;
    }

    void setTalk(String talk) {
        this.talk = talk;
    }

    @Override
    public int getExpDrop() {
        return expDrop;
    }

    void setExpDrop(int expDrop) {
        this.expDrop = expDrop;
    }

    @Override
    public void onDeath() {
        
    }
}
