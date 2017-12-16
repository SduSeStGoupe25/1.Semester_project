package Domain;

import Acq.IQuest;
import java.util.HashMap;

/**
 * Class creating and controlling quests in the game.
 */
class Quest implements IQuest {

    private String name; //Name of the quest
    private String description; //Description of the quest
    private int gold; //Gold reward for completing the quest
    private int exp; //Experience reward for completing the quest
    private HashMap<String, Integer> items; //HashMap containing required items to complete quest
    private String giver; //The NPC the quest has to be turned in to
    private String questDescription = ""; //Variable used to handle quest items in description, to avoid duplicate quest items in description

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
    
    /**
     * Method used to add quest items to quest description
     */
    private void addItemsToDescription() { //This method adds quest items from our HashMap to the quest description, so the player can see what items are required for completion
        String itemDescription = ""; //Empty string created to hold item names
        questDescription = description;
        if (items.keySet().size() == 1) {
            for (String item : items.keySet()) {
                itemDescription = "\n\nRequired items: " + items.get(item) + " " + item; //If there is any quest items, we add it to itemDescription
            }
        } else if (items.keySet().size() > 1) {
            int i = 0;
            itemDescription = "\n\nRequired items: "; //if there's more than one quest item, we add the "Required items" line, and then iterate through them
            for (String item : items.keySet()) {
                i++;
                if (i == items.keySet().size()) {
                    itemDescription += items.get(item) + " " + item + " "; //If the item is the last item, we don't want a comma at the end, so this if statement adds only a space
                    break;
                }
                itemDescription += items.get(item) + " " + item + ", "; //Every item gets added to itemDescription
            }
        }
        questDescription += itemDescription;
//        questDescription = description + itemDescription; //Finally it sets questDescription (which is initially empty) to be equals to the original description + the required items.
        //The extra String variable questDescription was needed to prevent duplicates of "Required items..." to be added to description
    }

    @Override
    public String getDescription() {
        addItemsToDescription(); //This line starts the method addItemsToDescription, and updates questDescription if there's any quest items
        return questDescription; //Returning questDescription, otherwise items duplicate in description when called more than once
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

    void setGiver(String giver) {
        this.giver = giver;
    }
}
