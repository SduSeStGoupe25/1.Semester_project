package worldofzuul;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import worldofzuul.Entity.CharacterEntity;
import worldofzuul.Inventory.Item;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private List<CharacterEntity> charactersInRoom = new ArrayList<>(); 
    private List<Item> items = new ArrayList<>();
    

    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        charactersInRoom = new ArrayList<>(); 
        items = new ArrayList<>(); 
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
    
    public List<CharacterEntity> getCharactersInRoom(){
        return charactersInRoom;
    }
    
    public void addCharacterToRoom(CharacterEntity ce){
        charactersInRoom.add(ce);
    }
}

