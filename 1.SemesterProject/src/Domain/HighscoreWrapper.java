/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Arq.IHighscoreWrapper;

/**
 *
 * @author Victor Gram
 */
public class HighscoreWrapper implements Comparable<HighscoreWrapper>, IHighscoreWrapper {

    private int score;
    private String name;

    public HighscoreWrapper(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(HighscoreWrapper h) {
        return Integer.compare(score, h.getScore()); 
    }
}
