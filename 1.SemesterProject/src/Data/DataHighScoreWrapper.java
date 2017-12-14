package Data;

import Acq.IHighscoreWrapper;

/**
 * DataHighScoreWrapper class
 */
public class DataHighScoreWrapper implements IHighscoreWrapper {

    private int score;
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore() {
        return score;
    }
}
