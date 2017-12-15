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

    private String name;
    private String description; //The room description, printed upon entering
    private HashMap<String, IExit> exits;
    private List<ICharacterEntity> charactersInRoom; //ArrayList containing the NPC's in the room
    private ArrayList<IItem> items; //ArrayList containing the items in the room which are pickupable through the "search function", e.g. rocks in the city center
    private HashSet<String> allowedMonsters;

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

    void removeCharacterFromRoom(CharacterEntity ce) {
        charactersInRoom.remove(ce);
    }

    void setExit(String direction, Exit neighbor) {
        exits.put(direction, neighbor);
    }

    IExit getExit(String direction) {
        return exits.get(direction);
    }

    void addCharacterToRoom(CharacterEntity ce) { //Adds characters to the room
        charactersInRoom.add(ce);
    }

    void addCharacterToRoom(List<CharacterEntity> ce) { //Adds characters to the room
        charactersInRoom.addAll(ce);
    }

    void addAllowedMonsters(String name) {
        allowedMonsters.add(name);
    }

    void addAllowedMonsters(HashSet<String> d) {
        allowedMonsters.addAll(d);
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

    ICharacterEntity getCharacterEntity(int index) {
        return charactersInRoom.get(index);
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

    void setAllowesMonsters(HashSet<String> allowedMonsters) {
        this.allowedMonsters = allowedMonsters;
    }

    @Override
    public ArrayList<IItem> getItemList() {
        return items;
    }

    void setItemList(ArrayList<IItem> items) {
        this.items = items;
    }

    @Override
    public String getShortDescription() {
        return description;
    }

    void setShortDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public List<ICharacterEntity> getCharactersInRoom() {
        return charactersInRoom;
    }

    void setCharactersInRoom(List<ICharacterEntity> charactersInRoom) {
        this.charactersInRoom = charactersInRoom;
    }

    @Override
    public HashMap<String, IExit> getExits() {
        return exits;
    }

    void setExits(HashMap<String, IExit> exits) {
        this.exits = exits;
    }
}
