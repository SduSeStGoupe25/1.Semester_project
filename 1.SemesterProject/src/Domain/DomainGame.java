package Domain;

import Arq.ICombat;
import Arq.IConsumeable;
import Arq.ICombatResponse;
import Arq.IConsumeable;
import Arq.IDomainGame;
import Arq.IExit;
import Arq.IGame;
import Arq.IItem;
import Arq.IPlayer;
import Arq.IRoom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import UI.Command.PutHandler;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 *
 * This is the main class, that lets the user play the game. The game is played
 * by write in the console
 */
public class DomainGame implements IGame {

    @Override
    public String toString() {
        return "DomainGame{" + "currentRoom=" + currentRoom + ", player=" + player + ", combat=" + combat + ", rooms=" + rooms + ", finished=" + finished + ", itemNames=" + itemNames + '}';
    }

    private transient static DomainGame instance = null;
    // private transient PutHandler putHandler;  //Class responsible for user input and print output
    private String currentRoom;       //The current room
    private DomainPlayer player;
    private transient ICombat combat;

    private Map<String, IRoom> rooms; // creating objects of the DomainRoom-class

    void setMap(Map<String, DomainRoom> m) {
        rooms.clear();
        rooms = new HashMap<>(m);
        System.out.println("M ::::: " + rooms);
    }

    private transient boolean finished = false;

    private String[][] itemNames = {
        {"Rock", "Sword"}, {"Chainmail"}, {"Potion", "Meat"}, {"Key", "Key2"}, {"Wool"}};

    /**
     * This is the constructor, which is used when a instance of Game is made.
     */
    public DomainGame() {
        //System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
////        player = new DomainPlayer("Arthur", 100, 10, 10, 1, 1000, 0);
////        player.getItemInventory().addItem(createItem(itemNames[4][0],0), 1);
        rooms = new HashMap<>();
        instance = this;
        
////        createRooms();
////        createNPC();
////        //putHandler = new PutHandler(this);

        //System.out.println(this.toString());
    }

    void makeCombat() {
        combat = new DomainCombat(player, this);
        System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOMMMMMMMMMMMMMMMMMMMMMMMMMMAAAAAAAT");
        System.out.println(combat);
    }

    IDomainGame initialize(IDomainGame game) {
        System.out.println("INGAME");
//        System.out.println(this);
//        System.out.println(currentRoom);
//        System.out.println(game);
        instance = (DomainGame) game;

        System.out.println(instance);
//        System.out.println(game.getCurrentRoom().getName());
//        currentRoom = game.getCurrentRoom().getName();
//        player = (DomainPlayer) game.getPlayer();
//        if(game.getRoomMap() != null) {
//        for (String itemName : game.getRoomMap().keySet()) {
//            rooms.put(itemName, (DomainRoom) game.getRoomMap().get(itemName));
//        }
//        }
        return instance;
    }

    public static DomainGame getInstance() {
        if (instance == null) {
            instance = new DomainGame();
        }
        return (DomainGame) instance;
    }

    /**
     * This method creates all the NPC's in the game. It sets their attributes,
     * and gives moveableNPC's their allowed rooms
     */
    private void createNPC() {
////        rooms.get("citycenter").addCharacterToRoom(new MoveableNPC("Merlin", 100, 1, 1, 10, 10, "Hello", new HashSet(Arrays.asList("citycenter", "shop", "tavern", "tower", "castle"))));
////        
////        rooms.get("tavern").addCharacterToRoom(new NPC("Bartender", 10, 10, 10, 10, 10, "Hello"));
////        rooms.get("tavern").addCharacterToRoom(new NPC("Drunk man", 10, 10, 10, 10, 10, "Hello"));
////        
////        rooms.get("shop").addCharacterToRoom(new Shopkeeper("Shopkeeper", 10, 10, 10, 10, 10, "Hello"));
////        rooms.get("castle").addCharacterToRoom(new NPC("King", 10, 10, 10, 10, 10, "Hello"));
////        rooms.get("castle").addCharacterToRoom(new MoveableNPC("Princess", 10, 10, 10, 10, 10, "Hello", new HashSet(Arrays.asList("citycenter", "shop", "tavern", "tower", "castle"))));
////        
////        rooms.get("farm").addCharacterToRoom(new NPC("Farmer", 10, 10, 10, 10, 10, "Hello"));
    }

    /**
     * This method creates all the rooms in the game, and sets the correct
     * exits.
     */
    private void createRooms() {
////
////        //initialising new rooms, with room-description that will be output to the console
////        DomainRoom citycenter = new DomainRoom("citycenter", "in the center of the city");
////        DomainRoom shop = new DomainRoom("shop", "in the shop");
////        DomainRoom tavern = new DomainRoom("tavern", " in the local tavern");
////        DomainRoom castle = new DomainRoom("castle", "in the kings castle");
////        DomainRoom excalibur = new DomainRoom("excalibur", "in the room where excalibur is caught in the stone");
////        DomainRoom tower = new DomainRoom("tower", "in Merlin's chambers");
////        DomainRoom cave = new DomainRoom("cave", "in a dark and gloomy cave");
////        DomainRoom farm = new DomainRoom("farm", "at the local farm");
////        DomainRoom forrest = new DomainRoom("forrest", "in the forrest");
////        DomainRoom deepwoods = new DomainRoom("deepwoods", "deeper into the woods, more dark and gloomy");
////
////        // Defining allowed monsters for each room
////        forrest.addAllowedMonsters("Imp");
////        forrest.addAllowedMonsters("Bear");
////        forrest.addAllowedMonsters("Ogre");
////        
////        farm.addAllowedMonsters("Sheep");
////
////        //Defining exits
////        DomainExit exitCitycenterTavern = new DomainExit("citycenter", "tavern");
////        DomainExit exitCitycenterShop = new DomainExit("citycenter", "shop");
////        DomainExit exitCitycenterFarm = new DomainExit("citycenter", "farm");
////        DomainExit exitCitycenterCastle = new DomainExit("citycenter", "castle");
////        
////        DomainExit exitCastleTower = new DomainExit("castle", "tower");
////        DomainExit exitCastleExcalibur = new DomainExit("castle", "excalibur", true, 1);
////        DomainExit exitCastleCave = new DomainExit("castle", "cave", true, 2);
////        
////        DomainExit exitCaveDeepwoods = new DomainExit("cave", "deepwoods");
////        DomainExit exitDeepwoodsForrest = new DomainExit("forrest", "deepwoods");
////        DomainExit exitFarmForrest = new DomainExit("farm", "forrest");
////
////        //defining exits from the city center 
////        citycenter.setExit("east", exitCitycenterTavern);
////        citycenter.setExit("north", exitCitycenterShop);
////        citycenter.setExit("west", exitCitycenterFarm);
////        citycenter.setExit("south", exitCitycenterCastle);
////        citycenter.addItemToRoom(createItem(itemNames[0][0], 0));
////        rooms.put("citycenter", citycenter);
////        // defining exits from the shop
////        shop.setExit("south", exitCitycenterShop);
////        rooms.put("shop", shop);
////        //defining exits form the tavern
////        tavern.setExit("west", exitCitycenterTavern);
////        rooms.put("tavern", tavern);
////        // defining exits from the castle
////        castle.setExit("north", exitCitycenterCastle);
////        castle.setExit("south", exitCastleTower);
////        castle.setExit("east", exitCastleExcalibur);
////        castle.setExit("west", exitCastleCave);
////        rooms.put("castle", castle);
////        //defining exits from excalibur
////        excalibur.setExit("west", exitCastleExcalibur);
////        rooms.put("excalibur", excalibur);
////        //definings exits from tower
////        tower.setExit("north", exitCastleTower);
////        rooms.put("tower", tower);
////        //defining exits from cave
////        cave.setExit("north", exitCaveDeepwoods);
////        cave.setExit("east", exitCastleCave);
////        rooms.put("cave", cave);
////        //defining exits from farm
////        farm.setExit("east", exitCitycenterFarm);
////        farm.setExit("west", exitFarmForrest);
////        rooms.put("farm", farm);
////        // defining exits from forrest
////        forrest.setExit("east", exitFarmForrest);
////        forrest.setExit("south", exitDeepwoodsForrest);
////        rooms.put("forrest", forrest);
////        //defining exits from the deep woods
////        deepwoods.setExit("north", exitDeepwoodsForrest);
////        deepwoods.setExit("south", exitCaveDeepwoods);
////        rooms.put("deepwoods", deepwoods);
////        currentRoom = citycenter.getName();
    }

    /**
     * The method lets the user play the game
     */
    void play() {

////        while (!finished) {
////            if (combat.isRunning()) {
////                putHandler.processCommandCombat();
////                continue;
////            }
////            if (player.getHealth() <= 0) {
////                player.onDeath();
////                continue;
////            }
////            System.out.println(player.getHealth());
////            finished = putHandler.processCommand();     //If the command is quit, finished is true otherwise continues
////        }
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

    public void setPlayer(IPlayer player) {
        this.player = (DomainPlayer) player;
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
    public void setCurrentIRoom(DomainRoom currentRoom) {
        this.currentRoom = currentRoom.getName();
    }

    /**
     * Gets combat
     *
     * @return Returns the combat object
     */
    public DomainCombat getCombat() {
        return (DomainCombat) combat;
    }

    /**
     * This method is called when moveableNPC's should move. It uses a for-each
     * loop to go through all rooms, and calls the move() method in each.
     */
    public void moveAllNPC() {
        for (String room : rooms.keySet()) {
            ((DomainRoom) rooms.get(room)).move();

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

    void setFinished(boolean finished) {
        this.finished = finished;
    }

    public DomainItem createItem(String name, int itemType) {
        switch (itemType) {
            case 0:
                return new DomainWeapon(name, 1, 1, 1, 1);

            case 1:
                return new DomainArmor(name, 1, 1, 1, 1);

            case 2:
                return new DomainConsumeable(name, 1, 1, 1, 1);

            case 3:
                return new DomainKey(name, 1, 1, 1);

            case 4:
                return new DomainNormalItem(name, 1, 1);

            default:
                return null;
        }
    }

////    @Override
////    public void saveGame() {
////        db.saveGame();
////    }
////    
////    @Override
////    public void loadGame(boolean newGame) {
////        db.loadGame(newGame);
////    }
////    
////    @Override
////    public List<HighscoreWrapper> getHighScore() {
////        return db.getHighscore();
////    }
////    
////    @Override
////    public void addScore(HighscoreWrapper hw) {
////        List<HighscoreWrapper> highList = getHighScore();
////        
////        int count = 0;
////        for (HighscoreWrapper highscoreWrapper : highList) {
////            int compareValue = hw.compareTo(highscoreWrapper);
////            
////            if (compareValue == 0 || compareValue == 1) {
////                break;
////            }
////            count++;
////        }
////        highList.add(count, hw);
////        highList.subList(10, highList.size()).clear();
////        db.saveScoreTable(highList);
////    }
    // @Override
    public boolean goRoom(String direction) {

        IExit exit = ((DomainRoom) getCurrentIRoom()).getExit(direction); //Instantiats a room next to the current room

        if (exit == null) { //Chechs if the current room has a exit in this direction
            return false;

        } else if (((DomainExit) exit).isLocked(player.getItemInventory())) {
            return false;
        } else {  //If it has
            DomainRoom nextRoom = (DomainRoom) getRoomMap().get(((DomainExit) exit).nextRoom(getCurrentIRoom().getName()));
            System.out.println("nextRoom " + nextRoom + " -------------------------------");
            setCurrentIRoom(nextRoom); //Current room is now the nextRoom
            player.addHunger(-3);
            ((DomainRoom) getCurrentIRoom()).spawnEnemies();
            System.out.println(((DomainRoom) getCurrentIRoom()).getLongDescription()); //Prints a description of the room
            return true;
        }
    }

    @Override
    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
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

    public void setRoomMap(Map<String, IRoom> rooms) {
        this.rooms = rooms;
    }

    //@Override
    public String[][] getItemNames() {
        return itemNames;
    }

    public void setItemNames(String[][] itemNames) {
        this.itemNames = itemNames;
    }

    @Override
    public boolean movePlayer(String direction) {
        return goRoom(direction);
    }

    @Override
    public IExit pullExitCurrentRoom(String direction) {
        if (rooms.get(currentRoom).getExits().containsKey(direction)) {
            return rooms.get(currentRoom).getExits().get(direction);
        } else {
            return null;
        }
    }

    @Override
    public boolean isInCombat() {
        return ((DomainCombat) combat).isRunning();
    }

    @Override
    public LinkedHashMap pullQuestList() {
        return player.getMainQuest();
    }

    @Override
    public String pullQuestDescription() {
        String questDescription = player.getMainQuest().get(player.getQuestsCompleted()).getDescription();
        return questDescription;
    }

    @Override
    public boolean restoreHpPlayer(IItem item) {
        return player.restoreHp((DomainItem) item);
    }

    @Override
    public boolean equipItemPlayer(IItem item) {
        return player.equip((DomainItem) item);
    }

    @Override
    public boolean removeItemPlayer(IItem item, int amount) {
        DomainInventory inventory = (DomainInventory) player.getItemInventory();
        return inventory.removeItem((DomainItem) item, amount);
    }

    @Override
    public boolean addItemPlayer(IItem item, int amount) {
        DomainInventory inventory = (DomainInventory) player.getItemInventory();
        return inventory.addItem((DomainItem) item, amount);
    }

    @Override
    public ICombatResponse pullCombatResponse(int action) {
        return combat.combatLoop(action);
    }

    @Override
    public boolean usePotion() {
        for (IItem consumeable : player.getItemInventory().getInventory()) {
            if (consumeable instanceof IConsumeable) {
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
}
