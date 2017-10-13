package worldofzuul;

import worldofzuul.Command.PutHandler;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * This is the main class, that lets the user play the game. 
 * The game is played by write in the console 
 */
public class Game {
    private PutHandler putHandler;  //Class responsible for user input and print output
    private Room currentRoom;       //The current room
    
    /**
     * This is the constructor, which is used when a instance of Game is made.
     */
    public Game() {
        createRooms();
        putHandler = new PutHandler(this);
    }


    private void createRooms() {
        Room citycenter, shop, tavern, castle, excalibur, tower, cave, farm, forrest, 
                deepwoods; // creating objects of the Room-class
        
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
       cave.setExit("north",farm);
       cave.setExit("east", castle); 
       
       //defining exits from farm
       farm.setExit("east", citycenter); 
       farm.setExit("west", forrest); 
       farm.setExit("south",cave);
       
       // defining exits from forrest
       forrest.setExit("east", farm); 
       forrest.setExit("south",deepwoods); 
       
       //defining exits from the deep woods
       deepwoods.setExit("north", forrest); 
       
       currentRoom = citycenter;
    }

    /**
     * The method lets the user play the game
     */
    public void play() {            

        boolean finished = false; //Indicates if the game is finished
        while (! finished) {    
            finished = putHandler.processCommand();     //If the command is quit, finished is true otherwise continues
        }
        System.out.println("Thank you for playing.  Good bye."); //Prints an exit message
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }










}
