/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Entity;

import worldofzuul.Inventory.Inventory;
import worldofzuul.Entity.CharacterEntity;
import worldofzuul.Entity.Moveable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import worldofzuul.Inventory.Item;
import worldofzuul.Inventory.ItemType;
import worldofzuul.Inventory.Stash;
import worldofzuul.Room;

/**
 *
 * @author Victor Gram
 */
public class Player extends CharacterEntity {

    private Inventory itemInventory;
    private Inventory equipableInventory;
    private int gold;
    private Room currentRoom;
    private int exp;
    private LinkedHashMap<Integer, Quest> mainQuest;
    private HashMap<String, Quest> sideQuest;
    private int questsCompleted;

    public Player(String name, int health, int armor, int attack, int level, int gold, Room currentRoom, int exp) {
        super(name, health, armor, attack, level);
        this.gold = gold;
        this.currentRoom = currentRoom;
        this.exp = exp;
        mainQuest = Stash.getQuestMap();
        itemInventory = new Inventory(20);
        equipableInventory = new Inventory(3);
        questsCompleted = 0;
    }

    public int getAttackValue() {
        int attackValue = attack;
        for (Item item : equipableInventory.getInventory()) {
            if (item.getItemType().equals(ItemType.WEAPON)) {
                attackValue += item.getItemValue();
                break;
            }
        }
        return attackValue;
    }

    public int getArmorValue() {
        int armorValue = armor;
        for (Item item : equipableInventory.getInventory()) {
            if (item.getItemType().equals(ItemType.ARMOR)) {
                armorValue += item.getItemValue();
                break;
            }
        }
        return armorValue;
    }

    public void setCurrentRoom(Room nextRoom) {
        currentRoom = nextRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Inventory getItemInventory() {
        return itemInventory;
    }

    public Inventory getEquipableInventory() {
        return equipableInventory;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void removeGold(int amount) {
        gold -= amount;
    }

    public boolean equip(Item item) {
        if (itemInventory.removeItem(item, 1)) {
            if (item.getItemType().equals(ItemType.WEAPON)) {
                for (Item i : equipableInventory.getInventory()) {
                    if (i.getItemType().equals(ItemType.WEAPON)) {
                        itemInventory.addItem(i, 1);
                        equipableInventory.removeItem(i, 1);
                        equipableInventory.addItem(item, 1);
                        return true;
                    }
                }
                equipableInventory.addItem(item, 1);
                return true;
            } else if (item.getItemType().equals(ItemType.ARMOR)) {
                for (Item i : equipableInventory.getInventory()) {
                    if (i.getItemType().equals(ItemType.ARMOR)) {
                        itemInventory.addItem(i, 1);
                        equipableInventory.removeItem(i, 1);
                        equipableInventory.addItem(item, 1);
                        return true;
                    }
                }
                equipableInventory.addItem(item, 1);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean restoreHp(Item item) {
        if (!(this.health >= this.maxHealth)) {
            if (item.getItemType().equals(ItemType.CONSUMEABLE)) {
                if (this.itemInventory.removeItem(item, 1)) {
                    if (this.health + item.getItemValue() >= this.maxHealth) {
                        this.health = this.maxHealth;
                        return true;
                    } else {
                        this.health += item.getItemValue();
                        return true;
                    }
                }
            }
        }
        return false;
    }


    @Override
    public void onDeath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Quest getCurrentMainQuest() {
        return mainQuest.get(questsCompleted);
    }

    public void setQuestsCompleted() {
        questsCompleted++;
    }

    public boolean checkQuest(Room room) {
        int itemCount = 0;
        for (CharacterEntity ce : room.getCharactersInRoom()) {
            if (ce.getName().equals(getCurrentMainQuest().getGiver())) {
                for (Item item : getCurrentMainQuest().getItems()) {
                    for (Item i : getItemInventory().getInventory()) {
                        if (i.getName().equals(item.getName()) && i.getCount() >= item.getCount()) {
                            itemCount++;
                            break;
                        }
                    }
                }
                if (itemCount == getCurrentMainQuest().getItems().size()) {
                    addGold(getCurrentMainQuest().getGold());
                    for (Item item : getCurrentMainQuest().getItems()) {
                        getItemInventory().removeItem(item, item.getCount());
                    }
                    setQuestsCompleted();
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
