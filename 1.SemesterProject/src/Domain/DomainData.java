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
import Arq.IItem;
import Arq.INPC;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author madsd
 */
public class DomainData implements IDomainData {

    private IData data;
    private final GameMapper mapper = new GameMapper();
    
    private static DomainData instance;
    
    private DomainData() {
    }
    
    public static DomainData getInstance(){
        if(instance == null){
            instance = new DomainData();
        } 
        return instance;
    }
    

    @Override
    public void addNewScore(String name, int score) {
        HighscoreWrapper hw = new HighscoreWrapper(score, name);
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
        IGame f = mapper.map(data.loadGame(newGame));
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

    @Override
    public IItem getItem(String name) {
        
        return mapper.map(data.getItem().get(name));
    }

    @Override
    public Map<String, IItem> getItemMap() {
        return mapper.map(data.getItem());
    }

    @Override
    public NPC getNPC(String name, int level) {
        NPC npc = (NPC) mapper.map(data.getNPC(name.toLowerCase()));
        npc.setLevel(level);
        npc.setStatsToMax();
        return npc;
    }

}
