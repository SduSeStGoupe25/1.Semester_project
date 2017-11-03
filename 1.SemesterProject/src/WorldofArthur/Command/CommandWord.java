package WorldofArthur.Command;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord {
    GO("go"), QUIT("quit"), HELP("help"), TALK("talk"), BUY("buy"), SEARCH("search"), 
    INVENTORY("inventory"), EQUIP("equip"), DROP("drop"), ATTACK("attack"), USE("use"),
    QUEST("quest"), SELL("sell"), LIGHT("light"), HEAVY("heavy"), FLEE("flee"), UNKNOWN("?");
    
    private String commandString;
    
    CommandWord(String commandString) {
        this.commandString = commandString;
    }
    
    public String toString() {
        return commandString;
    }
}
