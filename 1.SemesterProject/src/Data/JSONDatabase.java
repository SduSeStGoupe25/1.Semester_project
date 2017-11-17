/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Domain.Database;
import Domain.Entity.Player;
import Domain.Game;
import Domain.HighscoreWrapper;
import Domain.Room;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor Gram
 */
public class JSONDatabase implements Database {

    File fileHigh = new File("highScore.json");
    File fileSave = new File("save.json");

    public JSONDatabase() {
        loadDatabase();

    }

    @Override
    public List<HighscoreWrapper> getHighscore() {
        FileInputStream fIn = null;
        try {
            List<HighscoreWrapper> highscoreTable = new ArrayList<>();
            fIn = new FileInputStream(fileHigh);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = ""; //Holds the text
            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow;
            }
            myReader.close();
            Gson gson = new Gson();
            System.out.println("GSON");
            System.out.println(gson);

            highscoreTable = gson.fromJson(aBuffer, new TypeToken<List<HighscoreWrapper>>() {
            }.getType());
            System.out.println("int2");
            System.out.println(highscoreTable);

            System.out.println("Load highscore done");
            return highscoreTable;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fIn.close();
            } catch (IOException ex) {
                Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    @Override
    public void saveProgress(List<HighscoreWrapper> scoreTable, Player player) {
        ArrayList<HighscoreWrapper> list = new ArrayList<>();
        list.add(new HighscoreWrapper(23, "Bob"));
        list.add(new HighscoreWrapper(2, "Lars"));

        saveHighScore(list);

        System.out.println("HighScore ");
        System.out.println(getHighscore());

        saveGame();
        System.out.println("Game ::::::::::::::::::::");
        System.out.println(player);
    }

    private void saveGame() {

        //Player game = new Player("bob", 0, 0, 0, 0, 0, null, 0);
        
        Map<String, Room> game = Game.getInstance().getRoomMap();
        //Game game = Game.getInstance();

        FileOutputStream fOut = null;
        OutputStreamWriter myOutWriter = null;
        try {

            Gson gson = new Gson();
            System.out.println("HERE::::::::::::::::::");
            //System.out.println(game.toString());
            String json = gson.toJson(game);
            System.out.println("Game in saveGame::::::::::::");
            System.out.println(game);
            fOut = new FileOutputStream(fileSave);
            myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(json);
            System.out.println("Json" + json);
            myOutWriter.close();
            fOut.close();

            System.out.println("Save done Game");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fOut != null) {
                try {
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (myOutWriter != null) {
                try {
                    myOutWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void saveHighScore(List<HighscoreWrapper> list) {
        System.out.println("LIST_____________");
        System.out.println(list);
        FileOutputStream fOut = null;
        OutputStreamWriter myOutWriter = null;
        try {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            fOut = new FileOutputStream(fileHigh);
            myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(json);
            System.out.println("Json" + json);
            myOutWriter.close();
            fOut.close();

            System.out.println("Save done highScore");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fOut.close();
                myOutWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    private void saveHighscore(HighscoreWrapper wrapper) { 
//        List<HighscoreWrapper> list = getHighscore();
//        int count = 0;
//        for (HighscoreWrapper highscoreWrapper : list) {
//            int compareValue = wrapper.compareTo(highscoreWrapper); 
//            switch (compareValue) { 
//                case -1: 
//                    
//                    break;
//                case 0:
//                    break;
//                case 1:
//                    list.add(count, wrapper);
//                    break;
//            }
//            count++;
//        }
//    }
    private void loadDatabase() {
        if (!fileHigh.exists()) {
            try {
                fileHigh.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!fileSave.exists()) {
            try {
                fileSave.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
