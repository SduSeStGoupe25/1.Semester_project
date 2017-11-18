/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Entity;

//import Domain.Game;
/**
 *
 * @author Victor Gram
 */
public class NPC extends CharacterEntity {

    private String talk;
    private int expDrop;

    public NPC(String name, int health, int armor, int attack, int level, int expDrop, String talk) {
        super(name, health, armor, attack, level, 1);
        this.talk = talk;
        this.expDrop = expDrop;
    }

    public NPC(String name, int health, int armor, int attack, int level, int expDrop, int id, String talk) {
        super(name, health, armor, attack, level, id);
        this.talk = talk;
        this.expDrop = expDrop;
    }

    public String getTalk() {
        return talk;
    }

    @Override
    public void onDeath() {
        //Game.getInstance().getPlayer().addExp(expDrop);

    }
}
