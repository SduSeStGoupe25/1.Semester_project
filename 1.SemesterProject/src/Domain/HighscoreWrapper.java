package Domain;

import Acq.IHighscoreWrapper;

/**
 * HighscoreWrapper class
 */
class HighscoreWrapper implements Comparable<HighscoreWrapper>, IHighscoreWrapper {

    /**
     * The score
     */
    private int score;

    /**
     * The name of the player
     */
    private String name;

    /**
     * Constructor
     *
     * @param score {@link #score}
     * @param name {@link #name}
     */
    HighscoreWrapper(int score, String name) {
        this.score = score;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(HighscoreWrapper h) {
        return Integer.compare(score, h.getScore());
    }

    @Override
    public String toString() {
        return this.name + "\t" + this.score + "\n";
    }
}
