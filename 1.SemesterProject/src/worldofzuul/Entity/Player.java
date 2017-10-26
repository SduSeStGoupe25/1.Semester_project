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
import worldofzuul.Inventory.Item;
import worldofzuul.Inventory.ItemType;
import worldofzuul.Room;

/**
 *
 * @author Victor Gram
 */
public class Player extends CharacterEntity implements Moveable {

    private Inventory itemInventory;
    private Inventory equipableInventory;
    private int gold;
    private Room currentRoom;
    private int exp;
    private HashMap<Integer, String> questLog;

    public Player(String name, int health, int armor, int attack, int level, int gold, Room currentRoom, int exp) {
        super(name, health, armor, attack, level);
        this.gold = gold;
        this.currentRoom = currentRoom;
        this.exp = exp;
        questLog = new HashMap<>();
        itemInventory = new Inventory(20);
        equipableInventory = new Inventory(3);
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
    public void move() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onDeath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
