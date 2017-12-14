package Data;

import Acq.IQuest;
import java.util.HashMap;

/**
 * DataQuest class
 */
class DataQuest implements IQuest {

    private String name;
    private String description;
    private int gold; //how much gold does the player reieve upon completion
    private int exp; //how much experience the player recieves upon completion
    private HashMap<String, Integer> items; //ArrayList to store questitems
    private String giver;

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
