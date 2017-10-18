package worldofzuul;

import worldofzuul.Command.PutHandler;
import worldofzuul.Entity.MoveableNPC;
import worldofzuul.Entity.NPC;
import worldofzuul.Entity.Player;

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

    public Room citycenter, shop, tavern, castle, excalibur, tower, cave, farm, forrest,
            deepwoods; // creating objects of the Room-class

    /**
     * This is the constructor, which is used when a instance of Game is made.
     */
    public Game() {
        player = new Player("Arthur", 100, 0, 10, 1, 0, null, 0);
        createRooms();
        createNPC();
        putHandler = new PutHandler(this);
    }

    private void createNPC() {
        citycenter.addCharacterToRoom(new MoveableNPC("Merlin", 10, 10, 10, 10, 10, "Hello", citycenter));
        
        tavern.addCharacterToRoom(new NPC("Bartender", 10, 10, 10, 10, 10, "Hello"));
        tavern.addCharacterToRoom(new NPC("Drunk man", 10, 10, 10, 10, 10, "Hello"));
        
        //shop.addCharacterToRoom();
        
        castle.addCharacterToRoom(new NPC("King", 10, 10, 10, 10, 10, "Hello"));
        castle.addCharacterToRoom(new NPC("Princess", 10, 10, 10, 10, 10, "Hello"));
        
        farm.addCharacterToRoom(new NPC("Farmer", 10, 10, 10, 10, 10, "Hello"));
    }

    private void createRooms() {

        //initialising new rooms, with room-description that will be output to the console
        citycenter = new Room("in the center of the city");
        shop = new Room("in the shop");
        tavern = new Room(" in the local tavern");
        castle = new Room("in the kings castle");
        excalibur = new Room("in the room where excalibur is caught in the stone");
        tower = new Room("in Merlin's chambers");
        cave = new Room("in a dark and gloomy cave");
        farm = new Room("at the local farm");
        forrest = new Room("in the forrest");
        deepwoods = new Room("deeper into the woods, more dark and gloomy");

        //defining exits from the city center 
        citycenter.setExit("east", tavern);
        citycenter.setExit("north", shop);
        citycenter.setExit("west", farm);
        citycenter.setExit("south", castle);

        // defining exits from the shop
        shop.setExit("south", citycenter);

        //defining exits form the tavern
        tavern.setExit("west", citycenter);

        // defining exits from the castle
        castle.setExit("north", citycenter);
        castle.setExit("south", tower);
        castle.setExit("east", excalibur);
        castle.setExit("west", cave);

        //defining exits from excalibur
        excalibur.setExit("west", castle);

        //definings exits from tower
        tower.setExit("north", castle);

        //defining exits from cave
        cave.setExit("north", farm);
        cave.setExit("east", castle);

        //defining exits from farm
        farm.setExit("east", citycenter);
        farm.setExit("west", forrest);
        farm.setExit("south", cave);

        // defining exits from forrest
        forrest.setExit("east", farm);
        forrest.setExit("south", deepwoods);

        //defining exits from the deep woods
        deepwoods.setExit("north", forrest);

        currentRoom = citycenter;
    }

    /**
     * The method lets the user play the game
     */
    public void play() {

        boolean finished = false; //Indicates if the game is finished
        while (!finished) {
            finished = putHandler.processCommand();     //If the command is quit, finished is true otherwise continues
        }
        System.out.println("Thank you for playing.  Goodbye."); //Prints an exit message
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

}
