package Domain;

import Domain.Entity.Player;
import java.util.List;

/**
 * Database interface
 *
 * This interface describes what a database can provide.
 *
 */
public interface Database {

    /**
     * Called to get to scoreTable
     *
     * @return Returns a sorted list with HighscoreWrappers, with the biggest
     * score at index 0.
     */
    List<HighscoreWrapper> getHighscore();

    /**
     * Called to load a game
     *
     * @param newGame The newGame is used determine what to load. If true it
     * loads a new game, if false it loads a existing game.
     *
     * @return Returns a game. with all the informations from the file.
     */
    Game loadGame(boolean newGame);

    /**
     * Called to save the state of the game
     */
    void saveGame();

    /**
     * Called to save the high scores of the game
     *
     * @param list A sorted list, with the biggest score first, that has to be
     * saved
     */
    void saveScoreTable(List<HighscoreWrapper> list);

}
