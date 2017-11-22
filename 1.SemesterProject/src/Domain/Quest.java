/**
 * Class creating and controlling quests in the game.
 */
package Domain;

import Arq.IItem;
import Arq.IQuest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor Gram
 */
class Quest implements IQuest{

    private String name;
    private String description; 
    private int gold; //how much gold does the player reieve upon completion
    private int exp; //how much experience the player recieves upon completion
    private ArrayList<IItem> items; //ArrayList to store questitems
    private String giver; //The NPC from whom the quest is recieved
//    private boolean isMainQuest; //boolean to seperate the main quests and side quests

    Quest(String name, String description, int gold, int exp, ArrayList<IItem> items, String giver) { 
        this.name = name;
        this.description = description;
        this.gold = gold;
        this.exp = exp;
        this.items = items;
        this.giver = giver;
//        this.isMainQuest = isMainQuest;   
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
    public List<IItem> getItems() {
        return items;
    }

    void setItems(ArrayList<IItem> items) {
        this.items = items;
    }

    @Override
    public String getGiver() {
        return giver;
    }
    
    void setGiver(String giver){
        this.giver = giver;
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
        for(IItem i : items){
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
