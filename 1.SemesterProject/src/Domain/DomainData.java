package Domain;

import Acq.IData;
import Acq.IDomainData;
import Acq.IDomainGame;
import Acq.IGame;
import Acq.IHighscoreWrapper;
import Acq.IItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DomainData class
 */
public class DomainData implements IDomainData {

    /**
     * Instance of IData
     */
    private IData data;

    /**
     * GameMapper to map
     */
    private final GameMapper mapper = new GameMapper();

    /**
     * Instance of DomainData
     */
    private static DomainData instance;

    /**
     * Private Constructor, the class are instantiated using
     * {@link #getInstance()}
     */
    private DomainData() {
    }

    /**
     * Called to get the instance of DomainData
     *
     * @return a instance of domainData
     */
    public static DomainData getInstance() {
        if (instance == null) {
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
        return mapper.map(data.getItem().get(name.toLowerCase()));
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
