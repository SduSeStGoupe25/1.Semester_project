package WorldofArthur;

import WorldofArthur.Combat.Combat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import WorldofArthur.Command.PutHandler;
import WorldofArthur.Entity.MoveableNPC;
import WorldofArthur.Entity.NPC;
import WorldofArthur.Entity.Player;
import WorldofArthur.Entity.Shopkeeper;
import WorldofArthur.Inventory.Armor;
import WorldofArthur.Inventory.Consumeable;
import WorldofArthur.Inventory.Item;
import WorldofArthur.Inventory.Key;
import WorldofArthur.Inventory.NormalItem;
import WorldofArthur.Inventory.Weapon;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 *
 * This is the main class, that lets the user play the game. The game is played
 * by write in the console
 */
public class Game {

    private PutHandler putHandler;  //Class responsible for user input and print output
    private Room currentRoom;       //The current room
    private Player player;
    private Combat combat;

    public Map<String, Room> rooms; // creating objects of the Room-class
    private boolean finished = false;

    private String[][] itemNames = {
        {"Rock", "Sword"}, {"Chainmail"}, {"Potion", "Meat"}, {"Super secret key"}, {"Wool"}};

    /**
     * This is the constructor, which is used when a instance of Game is made.
     */
    public Game() {
        player = new Player("Arthur", 100, 1, 1, 1, 1000, null, 0, this);
        rooms = new HashMap<>();
        createRooms();
        createNPC();
        putHandler = new PutHandler(this);
        combat = new Combat(player, this);
    }

    /**
     * This method creates all the NPC's in the game. It sets their attributes,
     * and gives moveableNPC's their allowed rooms
     */
    private void createNPC() {
        rooms.get("citycenter").addCharacterToRoom(new MoveableNPC("Merlin", 100, 1, 1, 10, 10, "Hello", new HashSet(Arrays.asList(rooms.get("citycenter"), rooms.get("shop"), rooms.get("tavern"), rooms.get("tower"), rooms.get("castle")))));

        rooms.get("tavern").addCharacterToRoom(new NPC("Bartender", 10, 10, 10, 10, 10, "Hello"));
        rooms.get("tavern").addCharacterToRoom(new NPC("Drunk man", 10, 10, 10, 10, 10, "Hello"));

        rooms.get("shop").addCharacterToRoom(new Shopkeeper("Shopkeeper", 10, 10, 10, 10, 10, "Hello"));
        rooms.get("castle").addCharacterToRoom(new NPC("King", 10, 10, 10, 10, 10, "Hello"));
        rooms.get("castle").addCharacterToRoom(new MoveableNPC("Princess", 10, 10, 10, 10, 10, "Hello", new HashSet(Arrays.asList(rooms.get("citycenter"), rooms.get("shop"), rooms.get("tavern"), rooms.get("tower"), rooms.get("castle")))));

        rooms.get("farm").addCharacterToRoom(new NPC("Farmer", 10, 10, 10, 10, 10, "Hello"));
    }

    /**
     * This method creates all the rooms in the game, and sets the correct
     * exits.
     */
    private void createRooms() {

        //initialising new rooms, with room-description that will be output to the console
        Room citycenter = new Room("in the center of the city");
        Room shop = new Room("in the shop");
        Room tavern = new Room(" in the local tavern");
        Room castle = new Room("in the kings castle");
        Room excalibur = new Room("in the room where excalibur is caught in the stone");
        Room tower = new Room("in Merlin's chambers");
        Room cave = new Room("in a dark and gloomy cave");
        Room farm = new Room("at the local farm");
        Room forrest = new Room("in the forrest");
        Room deepwoods = new Room("deeper into the woods, more dark and gloomy");

        // Defining allowed monsters for each room
        forrest.addAllowedMonsters("Imp");
        forrest.addAllowedMonsters("Bear");
        forrest.addAllowedMonsters("Ogre");

        farm.addAllowedMonsters("Sheep");

        //Defining exits
        Exit exitCitycenterTavern = new Exit(citycenter, tavern);
        Exit exitCitycenterShop = new Exit(citycenter, shop);
        Exit exitCitycenterFarm = new Exit(citycenter, farm);
        Exit exitCitycenterCastle = new Exit(citycenter, castle);

        Exit exitCastleTower = new Exit(castle, tower);
        Exit exitCastleExcalibur = new Exit(castle, excalibur);
        Exit exitCastleCave = new Exit(castle, cave);

        Exit exitCaveDeepwoods = new Exit(cave, deepwoods);
        Exit exitDeepwoodsForrest = new Exit(forrest, deepwoods);
        Exit exitFarmForrest = new Exit(farm, forrest);

        //defining exits from the city center 
        citycenter.setExit("east", exitCitycenterTavern);
        citycenter.setExit("north", exitCitycenterShop);
        citycenter.setExit("west", exitCitycenterFarm);
        citycenter.setExit("south", exitCitycenterCastle);
        citycenter.addItemToRoom(createItem(itemNames[0][0], 0));
        rooms.put("citycenter", citycenter);
        // defining exits from the shop
        shop.setExit("south", exitCitycenterShop);
        rooms.put("shop", shop);
        //defining exits form the tavern
        tavern.setExit("west", exitCitycenterTavern);
        rooms.put("tavern", tavern);
        // defining exits from the castle
        castle.setExit("north", exitCitycenterCastle);
        castle.setExit("south", exitCastleTower);
        castle.setExit("east", exitCastleExcalibur);
        castle.setExit("west", exitCastleCave);
        rooms.put("castle", castle);
        //defining exits from excalibur
        excalibur.setExit("west", exitCastleExcalibur);
        rooms.put("excalibur", excalibur);
        //definings exits from tower
        tower.setExit("north", exitCastleTower);
        rooms.put("tower", tower);
        //defining exits from cave
        cave.setExit("north", exitCaveDeepwoods);
        cave.setExit("east", exitCastleCave);
        rooms.put("cave", cave);
        //defining exits from farm
        farm.setExit("east", exitCitycenterFarm);
        farm.setExit("west", exitFarmForrest);
        rooms.put("farm", farm);
        // defining exits from forrest
        forrest.setExit("east", exitFarmForrest);
        forrest.setExit("south", exitDeepwoodsForrest);
        rooms.put("forrest", forrest);
        //defining exits from the deep woods
        deepwoods.setExit("north", exitDeepwoodsForrest);
        deepwoods.setExit("south", exitCaveDeepwoods);
        rooms.put("deepwoods", deepwoods);
        currentRoom = citycenter;
    }

    /**
     * The method lets the user play the game
     */
    public void play() {

        while (!finished) {
            if (combat.isRunning()) {
                putHandler.processCommandCombat();
                continue;
            }
            finished = putHandler.processCommand();     //If the command is quit, finished is true otherwise continues
        }
    }

    /**
     * Gets the player
     *
     * @return Returns the player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the current room
     *
     * @return Returns the currentRoom object
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the current room
     *
     * @param currentRoom Sets the currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Gets combat
     *
     * @return Returns the combat object
     */
    public Combat getCombat() {
        return combat;
    }

    /**
     * This method is called when moveableNPC's should move. It uses a for-each
     * loop to go through all rooms, and calls the move() method in each.
     */
    public void moveAllNPC() {
        for (String room : rooms.keySet()) {
            rooms.get(room).move();

        }
    }

    public int checkItemName(String itemName) {
        int count = 0;
        for (String[] itemName1 : itemNames) {
            for (String string : itemName1) {
                if (itemName.equalsIgnoreCase(string)) {
                    return count;
                }
            }
            count++;
        }
        return -1;
    }

    /**
     * Gets the map of rooms
     *
     * @return Returns the map rooms
     */
    public Map<String, Room> getRoomMap() {
        return rooms;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String[][] getItemNames() {
        return itemNames;
    }

    public Item createItem(String name, int itemType) {
        switch (itemType) {
            case 0:
                return new Weapon(name, 1, 1, 1, 1);

            case 1:
                return new Armor(name, 1, 1, 1, 1);

            case 2:
                return new Consumeable(name, 1, 1, 1);

            case 3:
                return new Key(name, 1, 1, 1);

            case 4:
                return new NormalItem(name, 1, 1);

            default:
                return null;
        }
    }
}
