/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Entity;

import Domain.whaever.Room;

/**
 *
 * @author Victor Gram
 */
public interface Moveable {
    public abstract void move (Room currentRoom); 
}
