package Data;

import Acq.IDomainGame;
import Acq.IPlayer;
import Acq.IRoom;
import java.util.Map;

/**
 * DataGame class
 */
public class DataGame implements IDomainGame {

    private String currentRoom;
    private DataPlayer player;

    private Map<String, IRoom> rooms;

    public DataGame() {
    }

    @Override
    public String getCurrentRoom() {
        return currentRoom;
    }

    @Override
    public IPlayer getPlayer() {
        return player;
    }

    @Override
    public Map<String, IRoom> getRoomMap() {
        return rooms;
    }
}
