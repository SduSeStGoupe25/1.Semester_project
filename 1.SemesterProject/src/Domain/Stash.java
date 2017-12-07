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
import Domain.Quest;

/**
 *
 * @author irus
 */
public class Stash {
private static Map<String, Item>itemMap=new HashMap<>();

//public static Item getItem(String name) {
//    Item cashedItem = itemMap.get(name);
//    return (Item) cashedItem.clone();
//    
//}
//public static void loadCache() {
//    itemMap.put("rock", new Item("Rock", 0, ItemType.WEAPON, 1, 1));
//    itemMap.put("hp", new Item("Hp", 1, ItemType.CONSUMEABLE, 25, 1));
//    itemMap.put("chest", new Item("Chest", 50, ItemType.ARMOR, 1, 1));
//    itemMap.put("sword", new Item("Sword", 50, ItemType.WEAPON, 1 ,1));
//}
//public static Map<String, Item> getItemMap(){
//    return itemMap;
//}

//public static LinkedHashMap<Integer, Quest> getQuestMap(){
//    LinkedHashMap<Integer, Quest> questMap = new LinkedHashMap<>();
//    int questCount = 0;
//    ArrayList<Item> questItem = new ArrayList<>();
//    questItem.add(itemMap.get("rock"));
//    questMap.put(questCount++, new Quest("Start 1", "To survive in this kingdom you must be able to fight. "
//                                            + "Practice by killing some sheeps, they are found on the farm." +
//                                            "You may find a weapon, for example, a rock.", 10, 10, 
//                                            new ArrayList<>(questItem), "Merlin"));
//    
//    questItem.clear();
//    questItem.add(itemMap.get("sword"));
//    questMap.put(questCount++, new Quest("Start 2", "The start part 2", 10, 10, new ArrayList<>(questItem), "Shopkeeper"));
//    
//    questItem.clear();
//    questMap.put(questCount++, new Quest("The final encounter", "You've made it all the way to Excalibur! "
//                                            + "now it's time to slay that dragon, "
//                                            + "and pull Excalibur from the stone!", 25, 50, new ArrayList<>(questItem), "Merlin"));
//    
//    questItem.clear();
//    questMap.put(questCount++, new Quest("Comming soon!", "More comming soon", 0, 0, new ArrayList<>(questItem), "No one yet"));
//    return questMap;
//}
}
