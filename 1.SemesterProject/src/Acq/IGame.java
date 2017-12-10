/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acq;

import java.util.LinkedHashMap;

/**
 *
 * @author Victor Gram
 */
public interface IGame extends IDomainGame {

    boolean movePlayer(String direction);

    ICombatResponse getCombatResponse(int action);
    
    ICombat getCombat();

    LinkedHashMap getQuestList();

    String getQuestDescription();

    IExit getExitCurrentRoom(String direction);

    boolean isInCombat();

    boolean usePotion();

    boolean restoreHpPlayer(IItem item);

    boolean equipItemPlayer(IItem item);

    boolean removeItemPlayer(IItem item, int amount);

    boolean addItemPlayer(IItem item, int amount);

    IShopkeeper getShopkeeper();

    boolean buy(IItem item, int amount, IPlayer player);

    boolean sell(IItem item, int amount, IPlayer player);
    
    void setPlayerName(String name);

}