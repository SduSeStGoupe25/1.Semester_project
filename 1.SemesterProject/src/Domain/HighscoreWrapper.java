package Domain;

import Acq.IHighscoreWrapper;

/**
 * HighscoreWrapper class
 */
class HighscoreWrapper implements Comparable<HighscoreWrapper>, IHighscoreWrapper {

    private int score;
    private String name;

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
    public String toString () { 
        return this.name + "\t" + this.score + "\n";
    }
}
