package Data;

import Domain.Database;
import Domain.Entity.CharacterEntity;
import Domain.Entity.MoveableNPC;
import Domain.Entity.NPC;
import Domain.Entity.Player;
import Domain.Entity.Shopkeeper;
import Domain.Game;
import Domain.HighscoreWrapper;
import Domain.Inventory.Armor;
import Domain.Inventory.Consumeable;
import Domain.Inventory.Item;
import Domain.Inventory.Key;
import Domain.Inventory.NormalItem;
import Domain.Inventory.Weapon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements Database and are used to save and load to json files.
 * The library used is google.gson.
 *
 */
public class JSONDatabase implements Database {

    /**
     * The file the config file are saved in. This file is used to start a new
     * game from.
     */
    private File fileConfigData = new File("SaveFiles/ConfigData.json");

    /**
     * The file the high score table is saved in.
     */
    private File fileHigh = new File("SaveFiles/highScore.json");

    /**
     * The file the game are saved in.
     */
    private File fileSave = new File("SaveFiles/save.json");

    /**
     * Creates a new JSONDatabase instance. And checks files need for the
     * database.
     */
    public JSONDatabase() {
        loadDatabase();
    }

    @Override
    public List<HighscoreWrapper> getHighscore() {
        //Instanciates a list to return
        List<HighscoreWrapper> highscoreTable = new ArrayList<>();

        //Try to read file
        try (FileReader reader = new FileReader(fileHigh)) {

            //Reades the file as a json file
            JsonReader jsonReader = new JsonReader(reader);
            Gson gson = new Gson();

            //Converts the string to a list
            highscoreTable = gson.fromJson(jsonReader, ArrayList.class);

            System.out.println("Done loading");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Returns the high score table
        return highscoreTable;
    }

    @Override
    public Game loadGame(boolean newGame) {
        //Determines what file to load from
        File file;
        if (newGame) { // If newGame
            file = fileConfigData;
        } else {       // else continue game
            file = fileSave;
        }

        //Try to read file
        try (FileReader reader = new FileReader(file)) {

            JsonReader jsonReader = new JsonReader(reader);

            //Creates a Gson instance with to custom typeAdapters.
            Gson gson = new GsonBuilder()
                    .registerTypeHierarchyAdapter(CharacterEntity.class, new CharacterEntityDeserializer()) // use read CharacterEntity correct
                    .registerTypeHierarchyAdapter(Item.class, new ItemDeserializer()) // use read item correct
                    .create();

            //Creates a game instance from the json string
            Game game = gson.fromJson(jsonReader, Game.class);

            System.out.println("Done loading");

            //Returns game
            return game;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Returns null if game not was instantiated
        return null;
    }

    /**
     * Custom deserializer for CharacterEntity. Is used to deserialize
     * CharacterEntites correctly to right type.
     */
    private class CharacterEntityDeserializer implements JsonDeserializer<CharacterEntity> {

        @Override
        public CharacterEntity deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {

            Gson g = new Gson();

            //Checks if type is player
            if (type.equals(Player.class)) {
                //Returns a instance of player
                return (Player) g.fromJson(je, Player.class);
            }

            //Creates an NPC so it is possible to access it id
            NPC ce = (NPC) g.fromJson(je, NPC.class);

            //Determines type of CharacterEntity ce is
            switch (ce.getId()) {
                case 1:
                    //Returns instance of NPC
                    return ce;
                case 3:
                    //Returns instance of Shopkeeper
                    return (Shopkeeper) g.fromJson(je, Shopkeeper.class);
                case 4:
                    //Returns instance of MoveableNPC
                    return (MoveableNPC) g.fromJson(je, MoveableNPC.class);
            }
            return null;
        }

    }

    /**
     * Custom deserializer for Item. Is used to deserialize
     * Item correctly to right type.
     */
    private class ItemDeserializer implements JsonDeserializer<Item> {

        /**
         * Inner class used to determine item type
         */
        private class tmpItem extends Item {

            public tmpItem(String name, int sellValue, int count, int MAX_COUNT, int id) {
                super(name, sellValue, count, MAX_COUNT, id);
            }

        }

        @Override
        public Item deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            Gson g = new Gson();
            //Creates a tmpItem
            tmpItem n = g.fromJson(je, tmpItem.class);

            //Determinds what type of Item the JsonElement is
            switch (n.getId()) {
                case 0:
                    //Returns instance of Armor
                    return (Armor) g.fromJson(je, Armor.class);
                case 1:
                    //Returns instance of Consumeable
                    return (Consumeable) g.fromJson(je, Consumeable.class);
                case 2:
                    //Returns instance of Key
                    return (Key) g.fromJson(je, Key.class);
                case 3:
                    //Returns instance of NormalItem
                    return (NormalItem) g.fromJson(je, NormalItem.class);
                case 4:
                    //Returns instance of Weapon
                    return (Weapon) g.fromJson(je, Weapon.class);
            }
            return null;
        }

    }

    @Override
    public void saveScoreTable(List<HighscoreWrapper> list) {
        //Saves the highScoreTable 
        saveData(list, fileHigh);
    }

    @Override
    public void saveGame() {
        //Saves the game
        saveData(Game.getInstance(), fileSave);

//
//  TEST TEST TEST TEST TEST TEST TEST
//        ArrayList<HighscoreWrapper> list = new ArrayList<>();
//        list.add(new HighscoreWrapper(23, "Bob"));
//        list.add(new HighscoreWrapper(2, "Lars"));
//
//        System.out.println("HighScore List");
//        System.out.println(list);
//
//        saveData(list, fileHigh);
//
//        System.out.println("HighScore ");
//
//        saveData(Game.getInstance(), fileSave);
//
//        System.out.println("HighScores");
//        System.out.println(getHighscore());
//
//        System.out.print("LOAD GAME__________________");
//        System.out.println(loadGame().toString());
//        System.out.println(" DONE");
//  TEST TEST TEST TEST TEST TEST TEST
//
    }

    /**
     *  Called to save an generic type to a given file
     * 
     * @param <E> the type
     * @param o the object to be saved
     * @param file the file o has to be saved in
     */
    private <E> void saveData(E o, File file) {
        Gson gson = new GsonBuilder().create();
        //Creates a json String of o
        String json = gson.toJson(o);

        //Try to write to file
        try (FileWriter writer = new FileWriter(file)) {

            //Writes o to the file
            gson.toJson(o, writer);

            System.out.println("Done saving");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called to check if the files that are needed exists, otherwise creates them
     */
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

        if (!fileConfigData.exists()) {
            try {
                fileConfigData.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(JSONDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
