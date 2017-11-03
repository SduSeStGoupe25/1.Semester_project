package WorldofArthur.Command;

import WorldofArthur.Entity.CharacterEntity;
import WorldofArthur.Entity.NPC;
import WorldofArthur.Entity.Shopkeeper;
import WorldofArthur.Game;
import WorldofArthur.Inventory.Item;
import WorldofArthur.Inventory.Stash;
import WorldofArthur.Room;
import WorldofArthur.Combat.CombatResponse;
import WorldofArthur.Exit;
import WorldofArthur.Inventory.Armor;
import WorldofArthur.Inventory.Consumeable;
import WorldofArthur.Inventory.Weapon;

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

        if (!game.getCombat().isRunning()) { //Checks if combat are startet
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
                    talk(command);
                    break;
                case BUY:
                    buy(command);
                    printInventory();
                    break;
                case SELL:
                    sell(command);
                    printInventory();
                    break;
                case SEARCH:

                    break;
                case INVENTORY:
                    printInventory();
                    break;
                case EQUIP:
                    equip(command);
                    break;
                case DROP:

                    break;
                case ATTACK:
                    attack(command);
                    break;
                case USE:
                    use(command);
                    break;
                case QUEST:
                    quest(command);
                    break;
                default:
                    break;
            }
        }
        return wantToQuit;
    }

    /**
     * This class processes commands when in combat
     *
     * @return true if the command the user gave was valid in combat, and false
     * if not
     */
    public boolean processCommandCombat() {
        Command command = parser.getCommand(); //Gets the next user input

        boolean validCommand = true; //Indicates if the user whats to quit the game

        CommandWord commandWord = command.getCommandWord(); //Gets the commandWord from the command

        switch (commandWord) {
            case USE:
                use(command);
                break;
            case LIGHT:
                printCombat(game.getCombat().combatLoop(0));
                break;
            case HEAVY:
                printCombat(game.getCombat().combatLoop(1));
                break;
            case FLEE:
                printCombat(game.getCombat().combatLoop(2));
                break;
            default:
                System.out.println("Not valid in combat");
                validCommand = false;
                break;
        }
        return validCommand;
    }

    private void buy(Command command) {
        if (game.getCurrentRoom().equals(game.getRoomMap().get("shop"))) {
            if (!command.hasSecondWord()) {
//                printStashList();
                return;
            }
            if (!command.hasThirdWord()) {
                System.out.println("Please enter an amount to buy");
                return;
            }
            int itemType = game.checkItemName(command.getSecondWord());
            if (itemType != -1) {
                if (isNumeric(command.getThirdWord()) && Integer.parseInt(command.getThirdWord()) > 0) {
                    if (game.getCurrentRoom().getCharactersInRoom().size() > 0) {
                        if (game.getCurrentRoom().getCharacterEntity(0) instanceof Shopkeeper) {
                            Shopkeeper sk = (Shopkeeper) game.getCurrentRoom().getCharacterEntity(0);
                            System.out.println(sk.buy(game.createItem(command.getSecondWord(), itemType), Integer.parseInt(command.getThirdWord()), game.getPlayer()));
                        }
                    }
                }
            }
        }
    }

    private void sell(Command command) {
        if (game.getCurrentRoom().equals(game.getRoomMap().get("shop"))) {
            if (!command.hasSecondWord()) {
//                printStashList();
                return;
            }
            if (!command.hasThirdWord()) {
                System.out.println("Please enter an amount to sell");
                return;
            }
            int itemType = game.checkItemName(command.getSecondWord());
            if (itemType != -1) {
                if (isNumeric(command.getThirdWord()) && Integer.parseInt(command.getThirdWord()) > 0) {
                    if (game.getCurrentRoom().getCharactersInRoom().size() > 0) {
                        if (game.getCurrentRoom().getCharacterEntity(0) instanceof Shopkeeper) {
                            Shopkeeper sk = (Shopkeeper) game.getCurrentRoom().getCharacterEntity(0);
                            //Fix this
                            System.out.println(sk.sell(game.createItem(command.getSecondWord(), itemType), Integer.parseInt(command.getThirdWord()), game.getPlayer()));
                        }
                    }
                }
            }
        }
    }

    private void quest(Command command) {
        int itemCount = 0;
        if (!command.hasSecondWord()) {
            System.out.println("Please choose a quest");
            printQuestList();
            return;
        }
        if (game.getPlayer().checkQuest(game.getCurrentRoom())) {
            System.out.println("Quest completed!");
        } else {
            System.out.println("You don't have the required items to complete this quest!");
        }
    }

    private void use(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Please enter the item you want to use");
            this.printEquipables();
            return;
        }
            int itemType = game.checkItemName(command.getSecondWord());
            if (itemType != -1) {
            Item item = game.createItem(command.getSecondWord(), itemType);
            if (game.getPlayer().restoreHp(item)) {
                System.out.println("You restored " + ((Consumeable)item).getUseValue() + " hp!");
                System.out.println("Current hp is " + game.getPlayer().getHealth());
            } else {
                System.out.println("You already have full health!");
            }
            return;
        }
        System.out.println("You don't have a " + command.getSecondWord() + " in your inventory!");
    }

    private void equip(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Which item do you want to equip?");
            this.printEquipables();
            this.printEquipables();
            return;
        }
            int itemType = game.checkItemName(command.getSecondWord());
            if (itemType != -1) {
            Item item = game.createItem(command.getSecondWord(), itemType);
            if (game.getPlayer().equip(item)) {
                System.out.println("You equiped a " + item.getName() + "!");
                return;
            }
        }
        System.out.println("You don't have a " + command.getSecondWord() + " in your inventory!");
    }

    /**
     * This private method checks if the command is valid to start combat, if so
     * starts combat
     *
     * @param command this is the user input about who the target is
     */
    private void talk(Command command) {
        if (!command.hasSecondWord()) { //Checks if the user has specifie a correct target
            printCharactersInRoom(game.getCurrentRoom());
            System.out.println("Talk to who?");
            return; //If there are on target don't talk to anyone
        }
        if (!isNumeric(command.getSecondWord())) {
            System.out.println("Wrong input, try again");
            return;
        }

        int target = Integer.parseInt(command.getSecondWord()); //Gets the targets index

        if (game.getCurrentRoom().getCharactersInRoom().size() >= target) { //Checks if the target index are a part of the List
            if (game.getCurrentRoom().getCharacterEntity(target - 1) instanceof NPC) {
                NPC npc = (NPC) game.getCurrentRoom().getCharacterEntity(target - 1);
                System.out.println(npc.getTalk());
            }
        }
    }

    private void attack(Command command) {
        if (!command.hasSecondWord()) { //Checks if the user has specifie a correct target
            printCharactersInRoom(game.getCurrentRoom());
            System.out.println("Attack who?");
            return; //If there are on target don't attack anyone
        }
        if (!isNumeric(command.getSecondWord())) {
            System.out.println("Wrong input, try again");
            return;
        }

        int target = Integer.parseInt(command.getSecondWord()); //Gets the targets index

        if (game.getCurrentRoom().getCharactersInRoom().size() >= target) { //Checks if the target index are a part of the List
            game.getCombat().startCombat(game.getCurrentRoom().getCharacterEntity(target - 1), game.getCurrentRoom());
            printStatsInAttack();
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

        Exit exit = game.getCurrentRoom().getExit(direction); //Instantiats a room next to the current room

        if (exit == null) { //Chechs if the current room has a exit in this direction
            System.out.println("There is no path here!");
        } else {  //If it has
            Room nextRoom = exit.nextRoom(game.getCurrentRoom());
            game.setCurrentRoom(nextRoom); //Current room is now the nextRoom
            game.getCurrentRoom().spawnEnemies();
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
        System.out.println("Characters in the room: ");
        if (!room.getCharactersInRoom().isEmpty()) { //Checkts if the room are empty
            int count = 1; // The number the character are at in the List + 1
            for (CharacterEntity ce : room.getCharactersInRoom()) { //Prints the name of each charackter in the room
                System.out.println((count++) + "  " + ce.getName() + " " + "lvl. " + ce.getLevel());
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
        System.out.println("Welcome to the world of King Arthur! Game of the year edition.");
        System.out.println("Venture out into this beautiful world, on your quest to free Excalibur from the stone "
                + "and claim your spot as the rightful king.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(game.getCurrentRoom().getLongDescription());
    }

    /**
     * This private method prints a text to help the user play the game
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands(); //Prints all commandWords
    }

    /**
     * This private method prints all items of a specific ItemType i the player
     * inventory
     *
     * @param type the ItemType to print
     */
    private void printEquipables() {
        System.out.println("Your inventory contains these equipables: ");
        for (Item i : game.getPlayer().getItemInventory().getInventory()) {
            if (i instanceof Weapon) {
                System.out.println(i.getName() + " " + ((Weapon) i).getAttackValue() + " " + "(" + ((Weapon) i).getItemLevel() + ")");
            } else if (i instanceof Armor) {
                System.out.println(i.getName() + " " + ((Armor) i).getArmorValue() + " " + "(" + ((Armor) i).getItemLevel() + ")");
            }
        }
    }

    private void printConsumeables() {
        System.out.println("Your inventory contains these consumeables: ");
        for (Item i : game.getPlayer().getItemInventory().getInventory()) {
            if (i instanceof Consumeable) {
                System.out.println(i.getName() + " " + ((Consumeable) i).getUseValue() + " " + i.getCount());
            }
        }
    }

    private void printInventory() {
        if (game.getPlayer().getItemInventory().getInventory().size() <= 0) {
            System.out.println("Your inventory is empty");
        } else {
            System.out.println("Your inventory contains: ");
            for (Item i : game.getPlayer().getItemInventory().getInventory()) {
                System.out.println(i.getName() + "  " + i.getClass().getName() + "  x" + i.getCount());
            }
        }
    }

    /**
     * This class prints the player and opponent stats
     */
    public void printStatsInAttack() {
        System.out.print(game.getPlayer().getName() + " has " + game.getPlayer().getHealth() + " health  |  ");
        System.out.println(game.getCombat().getOpponent().getName() + " has " + game.getCombat().getOpponent().getHealth() + " health");
    }

//    private void printStashList() {
//        for (String i : Stash.getItemMap().keySet()) {
//            System.out.println(i + " " + (Stash.getItemMap().get(i).getSellValue() * 2));
//        }
//    }

    private void printQuestList() {
        System.out.println("Main quest:");
        System.out.println(game.getPlayer().getCurrentMainQuest().toString());
    }

    private void printCombat(CombatResponse c) {
        System.out.println(c.getPlayer().getName() + " has " + c.getPlayer().getHealth() + " health  |  " + c.getOpponent().getName() + " has " + c.getOpponent().getHealth() + " health ");
        System.out.println(c.getPlayerAttack());
        System.out.println(c.getOpponentAttack());
    }
}
