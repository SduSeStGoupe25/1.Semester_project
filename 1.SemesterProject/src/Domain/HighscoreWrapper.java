/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Acq.IHighscoreWrapper;

/**
 *
 * @author Victor Gram
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
