package Domain;

import Acq.IQuest;
import java.util.HashMap;

/**
 * Class creating and controlling quests in the game.
 */
class Quest implements IQuest {

    /**
     * Name of the quest
     */
    private String name;

    /**
     * Description of the quest
     */
    private String description;

    /**
     * Gold reward for completing the quest
     */
    private int gold;

    /**
     * Experience reward for completing the quest
     */
    private int exp;

    /**
     * HashMap containing required items to complete quest
     */
    private HashMap<String, Integer> items;

    /**
     * The NPC the quest has to be turned in to
     */
    private String giver;

    /**
     * Variable used to handle quest items in description, to avoid duplicate
     * quest items in description
     */
    private String questDescription = "";

    /**
     * Constructor
     *
     * @param name {@link #name}
     * @param description {@link #description}
     * @param gold {@link #gold}
     * @param exp {@link #exp}
     * @param items {@link #items}
     * @param giver {@link #giver}
     */
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
