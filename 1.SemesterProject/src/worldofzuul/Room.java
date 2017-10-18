package worldofzuul;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import worldofzuul.Entity.CharacterEntity;
import worldofzuul.Entity.Monster;
import worldofzuul.Entity.Player;
import worldofzuul.Inventory.Item;


/**
 * Class that creates and controls the game Rooms/areas. This includes both which items
 * and NPC's there are within the room
 */

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room {
    private String description; //The room description, printed upon entering
    private HashMap<String, Room> exits;
    private List<CharacterEntity> charactersInRoom = new ArrayList<>(); //ArrayList containing the NPC's in the room
    private List<Item> items = new ArrayList<>(); //ArrayList containing the items in the room which are pickupable through the "search function", e.g. rocks in the city center
    private HashMap<String, Monster> allowedMonsters;  
    

    public Room(String description) {
        this(description, null); 
        
        
    }
    public Room (String description, HashMap<String, Monster> allowedMonsters) { 
        this.description = description; 
        exits = new HashMap<>();
        charactersInRoom = new ArrayList<>(); 
        items = new ArrayList<>(); 
        this.allowedMonsters = allowedMonsters; 
        
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    public List<CharacterEntity> getCharactersInRoom(){ //Returns characters in the room
        return charactersInRoom;
    }
    
    public void addCharacterToRoom(CharacterEntity ce){ //Adds characters to the room
        charactersInRoom.add(ce);
    }
    
    public void spawnEnemies () { //Spawns randomly generated enemies for the room
        
    }
}

