/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import worldofzuul.Entity.Quest;

/**
 *
 * @author irus
 */
public class Stash {
private static Map<String, Item>itemMap=new HashMap<>();

public static Item getItem(String name) {
    Item cashedItem = itemMap.get(name);
    return (Item) cashedItem.clone();
    
}
public static void loadCache() {
    itemMap.put("rock", new Item("Rock", 0, ItemType.WEAPON, 1, 1));
    itemMap.put("hp", new Item("Hp", 1, ItemType.CONSUMEABLE, 1, 1));
    itemMap.put("chest", new Item("Chest", 50, ItemType.ARMOR, 1, 1));
    itemMap.put("sword", new Item("Sword", 50, ItemType.WEAPON, 1 ,1));
}
public static Map<String, Item> getItemMap(){
    return itemMap;
}

public static LinkedHashMap<Integer, Quest> getQuestMap(){
    LinkedHashMap<Integer, Quest> questMap = new LinkedHashMap<>();
    ArrayList<Item> questItem = new ArrayList<>();
    questItem.add(itemMap.get("rock"));
    questMap.put(0, new Quest("Start", "The start", 10, 10, new ArrayList<>(questItem), "Merlin"));
    return questMap;
}
}
