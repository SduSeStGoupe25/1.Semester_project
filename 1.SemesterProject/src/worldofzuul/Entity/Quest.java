/**
 * Klasse til oprettelse og styring af quests
 */
package worldofzuul.Entity;

import java.util.ArrayList;
import worldofzuul.Inventory.Item;

/**
 *
 * @author Victor Gram
 */
public class Quest {

    private String name;
    private String description;
    private int questID;
    private int gold;
    private int exp;
    private ArrayList<Item> items = new ArrayList<>(); //ArrayList til opbevaring af questitems
    private NPC giver; //NPC'en der giver den pågældende quest
    private boolean isMainQuest;

    Quest(String name, String description, int questID, int gold, int exp, ArrayList<Item> items, NPC giver, boolean isMainQuest) {
        this.name = name;
        this.description = description;
        this.questID = questID;
        this.gold = gold;
        this.exp = exp;
        items = new ArrayList<>();
        this.giver = giver;
        this.isMainQuest = isMainQuest; //Ikke sikker på opsætnningen af boolean  
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuestID() {
        return questID;
    }

    public void setQuestID(int questID) {
        this.questID = questID;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public NPC getGiver() {
        return giver;
    }

    public void setGiver(NPC giver) {
        this.giver = giver;
    }

    public boolean isIsMainQuest() {
        return isMainQuest;
    }

    public void setIsMainQuest(boolean isMainQuest) {
        this.isMainQuest = isMainQuest;
    }

}
