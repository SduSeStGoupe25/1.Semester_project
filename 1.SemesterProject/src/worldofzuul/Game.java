package worldofzuul;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * This is the main class, that lets the user play the game. 
 * The game is played by write in the console 
 */
public class Game {
    private Parser parser;      //Class responsible for parsing the user input
    private Room currentRoom;   //The current room
    
    /**
     * This is the constructor, which is used when a instance of Game is made.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }


    private void createRooms()
    {
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
        printWelcome(); //Prints a welcome message

        boolean finished = false; //Indicates if the game is finished
        while (! finished) {    
            Command command = parser.getCommand();  //Gets the user input
            finished = processCommand(command);     //If the command is quit, finished is true otherwise continues
        }
        System.out.println("Thank you for playing.  Good bye."); //Prints a exit message
    }

    /**
     * The private method prints a welcome message with informations about the game
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * This private method processes a command to find out what to do with it
     * @param command This is the user input
     * @return true if the user will leave the game otherwise returns false 
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false; //Indicates if the user whats to quit the game

        CommandWord commandWord = command.getCommandWord(); //Gets the commandWord from the command

        if(commandWord == CommandWord.UNKNOWN) {    //If the commandWord is unknown
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) { //If the commandWord is help
            printHelp(); //Prints some help
        }
        else if (commandWord == CommandWord.GO) { //If the commandWord is go
            goRoom(command); //Class the method goRoom to go to a room
        }
        else if (commandWord == CommandWord.QUIT) { //If the commandWord is quit
            wantToQuit = quit(command); //Sets wantToQuit true
        }
        return wantToQuit; 
    }

    /**
     * This private method prints a text to help the user play the game
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands(); //Prints all commandWords
    }

    /**
     * This private method moves the player to a new room
     * @param command this is the user input about where to go
     */
    private void goRoom(Command command) {
        if(!command.hasSecondWord()) { //Checks if the user has specifie a direction to go
            System.out.println("Go where?");
            return; //If there are on direction don't go anywhere
        }
        
        String direction = command.getSecondWord(); //Gets the direction to go

        Room nextRoom = currentRoom.getExit(direction); //Instantiats a room next to the current room

        if (nextRoom == null) { //Chechs if the current room has a exit in this direction
            System.out.println("There is no door!");
        }
        else {  //If it has
            currentRoom = nextRoom; //Current room is now the nextRoom
            System.out.println(currentRoom.getLongDescription()); //Prints a description of the room
        }
    }

    /**
     * This private method is used to check if the user wants to leave the game
     * @param command This is the user input that as to be checked
     * @return boolean true if the user writes only a first word, and false if there also are a second word
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) { //If command has a second word
            System.out.println("Quit what?");
            return false;
        }
        else { //if command only has a first word
            return true;
        }
    }
}
