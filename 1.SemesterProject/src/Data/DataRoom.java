package Data;

import Acq.ICharacterEntity;
import Acq.IExit;
import Acq.IItem;
import Acq.IRoom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DataRoom class
 */
class DataRoom implements IRoom {

    private String name;
    private String description;
    private HashMap<String, IExit> exits;
    private List<ICharacterEntity> charactersInRoom;
    private ArrayList<IItem> items;
    private HashSet<String> allowedMonsters;

    @Override
    public Set<String> getAllowesNPC() {
        return allowedMonsters;
    }

    @Override
    public ArrayList<IItem> getItemList() {
        return items;
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

    @Override
    public HashMap<String, IExit> getExits() {
        return exits;
    }
}
