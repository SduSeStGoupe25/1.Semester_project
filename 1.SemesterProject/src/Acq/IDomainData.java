package Acq;

import java.util.List;
import java.util.Map;

/**
 * Interface for the domaindata. It interface are implemented be a class that
 * has contact to the data layer
 *
 */
public interface IDomainData {

    INPC getNPC(String name, int level);
    
    IItem getItem(String name);
    
    Map<String, IItem> getItemMap();
    
    /**
     * Called to add a new score to the scoretable in the data layer
     *
     * @param name of the player
     * @param score the game score
     */
    void addNewScore(String name, int score);

    /**
     * Called to save the current game state
     */
    void saveGame();

    /**
     * Called load the game, either a new game
     *
     * @param newGame a boolean, if true loads a new game if false continues on
     * a previous game
     * @return a new game
     */
    IGame loadGame(boolean newGame);

    /**
     * Called to get the highScoreTable
     * @return a List with IHighscoreWrapper
     */
    List<IHighscoreWrapper> getHighScoreTable();

    /**
     * Called to inject data to instance that implements this interface
     * @param data a instance of IData
     */
    void injectData(IData data);
}
