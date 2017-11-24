/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.IHighscoreWrapper;

/**
 *
 * @author madsd
 */
class DataHighScoreWrapper implements IHighscoreWrapper {

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
