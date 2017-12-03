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
import java.util.Map;

/**
 *
 * @author madsd
 */
public class DataPlayer extends DataCharactorEntity implements IPlayer {

    private IInventory itemInventory;
    private IInventory equipableInventory;
    private int gold;

    private int exp;
    private LinkedHashMap<Integer, IQuest> mainQuest;
    private HashMap<String, IQuest> sideQuest;
    private int questsCompleted;
    private int hunger;
    private int maxHunger;
    private int expToLevelUp;
    private int scoreValue;
    private int completedGame = 0;

    public DataPlayer(DataInventory itemInventory, DataInventory equipableInventory, int gold, int exp, LinkedHashMap<Integer, IQuest> mainQuest, HashMap<String, IQuest> sideQuest, int questsCompleted, int hunger, int maxHunger, int expToLevelUp, int scoreValue, String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id) {
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
    public IInventory getItemInventory() {
        return itemInventory;
    }

    @Override
    public IInventory getEquipableInventory() {
        return equipableInventory;
    }

    @Override
    public int getGold() {
        return gold;
    }

    @Override
    public int getExp() {
        return exp;
    }

    @Override
    public LinkedHashMap<Integer, IQuest> getMainQuest() {
        return mainQuest;
    }

    @Override
    public HashMap<String, IQuest> getSideQuest() {
        return sideQuest;
    }

    @Override
    public int getQuestsCompleted() {
        return questsCompleted;
    }

    @Override
    public int getHunger() {
        return hunger;
    }

    @Override
    public int getMaxHunger() {
        return maxHunger;
    }

    @Override
    public int getExpToLevelUp() {
        return expToLevelUp;
    }

    @Override
    public int getScoreValue() {
        return scoreValue;
    }

    @Override
    public void getCompleteQuest(String room) {
        
    }
}
