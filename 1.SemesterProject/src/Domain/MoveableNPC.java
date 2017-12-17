package Domain;

import Acq.IMoveableNPC;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * MoveableNPC class
 */
class MoveableNPC extends NPC implements Moveable, IMoveableNPC {

    /**
     * A Set with the names of the rooms the NPC can be in
     */
    private Set<String> allowedRooms;

    /**
     * Constructor
     *
     * @param name {@link CharacterEntity#name}
     * @param health {@link CharacterEntity#health}
     * @param armor {@link CharacterEntity#armor}
     * @param attack {@link CharacterEntity#attack}
     * @param level {@link CharacterEntity#level}
     * @param expDrop {@link NPC#expDrop}
     * @param talk {@link NPC#talk}
     * @param allowedRooms {@link #allowedRooms}
     * @param itemMap {@link NPC#itemMap}
     * @param hostile {@link CharacterEntity#hostile}
     * @param despawning {@link CharacterEntity#despawning}
     */
    MoveableNPC(String name, int health, int armor, int attack, int level, int expDrop, String talk, Set<String> allowedRooms, Map<String, Integer> itemMap, boolean hostile, boolean despawning) {
        super(name, health, armor, attack, level, expDrop, 4, talk, itemMap, hostile, despawning);
        this.allowedRooms = allowedRooms;
    }

    @Override
    public void move(String nameCurrentRoom) {//Fix before deadline
        Random random = new Random();
        if (random.nextInt(2) > 0) {
            while (true) {
                switch (random.nextInt(4)) {
                    case 0:
                        if (moveNPC(nameCurrentRoom, "north")) {
                            return;
                        }
                        break;
                    case 1:
                        if (moveNPC(nameCurrentRoom, "south")) {
                            return;
                        }
                        break;
                    case 2:
                        if (moveNPC(nameCurrentRoom, "east")) {
                            return;
                        }
                        break;
                    case 3:
                        if (moveNPC(nameCurrentRoom, "west")) {
                            return;
                        }
                        break;
                }
            }
        }
    }

    /**
     * Called to move the NPC to a room
     *
     * @param nameCurrentRoom the name of the current room
     * @param direction the direction to move to
     * @return true if NPC was moved, else false
     */
    private boolean moveNPC(String nameCurrentRoom, String direction) {
        Room currentRoom = (Room) DomainGame.getInstance().getRoomMap().get(nameCurrentRoom);
        if (currentRoom.getExit(direction) != null && allowedRooms.contains(((Exit) currentRoom.getExit(direction)).nextRoom(currentRoom.getName()))) {
            currentRoom.removeCharacterFromRoom(this);
            ((Room) DomainGame.getInstance().getRoomMap().get(((Exit) currentRoom.getExit(direction)).nextRoom(currentRoom.getName()))).addCharacterToRoom(this);
            return true;
        }
        return false;
    }

    @Override
    public Set<String> getAllowedRooms() {
        return allowedRooms;
    }
}
