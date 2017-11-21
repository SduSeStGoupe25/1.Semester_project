/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arq;

/**
 *
 * @author madsd
 */
public interface IPlayer extends ICharacterEntity{

    int getScoreValue();

    int getExpToLevelUp();

    int getHunger();

    int getMaxHunger();

    double getHungerPercent();

    int getAttackValue();

    int getArmorValue();

    IInventory getItemInventory();

    IInventory getEquipableInventory();

    int getGold();

    void addGold(int amount);

    void removeGold(int amount);

    IQuest getCurrentMainQuest();

    int getExp();
    
    double getExpPercent();
}
