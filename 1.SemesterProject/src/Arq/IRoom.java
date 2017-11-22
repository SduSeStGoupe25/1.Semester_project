/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author madsd
 */
public interface IRoom {

    Set<String> getAllowesMonsters();

    ArrayList<IItem> getItemList();

    String getShortDescription();

    String getName();
    
    List<ICharacterEntity> getCharactersInRoom();
    
    HashMap<String, IExit> getExits();
}
