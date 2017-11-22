/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arq;

import java.util.Map;

/**
 *
 * @author madsd
 */
public interface IPlayer extends ICharacterEntity {

    IInventory getItemInventory();

    IInventory getEquipableInventory();

    int getGold();

    int getExp();

    Map<Integer, IQuest> getMainQuest();

    Map<String, IQuest> getSideQuest();

    int getQuestsCompleted();

    int getHunger();

    int getMaxHunger();

    int getExpToLevelUp();

    int getScoreValue();
}
