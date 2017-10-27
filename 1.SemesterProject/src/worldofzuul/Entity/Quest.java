/**
 * Class creating and controlling quests in the game.
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
    private int gold; //how much gold does the player reieve upon completion
    private int exp; //how much experience the player recieves upon completion
    private ArrayList<Item> items = new ArrayList<>(); //ArrayList to store questitems
    private String giver; //The NPC from whom the quest is recieved
//    private boolean isMainQuest; //boolean to seperate the main quests and side quests

    public Quest(String name, String description, int gold, int exp, ArrayList<Item> items, String giver) { 
        this.name = name;
        this.description = description;
        this.gold = gold;
        this.exp = exp;
        this.items = items;
        this.giver = giver;
//        this.isMainQuest = isMainQuest;   
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

    public String getGiver() {
        return giver;
    }
    
    
//      Maybe for use later
//    public boolean isIsMainQuest() {
//        return isMainQuest;
//    }
//
//    public void setIsMainQuest(boolean isMainQuest) {
//        this.isMainQuest = isMainQuest;
//    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Quest: " + name + " \n" + description + " \n" + giver + " needs these items: " );
        for(Item i : items){
            output.append(i.getName());
            output.append(" x");
            output.append(i.getCount());
        }
        output.append("\n Your reword is Gold: ");
        output.append(gold);
        output.append(" Exp: ");
        output.append(exp);
        return output.toString();
    }

}
