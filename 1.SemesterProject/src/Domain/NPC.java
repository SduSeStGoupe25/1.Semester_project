/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

//import Domain.Game;
import Acq.INPC;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Victor Gram
 */
class NPC extends CharacterEntity implements INPC {

    private String talk;
    private int expDrop;
    private Map<String, Integer> itemMap = new HashMap<>();

    NPC(String name, int health, int armor, int attack, int level, int expDrop, String talk, Map<String, Integer> itemMap, boolean hostile, boolean despawning) {
        this(name, health, armor, attack, level, 1, expDrop, talk, itemMap, hostile, despawning);
    }

    NPC(String name, int health, int armor, int attack, int level, int expDrop, int id, String talk, Map<String, Integer> itemMap, boolean hostile, boolean despawning) {
        super(name, health, armor, attack, level, id, hostile, despawning);
        this.talk = talk;
        this.expDrop = expDrop;
        this.itemMap = itemMap;
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
        if (!itemMap.isEmpty()) {
            for (String name : itemMap.keySet()) {
                if ((int) (Math.random() * 100) <= itemMap.get(name)) {
                    DomainGame.getInstance().getCurrentIRoom().getItemList().add(DomainData.getInstance().getItem(name));
                }
            }
        }
        ((Player)DomainGame.getInstance().getPlayer()).addScore(this.expDrop);
    }

    @Override
    public Map<String, Integer> getItemDropMap() {
        return itemMap;
    }

    void setItemMap(Map<String, Integer> itemMap) {
        this.itemMap = itemMap;
    }
}
