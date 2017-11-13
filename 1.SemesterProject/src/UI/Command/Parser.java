package UI.Command;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 *
 * This class is responsible for getting the user input from the console.
 */
public class Parser {

    private CommandWords commands;  //
    private Scanner reader;

    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;
        String word3 = null;

        System.out.print("> ");

        inputLine = reader.nextLine().toLowerCase();

        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
                if (tokenizer.hasNext()) {
                    word3 = tokenizer.next();
                }
            }
        }

        return new Command(commands.getCommandWord(word1), word2, word3);
    }

    public void showCommands() {
        commands.showAll();
    }
}