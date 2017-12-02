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
import Data.DataHighScoreWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
        GameMapper mapper = new GameMapper();
        ArrayList<IHighscoreWrapper> highList = new ArrayList<>(mapper.mapScore(getHighScoreTable()));

        int count = 0;


        for (IHighscoreWrapper highscoreWrapper : highList) {
            int compareValue = hw.compareTo((HighscoreWrapper) highscoreWrapper);

            if (compareValue == 0 || compareValue == 1) {
                break;
            }
            count++;
        }
        highList.add(count, (IHighscoreWrapper) hw);
        if (highList.size() > 10) {
            highList.subList(10, highList.size()).clear();
        }
        data.saveScoreTable(highList);
    }

    @Override
    public void saveGame() {
        //data.saveGame(DomainGame.getInstance());
        data.saveGame((IDomainGame) DomainGame.getInstance());
    }

    @Override
    public IGame loadGame(boolean newGame) {
        GameMapper g = new GameMapper();

        IGame f = g.map(data.loadGame(newGame));
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
