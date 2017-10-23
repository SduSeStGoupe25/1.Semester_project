/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Inventory;

import java.util.HashMap;
import java.util.Map;

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
}
public static Map<String, Item> getItemMap(){
    return itemMap;
}
}
