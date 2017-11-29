/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arq;

import java.util.LinkedHashMap;

/**
 *
 * @author Victor Gram
 */
public interface IGame extends IDomainGame {
    
    boolean movePlayer(String direction);
    ICombatResponse pullCombatResponse(int action);
    

    LinkedHashMap pullQuestList();
    String pullQuestDescription();
    
    IExit pullExitCurrentRoom(String direction);
    
    boolean isInCombat();
    
    boolean usePotion(); 
    

    boolean restoreHpPlayer(IItem item);
    
    boolean equipItemPlayer(IItem item);
    
    boolean removeItemPlayer(IItem item, int amount);
    
    boolean addItemPlayer(IItem item, int amount);

}
