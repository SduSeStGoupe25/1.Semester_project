package Acq;

import java.util.Map;

/**
 *
 * Interface for DomainGame
 */
public interface IDomainGame {

    /**
     * Called to get the name of the current room
     *
     * @return name of the current room
     */
    String getCurrentRoom();

    /**
     * Called to get the player
     *
     * @return the player
     */
    IPlayer getPlayer();

    /**
     * Called to get a Map with all Rooms in the game
     *
     * @return a Map with Rooms
     */
    Map<String, IRoom> getRoomMap();
}
