package Domain.Entity;

import Domain.Inventory.Inventory;
import Domain.Entity.CharacterEntity;
import java.util.HashMap;
import java.util.LinkedHashMap;
import Domain.Game;
import Domain.Inventory.Armor;
import Domain.Inventory.Consumeable;
import Domain.Inventory.Item;
import Domain.Inventory.Stash;
import Domain.Inventory.Weapon;
import Domain.Room;

/**
 *
 * @author Victor Gram
 */
/**
 * This class defines a Player
 */
public class Player extends CharacterEntity {

    private Inventory itemInventory;
    private Inventory equipableInventory;
    private int gold;
    private Room currentRoom;
    private int exp;
    private LinkedHashMap<Integer, Quest> mainQuest;
    private HashMap<String, Quest> sideQuest;
    private int questsCompleted;
    private Game game;
    private int hunger;
    private int expToLevelUp;
    private int scoreValue;

    /**
     * Player constructor
     */
    public Player(String name, int health, int armor, int attack, int level, int gold, Room currentRoom, int exp, Game game) {
        super(name, health, armor, attack, level);
        this.gold = gold;
        this.currentRoom = currentRoom;
        this.exp = exp;
        mainQuest = Stash.getQuestMap();
        itemInventory = new Inventory(20);
        equipableInventory = new Inventory(3);
        questsCompleted = 0;
        this.game = game;
        this.hunger = 100;
        this.expToLevelUp = 10;
        this.scoreValue = 0;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public int getExpToLevelUp() {
        return expToLevelUp;
    }

    public void setExpToLevelUp(int expToLevelup) {
        this.expToLevelUp = expToLevelup;
    }

    public int getHunger() {
        return hunger;
    }

    public void addHunger(int hunger) {
        if (this.hunger + hunger >= 100) {
            this.hunger = 100;
        } else if (this.hunger + hunger < 0) {
            this.changeHealth(hunger + this.hunger);
            this.hunger = 0;
        } else {
            this.hunger += hunger;
        }
    }

    /**
     * This method calculates the players attackValue. It searches for equipped
     * weapons, and adds them to the base attackValue
     *
     * @return Returns the attackValue as an integer
     */
    public int getAttackValue() {
        int attackValue = attack;
        for (Item item : equipableInventory.getInventory()) { //Goes through our equipableInventory, to see if we have a weapon equipped
            if (item instanceof Weapon) {
                attackValue += ((Weapon) item).getAttackValue(); //If we have a weapon equipped, it gets added to our attackValue
                break;
            }
        }
        return attackValue;
    }

    /**
     * This method calculates the players armorValue It searches for equipped
     * armor, and adds it to the base armorValue
     *
     * @return Returns the armorValue as an integer
     */
    public int getArmorValue() {
        int armorValue = armor;
        for (Item item : equipableInventory.getInventory()) { //Goes through our equipableInventory and checks if we have any armor equipped
            if (item instanceof Armor) {
                armorValue += ((Armor) item).getArmorValue(); //If we have any armor equipped, it gets added to our armorValue
                break;
            }
        }
        return armorValue;
    }

    public void setCurrentRoom(Room nextRoom) {
        currentRoom = nextRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Inventory getItemInventory() {
        return itemInventory;
    }

    public Inventory getEquipableInventory() {
        return equipableInventory;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void removeGold(int amount) {
        gold -= amount;
    }

    /**
     * This method is used when the player equips either a weapon or a piece of
     * armor
     *
     * @param item It takes an item as a parameter, and that item gets equipped
     * into equipableInventory, if it's a weapon or piece of armor
     * @return If the item was a weapon or a piece of armor, and it was
     * equipped, it returns true. If item did not get equipped, returns false
     */
    public boolean equip(Item item) {
        if (itemInventory.removeItem(item, 1)) { //Removes the item that we want to equip from our inventory
            if (item instanceof Weapon) { //Checks if the item is a weapon
                for (Item i : equipableInventory.getInventory()) { //Searches through our equipableInventory
                    if (i instanceof Weapon) {  //If we find a weapon that is currently equipped, it does:
                        itemInventory.addItem(i, 1);                //Adds the equipped item to our itemInventory
                        equipableInventory.removeItem(i, 1);        //Removes the equipped item from our equipableInventory
                        equipableInventory.addItem(item, 1);        //Adds the new item to our equipableInventory
                        return true;
                    }
                }
                equipableInventory.addItem(item, 1); //If we didn't have a weapon equipped beforehand, it adds the new item to our equipableInventory
                return true;
            } else if (item instanceof Armor) { //Checks if the item is a piece of armor
                for (Item i : equipableInventory.getInventory()) { //Searches through our equipableInventory
                    if (i instanceof Armor) {   //If we find a piece of armor that is currently equipped, then do:
                        itemInventory.addItem(i, 1);                //Adds the already equipped armor to our itemInventory
                        equipableInventory.removeItem(i, 1);        //Removes the equipped item from our equipableInventory
                        equipableInventory.addItem(item, 1);        //Adds the new item to our equipableInventory
                        return true;
                    }
                }
                equipableInventory.addItem(item, 1); //If we didn't have any armor equipped, it adds the new armor to our equipableInventory
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * This method is used to restore Player health points
     *
     * @param item The item used to restore Health
     * @return Returns true if health restoring was successful, otherwise
     * returns false
     */
    public boolean restoreHp(Item item) {
        if (!(this.health >= this.maxHealth) || !(this.hunger >= 100)) { //If player is already at max health, returns false
            if (item instanceof Consumeable) { //Checks if the item is a consumeable (only type of item that can restore health)
                if (this.itemInventory.removeItem(item, 1)) { //Removes the item from our inventory
                    if (this.health + ((Consumeable) item).getUseValue() >= this.maxHealth) { //If the health restored + our current health is greater than our max health, 
                        // sets our health = max health. This is to prevent gaining more health.
                        this.health = this.maxHealth;
                        addHunger(((Consumeable) item).getHungerValue());
                        return true;
                    } else {
                        this.health += ((Consumeable) item).getUseValue(); //Adds the health to the player
                        addHunger(((Consumeable) item).getHungerValue());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onDeath(Room currentRoom) {
        game.setFinished(true);
    }

    public Quest getCurrentMainQuest() {
        return mainQuest.get(questsCompleted);
    }

    public void setQuestsCompleted() {
        questsCompleted++;
    }

    /**
     * This method checks whether or not we can complete a quest
     *
     * @param room It takes a room as parameter, to check if the required quest
     * giver is in the room
     * @return Returns true if quest is complete, otherwise returns false
     */
    public boolean checkQuest(Room room) {
        int itemCount = 0;
        for (CharacterEntity ce : room.getCharactersInRoom()) { //Searches through all the CharacterEntities in the room
            if (ce.getName().equals(getCurrentMainQuest().getGiver())) {    //If a CharacterEntity equals the quest giver
                for (Item item : getCurrentMainQuest().getItems()) {        //then we look through the items required to complete the quest,
                    for (Item i : getItemInventory().getInventory()) {      //and then we look through our itemInventory
                        if (i.getName().equals(item.getName()) && i.getCount() >= item.getCount()) { //If the required quest item is in our inventory, and we have the correct amount (or more),
                            itemCount++; //then it adds to itemCount
                            break;
                        }
                    }
                }
                if (itemCount == getCurrentMainQuest().getItems().size()) { //If itemCount is equal to the required amount to complete the quest,
                    addGold(getCurrentMainQuest().getGold());               //then we add Gold to the player (reward for completing quest)
                    for (Item item : getCurrentMainQuest().getItems()) {    //We then look through the quest items, and remove them from players itemInventory
                        getItemInventory().removeItem(item, item.getCount());
                    }
                    setQuestsCompleted();
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    
    public void levelUp() { //Function called to chech wether the player has enough experience to level up, and the fuctionality for leveling up
        level++;
        super.setStats();
        scoreValue += exp;
        exp = 0;
        expToLevelUp += 5;
    }

    void addExp(int exp) {
        this.exp += exp;
        if (exp >= expToLevelUp) {
            levelUp();
            

        }
    }
}
