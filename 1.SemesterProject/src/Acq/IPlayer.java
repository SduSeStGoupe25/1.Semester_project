package Acq;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * Interface player
 *
 */
public interface IPlayer extends ICharacterEntity {

    /**
     * Called to get the itemInventory on the player
     *
     * @return itemInventory from player
     */
    IInventory getItemInventory();

    /**
     * Called to get the equipableInventory on the player
     *
     * @return equipableInventory from player
     */
    IInventory getEquipableInventory();

    /**
     * Called to get the amount of gold the player has
     *
     * @return player gold
     */
    int getGold();

    /**
     * Called to get the player exp
     *
     * @return the exp
     */
    int getExp();

    /**
     * Called to get a LinkedHashMap with main quests
     *
     * @return a map with main quests
     */
    LinkedHashMap<Integer, IQuest> getMainQuest();

    /**
     * Called to get a HashMap with side quests
     *
     * @return a map with side quests
     */
    HashMap<String, IQuest> getSideQuest();

    /**
     * Called to get the amount of main quest that are completed
     *
     * @return the amount of completed main quests
     */
    int getQuestsCompleted();

    /**
     * Called to get the player hunger
     *
     * @return the player hunger
     */
    int getHunger();

    /**
     * Called to get the max hunger
     *
     * @return the max hunger
     */
    int getMaxHunger();

    /**
     * Called to get the exp that are needed to level up
     *
     * @return the exp needed to level up
     */
    int getExpToLevelUp();

    /**
     * Called to get the score value
     *
     * @return the score value
     */
    int getScoreValue();

    /**
     * Called to complete a quest
     *
     * @param room the room to complete the quest from
     */
    void getCompleteQuest(String room);

    /**
     * Called to get the attack value, player attack + equipment attack
     *
     * @return the attack value
     */
    int getAttackValue();

    /**
     * CAlled to get the armor value, player armor + equipment armor
     *
     * @return the armor value
     */
    int getArmorValue();

}
