package Arq;

import java.util.List;
import java.util.Map;

/**
 * Database interface
 *
 * This interface describes what a database can provide.
 *
 */
public interface IData {

    /**
     * Called to get a NPC this this name
     * @param name of the NPC
     * @return the NPC
     */
    ICharacterEntity getNPC(String name);
    
    /**
     * Called to get a map of all the items
     * @return a map with IItems
     */
    Map<String, IItem> getItem();
    
    /**
     * Called to get to scoreTable
     *
     * @return Returns a sorted list with HighscoreWrappers, with the biggest
     * score at index 0.
     */
    List<IHighscoreWrapper> getHighscore();

    /**
     * Called to load a game
     *
     * @param newGame The newGame is used determine what to load. If true it
     * loads a new game, if false it loads a existing game.
     *
     * @return Returns a game. with all the informations from the file.
     */
    IDomainGame loadGame(boolean newGame);

    /**
     * Called to save the state of the game
     */
    void saveGame(IDomainGame game);

    /**
     * Called to save the high scores of the game
     *
     * @param list A sorted list, with the biggest score first, that has to be
     * saved
     */
    void saveScoreTable(List<IHighscoreWrapper> list);
}
