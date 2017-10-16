package worldofzuul.Command;

import worldofzuul.Entity.CharacterEntity;
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

        if (null != commandWord) {
            switch (commandWord) {
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
                case TALK:
                    break;
                case BUY:

                    break;
                case SELL:

                    break;
                case SEARCH:

                    break;
                case INVENTORY:

                    break;
                case EQUIP:

                    break;
                case DROP:

                    break;
                case ATTACK:
                    printCharactersInRoom(game.getCurrentRoom());
                    attack(command);
                    break;
                case USE:

                    break;
                case QUEST:

                    break;
                default:
                    break;
            }
        }
        return wantToQuit;
    }

    /**
     * This private method checks if the command is valid to start combat, if so
     * starts combat
     *
     * @param command this is the user input about who the target is
     */
    private void attack(Command command) {
        if (!command.hasSecondWord()) { //Checks if the user has specifie a correct target
            System.out.println("Attack who?");
            return; //If there are on target don't attack anyone
        }
        if(isNumeric(command.getSecondWord())){
            System.out.println("Wrong input, try again"); 
        }

        int target = Integer.parseInt(command.getSecondWord()); //Gets the targets index

        if (game.getCurrentRoom().getCharactersInRoom().size() >= target) { //Checks if the target index are a part of the List
            System.out.println("Combat startet!!"); //Invoke combat here
        }
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
        if (command.hasSecondWord()) { //If command has a second word
            System.out.println("Quit what?");
            return false;
        } else { //if command only has a first word
            return true;
        }
    }

    /**
     * This private method checks if a String i a number, if so returns true
     *
     * @param str is to be checked
     * @return true if str is a number, or false if not
     */
    private boolean isNumeric(String str) {
        if (str != null) {
            try {
                double d = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    //Print methods
    /**
     * This private method prints the name and number in a List + 1.
     *
     * @param room the room to print characters from
     */
    private void printCharactersInRoom(Room room) {
        if (!room.getCharactersInRoom().isEmpty()) { //Checkts if the room are empty
            int count = 1; // The number the character are at in the List + 1
            for (CharacterEntity ce : room.getCharactersInRoom()) { //Prints the name of each charackter in the room
                System.out.println((count++) + ce.getName());
            }
        } else {
            System.out.println("There is no one here!!");
        }
    }

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
