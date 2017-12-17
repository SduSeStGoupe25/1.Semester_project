package Domain;

import Acq.ICombat;
import Acq.ICombatResponse;
import Acq.IExit;
import Acq.IGame;
import Acq.IItem;
import Acq.IPlayer;
import Acq.IRoom;
import Acq.IShopkeeper;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 *
 * This is the main class, that lets the user play the game. The game is played
 * by write in the console
 */
class DomainGame implements IGame {

    /**
     * Instance of DomainGame
     */
    private transient static DomainGame instance = null;

    /**
     * The name of the current room
     */
    private String currentRoom;

    /**
     * Instance of player
     */
    private Player player;

    /**
     * Instance of ICombat
     */
    private transient ICombat combat;

    /**
     * The rooms
     */
    private Map<String, IRoom> rooms;

    /**
     * This is the constructor, which is used when a instance of Game is made.
     */
    private DomainGame() {
        rooms = new HashMap<>();
    }

    /**
     * Called to make a instance of Combat
     */
    void makeCombat() {
        combat = new Combat(player);
    }

    /**
     * Called to get a instance of DomainGame
     *
     * @return a instance a DomainGame
     */
    public static DomainGame getInstance() {
        if (instance == null) {
            instance = new DomainGame();
        }
        return (DomainGame) instance;
    }

    @Override
    public IPlayer getPlayer() {
        return (IPlayer) player;
    }

    /**
     * Called to set the player
     *
     * @param player the player to set player to
     */
    void setPlayer(IPlayer player) {
        this.player = (Player) player;
    }

    /**
     * Gets the current room
     *
     * @return Returns the currentRoom object
     */
    IRoom getCurrentIRoom() {
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
    void moveAllNPC() {
        for (String room : rooms.keySet()) {
            ((Room) rooms.get(room)).move();

        }
    }

    /**
     * Called to move the player to a new room
     *
     * @param direction the direction to move to
     * @return true if the player has moved to the new room, else false
     */
    boolean goRoom(String direction) {

        IExit exit = ((Room) getCurrentIRoom()).getExit(direction); //Instantiats a room next to the current room

        if (exit == null) { //Chechs if the current room has a exit in this direction
            return false;

        } else if (((Exit) exit).isLocked(player.getItemInventory())) {
            return false;
        } else {  //If it has
            ((Room) rooms.get(currentRoom)).despawnEnemies();
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

    /**
     * Called to set the current room
     *
     * @param currentRoom the name of the room to set
     */
    void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    @Override
    public Map<String, IRoom> getRoomMap() {
        return rooms;
    }

    /**
     * Called to set rooms Map
     *
     * @param rooms the Map to set rooms to
     */
    void setRoomMap(Map<String, IRoom> rooms) {
        this.rooms = rooms;
    }

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
        System.out.println("INVENTORY USEPOTION " + player.getItemInventory().getInventory());
        System.out.println("SIZE " + player.getItemInventory().getInventory().size());

        for (IItem item : player.getItemInventory().getInventory()) {
            System.out.println("NAME " + item.getName());
        }

        for (IItem consumeable : player.getItemInventory().getInventory()) {
            if (consumeable instanceof Consumeable) {
                System.out.println("instanceof statement klaret jaja");
                if (consumeable.getName().equals("Potion")) {
                    player.restoreHp(consumeable);
                    return true;
                }
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
    public boolean buy(IItem item, int amount) {
        Shopkeeper s = (Shopkeeper) rooms.get("shop").getCharactersInRoom().get(0);
        if (s.buy((Item) item, amount) == true) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean sell(IItem item, int amount) {
        Shopkeeper s = (Shopkeeper) rooms.get("shop").getCharactersInRoom().get(0);
        if (s.sell((Item) item, amount) == true) {
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
