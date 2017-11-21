/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.ICombat;
import Arq.IDomainGame;
import Arq.IPlayer;
import Arq.IRoom;
import java.util.Map;

/**
 *
 * @author madsd
 */
public class DataGame implements IDomainGame{

    //private static IDomainGame instance;
    private String currentRoom;
    private DataPlayer player;
    //private DataCombat combat;
    
    private Map<String, DataRoom> rooms;
    
    private String[][] itemNames;
    
    public DataGame() {
    
    }
    @Override
    public IPlayer getPlayer() {
        System.out.println("DATA " + player);
        return player;
    }

    @Override
    public IRoom getCurrentRoom() {
        return rooms.get(currentRoom);
    }

    @Override
    public String toString() {
        return "DataGame{" + "currentRoom=" + currentRoom + ", player=" + player + ", combat=" + ", rooms=" + rooms + ", itemNames=" + itemNames + '}';
    }

    @Override
    public Map<String, IRoom> getRoomMap() {
        return null;//rooms;
    }
 
    
}
