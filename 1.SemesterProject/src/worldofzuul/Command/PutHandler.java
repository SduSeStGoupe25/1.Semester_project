package worldofzuul.Command;

import worldofzuul.Game;
import worldofzuul.Room;

/**
 *
 * This class is responsible for processing user input at show console output
 */
public class PutHandler {

    private Parser parser;      //Class responsible for parsing user input
    private Game game;          //A reference to game

    public PutHandler(Game game) {
        parser = new Parser();  //parser are initialized.
        this.game = game;       //sets the referance to game
        
        printWelcome(); //Prints a welcome message
    }

    //ProcessCommand related methods
    /**
     * This method processes a command to find out what to do with it
     *
     * @return true if the user will leave the game otherwise returns false
     */
    public boolean processCommand() {
        Command command = parser.getCommand(); //Gets the next user input
        
        boolean wantToQuit = false; //Indicates if the user whats to quit the game

        CommandWord commandWord = command.getCommandWord(); //Gets the commandWord from the command

        if(null != commandWord) switch (commandWord) {
            case UNKNOWN: //If the commandWord is unknown
                System.out.println("I don't know what you mean...");
                return false;
            case HELP: //If the commandWord is help
                printHelp(); //Prints some help
                break;
            case GO: //If the commandWord is go
                goRoom(command); //Class the method goRoom to go to a room
                break;
            case QUIT: //If the commandWord is quit
                wantToQuit = quit(command); //Sets wantToQuit true
                break;
            default:
                break;
        }
        return wantToQuit; 
    }
    
    /**
     * This private method moves the player to a new room
     *
     * @param command this is the user input about where to go
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) { //Checks if the user has specifie a direction to go
            System.out.println("Go where?");
            return; //If there are on direction don't go anywhere
        }

        String direction = command.getSecondWord(); //Gets the direction to go

        Room nextRoom = game.getCurrentRoom().getExit(direction); //Instantiats a room next to the current room

        if (nextRoom == null) { //Chechs if the current room has a exit in this direction
            System.out.println("There is no door!");
        } else {  //If it has
            game.setCurrentRoom(nextRoom); //Current room is now the nextRoom
            System.out.println(game.getCurrentRoom().getLongDescription()); //Prints a description of the room
        }
    }

    /**
     * This private method is used to check if the user wants to leave the game
     *
     * @param command This is the user input that as to be checked
     * @return boolean true if the user writes only a first word, and false if
     * there also are a second word
     */
    private boolean quit(Command command) {
        if(command.hasSecondWord()) { //If command has a second word
            System.out.println("Quit what?");
            return false;
        }
        else { //if command only has a first word
            return true;
        }
    }
    
    //Print methods
    /**
     * The private method prints a welcome message with informations about the
     * game
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(game.getCurrentRoom().getLongDescription());
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
}
