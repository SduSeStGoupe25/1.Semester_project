package Domain;

import Acq.IQuest;
import java.util.HashMap;

/**
 * Class creating and controlling quests in the game.
 */
class Quest implements IQuest{

    private String name;
    private String description; 
    private int gold; //how much gold does the player reieve upon completion
    private int exp; //how much experience the player recieves upon completion
    private HashMap<String, Integer> items; //HashMap to hold items
    private String giver; //The NPC from whom the quest is recieved

    Quest(String name, String description, int gold, int exp, HashMap<String, Integer> items, String giver) { 
        this.name = name;
        this.description = description;
        this.gold = gold;
        this.exp = exp;
        this.items = items;
        this.giver = giver;
    }

    @Override
    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getGold() {
        return gold;
    }

    void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public int getExp() {
        return exp;
    }

    void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public HashMap<String, Integer> getItems() {
        return items;
    }

    void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }

    @Override
    public String getGiver() {
        return giver;
    }
    
    void setGiver(String giver){
        this.giver = giver;
    }
}
