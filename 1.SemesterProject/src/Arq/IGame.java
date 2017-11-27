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
    ICombatResponse getCombatResponse(int action);
    

    LinkedHashMap getQuestList();
    String getQuestDescription();
    
    IExit getExitCurrentRoom(String direction);
    
    boolean isInCombat();
    
}
