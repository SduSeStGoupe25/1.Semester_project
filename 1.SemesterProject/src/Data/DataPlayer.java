package Data;

import Acq.IInventory;
import Acq.IPlayer;
import Acq.IQuest;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * DataPlayer class
 */
class DataPlayer extends DataCharacterEntity implements IPlayer {

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

    DataPlayer(DataInventory itemInventory, DataInventory equipableInventory, int gold, int exp, LinkedHashMap<Integer, IQuest> mainQuest, HashMap<String, IQuest> sideQuest, int questsCompleted, int hunger, int maxHunger, int expToLevelUp, int scoreValue, String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id) {
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

    @Override
    public int getAttackValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getArmorValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
