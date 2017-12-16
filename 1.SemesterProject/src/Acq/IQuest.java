package Acq;

import java.util.HashMap;

/**
 *
 * Interface Quest
 */
public interface IQuest {

    /**
     * Called to get the name of the quest
     *
     * @return the name of the quest
     */
    String getName();

    /**
     * Called to get the quest description
     *
     * @return a String representing the description of the quest
     */
    String getDescription();

    /**
     * Called to get the amount of gold the quest gives when completed
     *
     * @return the gold reword of the quest
     */
    int getGold();

    /**
     * Called to get the amount of exp the quest gives when completed
     *
     * @return the exp reword of the quest
     */
    int getExp();

    /**
     * Called to get the item necessary to complete the quest
     *
     * @return the list of item
     */
    HashMap<String, Integer> getItems();

    /**
     * Called to get the name of the quest giver
     *
     * @return the name of characterEntity
     */
    String getGiver();
}
