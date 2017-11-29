package Domain;

import Arq.ICharacterEntity;
import Arq.IExit;
import Arq.IItem;
import Arq.INPC;
import Arq.IRoom;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Class that creates and controls the game Rooms/areas. This includes both
 * which items and NPC's there are within the room
 */
/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
class DomainRoom implements IRoom {

    private String name;
    private String description; //The room description, printed upon entering
    private HashMap<String, IExit> exits;
    private List<ICharacterEntity> charactersInRoom; //ArrayList containing the DomainNPC's in the room
    private ArrayList<IItem> items; //ArrayList containing the items in the room which are pickupable through the "search function", e.g. rocks in the city center
    private Set<String> allowedMonsters;

    public DomainRoom() {
        exits = new HashMap<>();
        charactersInRoom = new ArrayList<>();
        items = new ArrayList<>();
        allowedMonsters = new HashSet<>();
    }
    public DomainRoom(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
        charactersInRoom = new ArrayList<>();
        items = new ArrayList<>();
        allowedMonsters = new HashSet<>();
        charactersInRoom = new ArrayList<>();
        items = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Room{" + "name=" + name + ", description=" + description + ", charactersInRoom=" + charactersInRoom + ", items=" + items + ", allowedMonsters=" + allowedMonsters + '}';
    }

    void removeCharacterFromRoom(DomainCharacterEntity ce) {
        charactersInRoom.remove(ce);
    }

    void setExit(String direction, DomainExit neighbor) {
        exits.put(direction, neighbor);
    }

    String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();

    }

    String getItems() {
        String s = "";
        for (IItem i : items) {
            s += i.getName() + "\n";
        }
        return s;
    }

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    IExit getExit(String direction) {
        return exits.get(direction);
    }

    void addCharacterToRoom(DomainCharacterEntity ce) { //Adds characters to the room
        charactersInRoom.add(ce);
    }

    void addCharacterToRoom(List<DomainCharacterEntity> ce) { //Adds characters to the room
        charactersInRoom.addAll(ce);
    }

    void addAllowedMonsters(String name) {
        allowedMonsters.add(name);
    }

    void addAllowedMonsters(HashSet<String> d) {
        allowedMonsters.addAll(d);
    }

    void spawnEnemies() { //Spawns a randomly generated amount of enemies for the room

        if (!allowedMonsters.isEmpty()) {

            int monsterAmount = ((int) (Math.random() * 5) + 1);
            for (int i = 0; i < monsterAmount; i++) {
                int nameValue = (int) (Math.random() * allowedMonsters.size());
                int count = 0;
                String monsterName = "";
                for (String name : allowedMonsters) {
                    if (count == nameValue) {
                        monsterName = name;
                        break;
                    }
                    count++;

                }
                this.charactersInRoom.add((new DomainNPC(monsterName, 10, 1, 1, (int) (Math.random() * 10) + 1, 20, "Nonono")));

            }
            if ((int) (Math.random() * 2) == 0) {
                int nonMonstersInRoom = charactersInRoom.size() - monsterAmount;
                int opponent = (int) (Math.random() * monsterAmount) + nonMonstersInRoom;
                System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
                System.out.println(DomainGame.getInstance());
                System.out.println(DomainGame.getInstance().getCombat());
                System.out.println();
                System.out.println();
                DomainGame.getInstance().getCombat().startCombat(charactersInRoom.get(opponent), this);
            }
        }

    }

    ICharacterEntity getCharacterEntity(int index) {
        return charactersInRoom.get(index);
    }

    void move() {
        for (int i = charactersInRoom.size() - 1; i >= 0; i--) {
            ICharacterEntity characterEntity = charactersInRoom.get(i);
            if (characterEntity instanceof Moveable) {
                ((Moveable) characterEntity).move(name);
            }

        }

    }

    @Override
    public Set<String> getAllowesMonsters() {
        return allowedMonsters;
    }

    public void setAllowesMonsters(Set<String> allowedMonsters) {
        this.allowedMonsters = allowedMonsters;
    }

    @Override
    public ArrayList<IItem> getItemList() {
        return items;
    }

    public void setItemList(ArrayList<IItem> items) {
        this.items = items;
    }

    @Override
    public String getShortDescription() {
        return description;
    }

    public void setShortDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<ICharacterEntity> getCharactersInRoom() {
        return charactersInRoom;
    }

    public void setCharactersInRoom(List<ICharacterEntity> charactersInRoom) {
        this.charactersInRoom = charactersInRoom;
    }

    @Override
    public HashMap<String, IExit> getExits() {
        return exits;
    }

    public void setExits(HashMap<String, IExit> exits) {
        this.exits = exits;
    }
}
