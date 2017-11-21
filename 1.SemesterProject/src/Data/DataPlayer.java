/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.IInventory;
import Arq.IPlayer;
import Arq.IQuest;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author madsd
 */
public class DataPlayer extends DataCharactorEntity implements IPlayer {

    private DataInventory itemInventory;
    private DataInventory equipableInventory;
    private int gold;

    private int exp;
    private LinkedHashMap<Integer, DataQuest> mainQuest;
    private HashMap<String, DataQuest> sideQuest;
    private int questsCompleted;
    private int hunger;
    private int maxHunger;
    private int expToLevelUp;
    private int scoreValue;

    public DataPlayer(DataInventory itemInventory, DataInventory equipableInventory, int gold, int exp, LinkedHashMap<Integer, DataQuest> mainQuest, HashMap<String, DataQuest> sideQuest, int questsCompleted, int hunger, int maxHunger, int expToLevelUp, int scoreValue, String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id) {
        super(name, health, armor, level, maxHealth, baseHealth, baseAttack, attack, id);
        this.itemInventory = itemInventory;
        this.equipableInventory = equipableInventory;
        this.gold = gold;
        this.exp = exp;
        this.mainQuest = mainQuest;
        this.sideQuest = sideQuest;
        this.questsCompleted = questsCompleted;
        this.hunger = hunger;
        this.maxHunger = maxHunger;
        this.expToLevelUp = expToLevelUp;
        this.scoreValue = scoreValue;
    }


    @Override
    public int getScoreValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getExpToLevelUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHunger() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxHunger() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getHungerPercent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getAttackValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getArmorValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IInventory getItemInventory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IInventory getEquipableInventory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getGold() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addGold(int amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeGold(int amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IQuest getCurrentMainQuest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getExp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getExpPercent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
