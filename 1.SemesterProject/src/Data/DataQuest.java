package Data;

import Acq.IQuest;
import java.util.HashMap;

/**
 * DataQuest class
 */
class DataQuest implements IQuest {

    private String name; //The name of the quest
    private String description; //The description of the quest
    private int gold; //how much gold the player recieves upon completion
    private int exp; //how much experience the player recieves upon completion
    private HashMap<String, Integer> items; //HashMap to store items required to complete the quest
    private String giver; //Name of the NPC who the players has to turn the quest in to

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
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
    public HashMap<String, Integer> getItems() {
        return items;
    }

    @Override
    public String getGiver() {
        return giver;
    }
}
