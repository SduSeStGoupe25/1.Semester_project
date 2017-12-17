package Domain;

import Acq.ICharacterEntity;
import Acq.IExit;
import Acq.IItem;
import Acq.IRoom;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Class that creates and controls the game Rooms/areas. This includes both
 * which items and NPC's there are within the room
 */
class Room implements IRoom {

    /**
     * The name of the room
     */
    private String name;

    /**
     * The room description, printed upon entering
     */
    private String description;

    /**
     * The exits from the room
     */
    private HashMap<String, IExit> exits;

    /**
     * ArrayList containing the CharacterEntity's in the room
     */
    private List<ICharacterEntity> charactersInRoom;

    /**
     * ArrayList containing the items in the room which are pickupable through
     * the "search function", e.g. rocks in the city center
     */
    private ArrayList<IItem> items;

    /**
     * The names of the monsters that can spawn in the room
     */
    private HashSet<String> allowedMonsters;

    /**
     * Constructor
     *
     * @param name {@link #name}
     * @param description {@link #description}
     */
    Room(String name, String description) {
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

    /**
     * Called to remove a character from the room
     *
     * @param ce the CharacterEntity to remove
     */
    void removeCharacterFromRoom(CharacterEntity ce) {
        charactersInRoom.remove(ce);
    }

    /**
     * Called to get a exit in a direction
     *
     * @param direction the name of the direction
     * @return a Exit
     */
    IExit getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * Adds characters to the room
     *
     * @param ce the Character to add
     */
    void addCharacterToRoom(CharacterEntity ce) {
        charactersInRoom.add(ce);
    }

    /**
     * Adds characters to the room
     *
     * @param ce the characters to add
     */
    void addCharacterToRoom(List<CharacterEntity> ce) {
        charactersInRoom.addAll(ce);
    }

    /**
     * Called to despawn despawning NPC's from the room
     */
    void despawnEnemies() {
        if (!charactersInRoom.isEmpty()) {
            Iterator<ICharacterEntity> iterator = charactersInRoom.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().isDespawning()) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * Called to spawn NPC's to the room
     */
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
                this.charactersInRoom.add(DomainData.getInstance().getNPC(monsterName, (int) (Math.random() * 10) + 1));
            }
            if ((int) (Math.random() * 2) == 0) {
                int nonMonstersInRoom = charactersInRoom.size() - monsterAmount;
                int opponent = (int) (Math.random() * monsterAmount) + nonMonstersInRoom;
                ICharacterEntity ce = charactersInRoom.get(opponent);
                if (ce.isHostile()) {
                    DomainGame.getInstance().getCombat().startCombat(ce);
                }
            }
        }
    }

    /**
     * Called to move all movableNPC's in the room
     */
    void move() {
        for (int i = charactersInRoom.size() - 1; i >= 0; i--) {
            ICharacterEntity characterEntity = charactersInRoom.get(i);
            if (characterEntity instanceof Moveable) {
                ((Moveable) characterEntity).move(name);
            }

        }

    }

    @Override
    public Set<String> getAllowesNPC() {
        return allowedMonsters;
    }

    /**
     * Called to set the allowed monsters
     *
     * @param allowedMonsters the names of the monsters
     */
    void setAllowesMonsters(HashSet<String> allowedMonsters) {
        this.allowedMonsters = allowedMonsters;
    }

    @Override
    public ArrayList<IItem> getItemList() {
        return items;
    }

    /**
     * Called to set the items in the room
     *
     * @param items the items
     */
    void setItemList(ArrayList<IItem> items) {
        this.items = items;
    }

    @Override
    public String getShortDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<ICharacterEntity> getCharactersInRoom() {
        return charactersInRoom;
    }

    /**
     * Called to set characters in the room
     *
     * @param charactersInRoom the characters in the room
     */
    void setCharactersInRoom(List<ICharacterEntity> charactersInRoom) {
        this.charactersInRoom = charactersInRoom;
    }

    @Override
    public HashMap<String, IExit> getExits() {
        return exits;
    }

    /**
     * Called to set the exits from the room
     *
     * @param exits the exits from the room
     */
    void setExits(HashMap<String, IExit> exits) {
        this.exits = exits;
    }
}
