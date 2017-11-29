/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import Domain.DomainQuest;

/**
 *
 * @author irus
 */
public class Stash {
private static Map<String, DomainItem>itemMap=new HashMap<>();

//public static DomainItem getItem(String name) {
//    DomainItem cashedItem = itemMap.get(name);
//    return (DomainItem) cashedItem.clone();
//    
//}
//public static void loadCache() {
//    itemMap.put("rock", new DomainItem("Rock", 0, ItemType.WEAPON, 1, 1));
//    itemMap.put("hp", new DomainItem("Hp", 1, ItemType.CONSUMEABLE, 25, 1));
//    itemMap.put("chest", new DomainItem("Chest", 50, ItemType.ARMOR, 1, 1));
//    itemMap.put("sword", new DomainItem("Sword", 50, ItemType.WEAPON, 1 ,1));
//}
//public static Map<String, DomainItem> getItemMap(){
//    return itemMap;
//}

public static LinkedHashMap<Integer, DomainQuest> getQuestMap(){
    LinkedHashMap<Integer, DomainQuest> questMap = new LinkedHashMap<>();
    int questCount = 0;
    ArrayList<DomainItem> questItem = new ArrayList<>();
    questItem.add(itemMap.get("rock"));
    questMap.put(questCount++, new DomainQuest("Start 1", "To survive in this kingdom you must be able to fight. "
                                            + "Practice by killing some sheeps, they are found on the farm." +
                                            "You may find a weapon, for example, a rock.", 10, 10, 
                                            new ArrayList<>(questItem), "Merlin"));
    
    questItem.clear();
    questItem.add(itemMap.get("sword"));
    questMap.put(questCount++, new DomainQuest("Start 2", "The start part 2", 10, 10, new ArrayList<>(questItem), "Shopkeeper"));
    
    questItem.clear();
    questMap.put(questCount++, new DomainQuest("The final encounter", "You've made it all the way to Excalibur! "
                                            + "now it's time to slay that dragon, "
                                            + "and pull Excalibur from the stone!", 25, 50, new ArrayList<>(questItem), "Merlin"));
    
    questItem.clear();
    questMap.put(questCount++, new DomainQuest("Comming soon!", "More comming soon", 0, 0, new ArrayList<>(questItem), "No one yet"));
    return questMap;
}
}
