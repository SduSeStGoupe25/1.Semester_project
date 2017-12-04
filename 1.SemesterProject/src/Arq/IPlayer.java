/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arq;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author madsd
 */
public interface IPlayer extends ICharacterEntity {

    IInventory getItemInventory();

    IInventory getEquipableInventory();

    int getGold();

    int getExp();

    LinkedHashMap<Integer, IQuest> getMainQuest();

    HashMap<String, IQuest> getSideQuest();

    int getQuestsCompleted();

    int getHunger();

    int getMaxHunger();

    int getExpToLevelUp();

    int getScoreValue();
    
    void getCompleteQuest(String room);
    
    int getAttackValue();
    
    int getArmorValue();
}
