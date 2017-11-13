/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Domain.Database;
import Domain.HighscoreWrapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Victor Gram
 */
public class JSONDatabase implements Database {

    File file = new File("save.json");

    public JSONDatabase() {
        loadDatabase();

    }

    @Override
    public List<HighscoreWrapper> getHighscore() {
        List<HighscoreWrapper> highscoreTable = new LinkedList<>();
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader(file);
            JSONObject o = (JSONObject) parser.parse(reader);
            JSONArray array = (JSONArray) o.get("highscoreTable");

            Iterator<Object> iterator = array.iterator();
            while (iterator.hasNext()) {
                HighscoreWrapper wrapper = (HighscoreWrapper) iterator.next();
                highscoreTable.add(new HighscoreWrapper(wrapper.getScore(), wrapper.getName()));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return highscoreTable;
    }

    @Override
    public void saveProgress() {
        

    }
    
    private void saveHighscore(HighscoreWrapper wrapper) { 
        List<HighscoreWrapper> list = getHighscore();
        int count = 0;
        for (HighscoreWrapper highscoreWrapper : list) {
            int compareValue = wrapper.compareTo(highscoreWrapper); 
            switch (compareValue) { 
                case -1: 
                    
                    break;
                case 0:
                    break;
                case 1:
                    list.add(count, wrapper);
                    break;
            }
            count++;
        }
    }

    private void loadDatabase() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {

        }
    }

}
