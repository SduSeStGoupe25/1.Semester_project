package Arq;

/**
 *
 * Interface to send highscores between layers
 */
public interface IHighscoreWrapper {
    /**
     * Called to get the name of the person which got the score
     * @return the name as a String
     */
    String getName();
    
    /**
     * Called to get the score
     * @return the score as a integer
     */
    int getScore();
}
