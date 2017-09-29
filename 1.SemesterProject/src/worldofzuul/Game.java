package worldofzuul;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    private void createRooms()
    {
        Room citycenter, shop, tavern, castle, excalibur, tower, cave, farm, forrest, 
                deepwoods; // creating objects of the Room-class
        
        citycenter = new Room("in the center of the city"); //initialising new rooms, with explanatory text-output
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

    public void play() 
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
