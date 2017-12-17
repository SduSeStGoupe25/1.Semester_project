package Acq;

import java.util.LinkedHashMap;

/**
 *
 * Interface for DomainGame, this specifies methods the UI can call
 */
public interface IGame extends IDomainGame {

    /**
     * Called to move the player from one room to another
     *
     * @param direction the name of the room to move to
     * @return true if the player was moved, else if the player stays in the
     * current room
     */
    boolean movePlayer(String direction);

    /**
     * Called to perform a action in combat
     *
     * @param action is that to do, 0 = lightAttack, 1 = heavyAttack, 2 = flee,
     * 3 is use when combat are started to get start information
     * @return information about the turn
     */
    ICombatResponse getCombatResponse(int action);

    /**
     * Called to get combat
     *
     * @return combat
     */
    ICombat getCombat();

    /**
     * Called to get a LinkedHashMap with all mainQuests
     *
     * @return a LinkedHashMap with the main quests
     */
    LinkedHashMap<Integer, IQuest> getQuestList();

    /**
     * Called to get the description on the current active main quest
     *
     * @return the quest description
     */
    String getQuestDescription();

    /**
     * Called to get a exit in a room in a specific direction
     *
     * @param direction the direction the room at at
     * @return the room at the specified direction
     */
    IExit getExitCurrentRoom(String direction);

    /**
     * Called to see if in combat
     *
     * @return true if in combat, else false
     */
    boolean isInCombat();

    /**
     * Called to use a potion from the player inventory
     *
     * @return whether the potion was use or not
     */
    boolean usePotion();

    /**
     * Called to restore player hp using a specific item
     *
     * @param item the item to use to restore hp
     * @return true if hp was restored, else false
     */
    boolean restoreHpPlayer(IItem item);

    /**
     * Called to equip a item on the player
     *
     * @param item the item to equip
     * @return true if the item was equipped
     */
    boolean equipItemPlayer(IItem item);

    /**
     * Called to remove a item from the player
     *
     * @param item the item to remove
     * @param amount the amount to remove
     * @return true if ok, else false
     */
    boolean removeItemPlayer(IItem item, int amount);

    /**
     * Called to add a item to the player
     *
     * @param item the item to add
     * @param amount the amount to add
     * @return true if ok, else false
     */
    boolean addItemPlayer(IItem item, int amount);

    /**
     * Called to get the shopkeeper
     *
     * @return the shopkeeper
     */
    IShopkeeper getShopkeeper();

    /**
     * Called to buy a item from the shopkeeper
     *
     * @param item the item to buy
     * @param amount the amount to buy
     * @return true if ok, else false
     */
    boolean buy(IItem item, int amount);

    /**
     * Called to sell a item from the player
     *
     * @param item the item to sell
     * @param amount the amount to sell
     * @return true if ok, else false
     */
    boolean sell(IItem item, int amount);

    /**
     * Called to set the name of the player
     *
     * @param name the name of the player
     */
    void setPlayerName(String name);

}
