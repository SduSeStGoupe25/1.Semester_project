package Domain;

import Arq.ICombat;
import Arq.ICombatResponse;
import Arq.IConsumeable;
import Arq.IDomainGame;
import Arq.IExit;
import Arq.IGame;
import Arq.IItem;
import Arq.IPlayer;
import Arq.IRoom;
import Arq.IShopkeeper;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 *
 * This is the main class, that lets the user play the game. The game is played
 * by write in the console
 */
public class DomainGame implements IGame {

    private transient static DomainGame instance = null;
    private String currentRoom;       //The current room
    private Player player;
    private transient ICombat combat;

    private Map<String, IRoom> rooms; // creating objects of the Room-class

    void setMap(Map<String, Room> m) {
        rooms.clear();
        rooms = new HashMap<>(m);
    }

    private transient boolean finished = false;

    /**
     * This is the constructor, which is used when a instance of Game is made.
     */
    private DomainGame() {
        rooms = new HashMap<>();
    }

    void makeCombat() {
        combat = new Combat(player, this);
    }

    public static DomainGame getInstance() {
        if (instance == null) {
            instance = new DomainGame();
        }
        return (DomainGame) instance;
    }

    /**
     * Gets the player
     *
     * @return Returns the player object
     */
    @Override
    public IPlayer getPlayer() {
        return (IPlayer) player;
    }

    void setPlayer(IPlayer player) {
        this.player = (Player) player;
    }

    /**
     * Gets the current room
     *
     * @return Returns the currentRoom object
     */
    public IRoom getCurrentIRoom() {
        return (IRoom) getRoomMap().get(currentRoom);
    }

    /**
     * Sets the current room
     *
     * @param currentRoom Sets the currentRoom
     */
    void setCurrentIRoom(Room currentRoom) {
        this.currentRoom = currentRoom.getName();
    }
    
    /**
     * This method is called when moveableNPC's should move. It uses a for-each
     * loop to go through all rooms, and calls the move() method in each.
     */
    public void moveAllNPC() {
        for (String room : rooms.keySet()) {
            ((Room) rooms.get(room)).move();

        }
    }

    void setFinished(boolean finished) {
        this.finished = finished;
    }

    // @Override
    public boolean goRoom(String direction) {

        IExit exit = ((Room) getCurrentIRoom()).getExit(direction); //Instantiats a room next to the current room

        if (exit == null) { //Chechs if the current room has a exit in this direction
            return false;

        } else if (((Exit) exit).isLocked(player.getItemInventory())) {
            return false;
        } else {  //If it has
            Room nextRoom = (Room) getRoomMap().get(((Exit) exit).nextRoom(getCurrentIRoom().getName()));
            setCurrentIRoom(nextRoom); //Current room is now the nextRoom
            player.addHunger(-3);
            ((Room) getCurrentIRoom()).spawnEnemies();
            return true;
        }
    }

    @Override
    public ICombat getCombat() {
        return combat;
    }

    @Override
    public String getCurrentRoom() {
        return currentRoom;
    }

    void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Gets the map of rooms
     *
     * @return Returns the map rooms
     */
    @Override
    public Map<String, IRoom> getRoomMap() {
        return rooms;
    }

    void setRoomMap(Map<String, IRoom> rooms) {
        this.rooms = rooms;
    }

//    @Override
//    public String[][] getItemNames() {
//        return itemNames;
//    }
//
//    void setItemNames(String[][] itemNames) {
//        this.itemNames = itemNames;
//    }
    @Override
    public boolean movePlayer(String direction) {
        return goRoom(direction);
    }

    @Override
    public IExit getExitCurrentRoom(String direction) {
        if (rooms.get(currentRoom).getExits().containsKey(direction)) {
            return rooms.get(currentRoom).getExits().get(direction);
        } else {
            return null;
        }
    }

    @Override
    public boolean isInCombat() {
        return ((Combat) combat).isRunning();
    }

    @Override
    public LinkedHashMap getQuestList() {
        return player.getMainQuest();
    }

    @Override
    public String getQuestDescription() {
        String questDescription = player.getMainQuest().get(player.getQuestsCompleted()).getDescription();
        return questDescription;
    }

    @Override
    public boolean restoreHpPlayer(IItem item) {
        return player.restoreHp((Item) item);
    }

    @Override
    public boolean equipItemPlayer(IItem item) {
        return player.equip((Item) item);
    }

    @Override
    public boolean removeItemPlayer(IItem item, int amount) {
        Inventory inventory = (Inventory) player.getItemInventory();
        return inventory.removeItem((Item) item, amount);
    }

    @Override
    public boolean addItemPlayer(IItem item, int amount) {
        Inventory inventory = (Inventory) player.getItemInventory();
        return inventory.addItem((Item) item, amount);
    }

    @Override
    public ICombatResponse getCombatResponse(int action) {
        return combat.combatLoop(action);
    }

    @Override
    public boolean usePotion() {
        for (IItem consumeable : player.getItemInventory().getInventory()) {
            System.out.println("her står vi nu jaja, i usepotion jaja" + player.getItemInventory().getInventory().contains(this));
            if (consumeable instanceof Consumeable) {
                System.out.println("instanceof statement klaret jaja");
                if (consumeable.getName().equals("Potion")) {
                    player.restoreHp(consumeable);
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public IShopkeeper getShopkeeper() {
        if (rooms.get("shop").getCharactersInRoom().get(0) instanceof IShopkeeper) {
            return (IShopkeeper) rooms.get("shop").getCharactersInRoom().get(0);
        }
        return null;
    }

    @Override
    public boolean buy(IItem item, int amount, IPlayer player) {
        Shopkeeper s = (Shopkeeper) rooms.get("shop").getCharactersInRoom().get(0);
        if (s.buy((Item) item, amount, (Player) player) == true) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean sell(IItem item, int amount, IPlayer player) {
        Shopkeeper s = (Shopkeeper) rooms.get("shop").getCharactersInRoom().get(0);
        if (s.sell((Item) item, amount, (Player) player) == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setPlayerName(String name) {
        player.setName(name);
    }
}
