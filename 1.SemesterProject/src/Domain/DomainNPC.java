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
class DomainNPC extends DomainCharacterEntity implements INPC{

    private String talk;
    private int expDrop;

    public DomainNPC(){}
    public DomainNPC(String name, int health, int armor, int attack, int level, int expDrop, String talk) {
        super(name, health, armor, attack, level, 1);
        this.talk = talk;
        this.expDrop = expDrop;
    }

    public DomainNPC(String name, int health, int armor, int attack, int level, int expDrop, int id, String talk) {
        super(name, health, armor, attack, level, id);
        this.talk = talk;
        this.expDrop = expDrop;
    }

    @Override
    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    @Override
    public int getExpDrop() {
        return expDrop;
    }

    public void setExpDrop(int expDrop) {
        this.expDrop = expDrop;
    }

    @Override
    public void onDeath() {
        //Game.getInstance().getPlayer().addExp(expDrop);

    }
}
