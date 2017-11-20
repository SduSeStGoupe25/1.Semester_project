/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arq;

import java.util.List;

/**
 *
 * @author madsd
 */
public interface IDomainData {
    void addNewScore(String name, int score);
    void saveGame();
    IDomainGame loadGame(boolean newGame);
    List<IHighscoreWrapper> getHighScoreTable();
    void injectData(IData data);
}
