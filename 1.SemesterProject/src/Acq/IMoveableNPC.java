package Acq;

import java.util.Set;

/**
 *
 * Interface for moveableNPC
 */
public interface IMoveableNPC extends INPC {

    /**
     * Called to get a Set of name one the charaterEntitys that are allowed in
     * the room
     *
     * @return a Set with String
     */
    Set<String> getAllowedRooms();
}
