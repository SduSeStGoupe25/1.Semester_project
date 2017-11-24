/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Arq.IData;
import Arq.IDomainData;
import Arq.IDomainGame;
import Arq.IGame;
import Arq.IHighscoreWrapper;
import java.util.List;

/**
 *
 * @author madsd
 */
public class DomainData implements IDomainData {

    private IData data;

    @Override
    public void addNewScore(String name, int score) {
        HighscoreWrapper hw = new HighscoreWrapper(score, name);
        List<IHighscoreWrapper> highList = getHighScoreTable();

        int count = 0;
        for (IHighscoreWrapper highscoreWrapper : highList) {
            int compareValue = hw.compareTo((HighscoreWrapper)highscoreWrapper);

            if (compareValue == 0 || compareValue == 1) {
                break;
            }
            count++;
        }
        highList.add(count, (IHighscoreWrapper)hw);
        highList.subList(10, highList.size()).clear();
        data.saveScoreTable(highList);
    }

    @Override
    public void saveGame() {
        //data.saveGame(DomainGame.getInstance());
        data.saveGame((IDomainGame)DomainGame.getInstance());
    }

    @Override
    public IGame loadGame(boolean newGame) {
        GameMapper g = new GameMapper();
        
        System.out.println("LOAD GAME!!!!!!!!!!!!!!!!!!!!!!!!!!!!11111");
        IGame f = g.map(data.loadGame(newGame));
        System.out.println("--------------------------");
        System.out.println(f);
        System.out.println("TEST MAP OVER-----------------------");
    return f;
        //return DomainGame.getInstance().initialize(data.loadGame(newGame));
    }

    @Override
    public List<IHighscoreWrapper> getHighScoreTable() {
        return data.getHighscore();
    }

    @Override
    public void injectData(IData data) {
        this.data = data;
    }

}
