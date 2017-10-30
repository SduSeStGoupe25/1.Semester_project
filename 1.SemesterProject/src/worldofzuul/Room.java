package worldofzuul;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import worldofzuul.Entity.CharacterEntity;
import worldofzuul.Entity.Monster;
import worldofzuul.Entity.Moveable;

import worldofzuul.Inventory.Item;

/**
 * Class that creates and controls the game Rooms/areas. This includes both
 * which items and NPC's there are within the room
 */
/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room {

    private String description; //The room description, printed upon entering
    private HashMap<String, Room> exits;
    private List<CharacterEntity> charactersInRoom = new ArrayList<>(); //ArrayList containing the NPC's in the room
    private List<Item> items = new ArrayList<>(); //ArrayList containing the items in the room which are pickupable through the "search function", e.g. rocks in the city center
    private HashSet<String> allowedMonsters;  


    public Room (String description) { 
        this.description = description; 
        exits = new HashMap<>();
        charactersInRoom = new ArrayList<>(); 
        items = new ArrayList<>(); 
        allowedMonsters = new HashSet<>(); 
        charactersInRoom = new ArrayList<>();
        items = new ArrayList<>();
    }
    public void addItemToRoom(Item i){
        items.add(i);
    }
    public void removeCharacterFromRoom(CharacterEntity ce){
        charactersInRoom.remove(ce);
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
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public List<CharacterEntity> getCharactersInRoom() { //Returns characters in the room
        return charactersInRoom;
    }

    public void addCharacterToRoom(CharacterEntity ce) { //Adds characters to the room
        charactersInRoom.add(ce);
    }
    
    public void addAllowedMonsters(String name) { 
        allowedMonsters.add(name);
    } 

    
    public void spawnEnemies () { //Spawns a randomly generated amount of enemies for the room
        
         if (!allowedMonsters.isEmpty()) { 
             
             int monsterAmount = ((int) (Math.random() * 5) + 1);  
             for (int i = 0; i < monsterAmount; i++) { 
                 int nameValue = (int) Math.random() * allowedMonsters.size();
                 int count = 0; 
                 String monsterName = ""; 
                 for (String name : allowedMonsters) {
                     if (count == nameValue) { 
                         monsterName = name; 
                         break;
                     }
                     i++;
                     
                 }
                 this.charactersInRoom.add(new Monster(monsterName, 10, 1, 1, (int) (Math.random() * 10) + 1, 1));
             }
         }
         
    }

    public CharacterEntity getCharacterEntity(int index) {
        return charactersInRoom.get(index);
    }
    
    public void move(){
        for (int i = charactersInRoom.size()-1;i>=0;i--) {
            CharacterEntity characterEntity = charactersInRoom.get(i);
            if (characterEntity instanceof Moveable){
                ((Moveable) characterEntity).move(this);
            }
            
        }
                
    }
}
