/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arq;

/**
 *
 * @author Victor Gram
 */
public interface IGame extends IDomainGame {
    
    boolean movePlayer(String direction);
    
    IExit getExitCurrentRoom(String direction);
    
    boolean isInCombat();
    
}
