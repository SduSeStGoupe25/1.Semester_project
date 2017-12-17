package Domain;

import Acq.ICharacterEntity;
import Acq.IInventory;
import Acq.IItem;
import Acq.IPlayer;
import Acq.IQuest;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * This class defines a Player
 */
class Player extends CharacterEntity implements IPlayer {

    /**
     * The inventory with items
     */
    private Inventory itemInventory;

    /**
     * The inventory with items that are equipped
     */
    private Inventory equipableInventory;

    /**
     * The amount of gold the player has
     */
    private int gold;

    /**
     * The amount of exp the player has. This resets the level up
     */
    private int exp;

    /**
     * The mainQuests
     */
    private LinkedHashMap<Integer, IQuest> mainQuest;

    /**
     * The sideQuests
     */
    private HashMap<String, IQuest> sideQuest;

    /**
     * The amount of main quest that are completed
     */
    private int questsCompleted;

    /**
     * The players hunger
     */
    private int hunger;

    /**
     * The max amount of hunger the player can get
     */
    private int maxHunger;

    /**
     * The exp need to level up
     */
    private int expToLevelUp;

    /**
     * The score the player has earned
     */
    private int scoreValue;

    /**
     * if set to 1 the game is lost
     */
    private int completedGame = 0;

    /**
     * Constructor
     *
     * @param name {@link CharacterEntity#name}
     * @param health {@link CharacterEntity#health}
     * @param armor {@link CharacterEntity#armor}
     * @param attack {@link CharacterEntity#attack}
     * @param level {@link CharacterEntity#level}
     * @param gold {@link #gold}
     * @param exp {@link #exp}
     */
    Player(String name, int health, int armor, int attack, int level, int gold, int exp) {
        super(name, health, armor, attack, level, 2);
        this.gold = gold;
        this.exp = exp;
        mainQuest = null;
        itemInventory = new Inventory(20);
        equipableInventory = new Inventory(3);
        questsCompleted = 0;
        this.hunger = 100;
        maxHunger = 100;
        this.expToLevelUp = 10;
        this.scoreValue = 0;
    }

    /**
     * Called to get hunger of maxHunger
     *
     * @return hungerPercent
     */
    double getHungerPercent() {
        return maxHunger / hunger;
    }

    /**
     * Called to add hunger to the players hunger
     *
     * @param hunger the amount to add
     */
    void addHunger(int hunger) {
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
    @Override
    public int getAttackValue() {
        int attackValue = getAttack();
        for (IItem item : equipableInventory.getInventory()) { //Goes through our equipableInventory, to see if we have a weapon equipped
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
    @Override
    public int getArmorValue() {
        int armorValue = getAttack();
        for (IItem item : equipableInventory.getInventory()) { //Goes through our equipableInventory and checks if we have any armor equipped
            if (item instanceof Armor) {
                armorValue += ((Armor) item).getArmorValue(); //If we have any armor equipped, it gets added to our armorValue
                break;
            }
        }
        return armorValue;
    }

    /**
     * Called to add gold to the players gold
     *
     * @param amount the amount to add
     */
    void addGold(int amount) {
        gold += amount;
    }

    /**
     * Called to remove gold from the player
     *
     * @param amount hte amount to remove
     */
    void removeGold(int amount) {
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
    boolean equip(Item item) {
        if (itemInventory.removeItem(((Item) item), 1)) { //Removes the item that we want to equip from our inventory
            if (item instanceof Weapon) { //Checks if the item is a weapon
                for (IItem i : equipableInventory.getInventory()) { //Searches through our equipableInventory
                    if (i instanceof Weapon) {  //If we find a weapon that is currently equipped, it does:
                        itemInventory.addItem((Item) i, 1);                //Adds the equipped item to our itemInventory
                        equipableInventory.removeItem((Item) i, 1);        //Removes the equipped item from our equipableInventory
                        equipableInventory.addItem(item, 1);        //Adds the new item to our equipableInventory
                        return true;
                    }
                }
                equipableInventory.addItem(item, 1); //If we didn't have a weapon equipped beforehand, it adds the new item to our equipableInventory
                return true;
            } else if (item instanceof Armor) { //Checks if the item is a piece of armor
                for (IItem i : equipableInventory.getInventory()) { //Searches through our equipableInventory
                    if (i instanceof Armor) {   //If we find a piece of armor that is currently equipped, then do:
                        ((Inventory) itemInventory).addItem((Item) i, 1);                //Adds the already equipped armor to our itemInventory
                        equipableInventory.removeItem((Item) i, 1);        //Removes the equipped item from our equipableInventory
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
    boolean restoreHp(IItem item) {
        if (!(getHealth() >= getMaxHealth()) || !(this.hunger >= 100)) { //If player is already at max health, returns false
            if (item instanceof Consumeable) { //Checks if the item is a consumeable (only type of item that can restore health)
                if (this.itemInventory.removeItem(item, 1)) { //Removes the item from our inventory
                    if (getHealth() + ((Consumeable) item).getUseValue() >= getMaxHealth()) { //If the health restored + our current health is greater than our max health, 
                        // sets our health = max health. This is to prevent gaining more health.
                        setHealth(getMaxHealth());
                        addHunger(((Consumeable) item).getHungerValue());
                        return true;
                    } else {
                        setHealth(((Consumeable) item).getUseValue() + getHealth()); //Adds the health to the player
                        addHunger(((Consumeable) item).getHungerValue());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onDeath() {
        if (getHealth() <= 0) {
            completedGame = 1; //Sets completedGame to 1, which is "Game lost"
        }
    }

    /**
     * Called to get the current main quest
     *
     * @return the current main quest
     */
    IQuest getCurrentMainQuest() {
        return mainQuest.get(questsCompleted);
    }

    /**
     * This method checks whether or not we can complete a quest
     *
     * @param room It takes a room as parameter, to check if the required quest
     * giver is in the room
     * @return Returns true if quest is complete, otherwise returns false
     */
    boolean checkQuest(String room) {
        int itemCount = 0;
        for (ICharacterEntity ce : DomainGame.getInstance().getRoomMap().get(room).getCharactersInRoom()) { //Searches through all the CharacterEntities in the room
            if (ce.getName().equals(getCurrentMainQuest().getGiver())) {    //If a CharacterEntity equals the quest giver
                for (String s : getCurrentMainQuest().getItems().keySet()) {        //then we look through the items required to complete the quest,
                    for (IItem i : getItemInventory().getInventory()) {      //and then we look through our itemInventory
                        if (i.getName().equalsIgnoreCase(s) && i.getCount() >= getCurrentMainQuest().getItems().get(s)) { //If the required quest item is in our inventory, and we have the correct amount (or more),
                            itemCount++; //then it adds to itemCount
                            break;
                        }
                    }
                }
                if (itemCount == getCurrentMainQuest().getItems().size()) { //If itemCount is equal to the required amount to complete the quest,
                    addGold(getCurrentMainQuest().getGold());               //then we add Gold to the player (reward for completing quest)
                    addExp(getCurrentMainQuest().getExp());
                    for (String s : getCurrentMainQuest().getItems().keySet()) {    //We then look through the quest items, and remove them from players itemInventory
                        ((Inventory) getItemInventory()).removeItem(DomainData.getInstance().getItem(s), getCurrentMainQuest().getItems().get(s));
                    }
                    setQuestsCompleted(questsCompleted + 1);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void getCompleteQuest(String room) {
        checkQuest(room);
    }

    /**
     * Function called to check whether the player has enough experience to
     * level up, and the fuctionality for leveling up
     */
    void levelUp() {
        setLevel(getLevel() + 1);
        super.setStats();
        scoreValue += exp;
        if (expToLevelUp - exp != 0) {
            exp -= expToLevelUp;
        } else {
            exp = 0;
        }

        expToLevelUp += 5;
    }

    /**
     * Called to add exp to the players current exp
     *
     * @param exp the amount to add
     */
    void addExp(int exp) {
        this.exp += exp;
        if (this.exp >= expToLevelUp) {
            levelUp();
        }
    }

    /**
     * Called to get the amount of exp of exp to level up
     *
     * @return exp percent
     */
    double getExpPercent() {
        if (exp != 0) {
            return expToLevelUp / exp;
        } else {
            return 0;
        }
    }

    @Override
    public IInventory getItemInventory() {
        return itemInventory;
    }

    /**
     * Called to set the item inventory
     *
     * @param itemInventory the inventory to set to
     */
    void setItemInventory(IInventory itemInventory) {
        this.itemInventory = (Inventory) itemInventory;
    }

    @Override
    public IInventory getEquipableInventory() {
        return equipableInventory;
    }

    /**
     * Called to set the equipable inventory
     *
     * @param equipableInventory the inventory to set to
     */
    void setEquipableInventory(IInventory equipableInventory) {
        this.equipableInventory = (Inventory) equipableInventory;
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

    /**
     * Called to set the mainQuest
     *
     * @param mainQuest LinkedHashMap to set to
     */
    void setMainQuest(LinkedHashMap<Integer, IQuest> mainQuest) {
        this.mainQuest = mainQuest;
    }

    @Override
    public HashMap<String, IQuest> getSideQuest() {
        return sideQuest;
    }

    /**
     * Called to set the sideQuest
     *
     * @param sideQuest HashMao to set to
     */
    void setSideQuest(HashMap<String, IQuest> sideQuest) {
        this.sideQuest = sideQuest;
    }

    @Override
    public int getQuestsCompleted() {
        return questsCompleted;
    }

    /**
     * Called to set the quests completed
     *
     * @param questsCompleted the amount of quest completed
     */
    void setQuestsCompleted(int questsCompleted) {
        this.questsCompleted = questsCompleted;
    }

    @Override
    public int getHunger() {
        return hunger;
    }

    /**
     * Called to set the hunger of the player
     *
     * @param hunger the amount to set hunger to
     */
    void setHunger(int hunger) {
        this.hunger = hunger;
    }

    @Override
    public int getMaxHunger() {
        return maxHunger;
    }

    /**
     * Called to set the max hunger
     *
     * @param maxHunger the max hunger
     */
    void setMaxHunger(int maxHunger) {
        this.maxHunger = maxHunger;
    }

    @Override
    public int getExpToLevelUp() {
        return expToLevelUp;
    }

    /**
     * Called to set the expToLevelUp
     *
     * @param expToLevelUp amount of exp to level up
     */
    void setExpToLevelUp(int expToLevelUp) {
        this.expToLevelUp = expToLevelUp;
    }

    @Override
    public int getScoreValue() {
        return scoreValue;
    }

    /**
     * Called to set the score value
     *
     * @param scoreValue the value to set score value to
     */
    void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    /**
     * Called to add score to scoreValue
     *
     * @param expDrop the amount to add
     */
    void addScore(int expDrop) {
        scoreValue += expDrop;
    }
}
