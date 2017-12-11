package Acq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * Interface Room
 */
public interface IRoom {

    /**
     * Called to get a Set with the names of the allowed NPC's
     *
     * @return a Set of the names of allowed NPC's
     */
    Set<String> getAllowesNPC();

    /**
     * Called to get a List of the items in the room
     *
     * @return a list of item in the room
     */
    ArrayList<IItem> getItemList();

    /**
     * Called to get the short description of the room
     *
     * @return a short description of the room
     */
    String getShortDescription();

    /**
     * Called to get the name of the room
     *
     * @return the name of the room
     */
    String getName();

    /**
     * Called to get list of the characters in the room
     *
     * @return a list of characters in the room
     */
    List<ICharacterEntity> getCharactersInRoom();

    /**
     * Called to get a HashMap the exit for the room
     *
     * @return a map with the exits from the room
     */
    HashMap<String, IExit> getExits();
}
