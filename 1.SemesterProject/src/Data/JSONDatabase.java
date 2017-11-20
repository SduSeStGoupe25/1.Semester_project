package Data;

import Arq.IArmor;
import Arq.ICharacterEntity;
import Arq.IConsumeable;
import Arq.IData;
import Arq.IDomainGame;
import Arq.IHighscoreWrapper;
import Arq.IItem;
import Arq.IKey;
import Arq.IMoveableNPC;
import Arq.INPC;
import Arq.INormalItem;
import Arq.IPlayer;
import Arq.IShopkeeper;
import Arq.IWeapon;
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
public class JSONDatabase implements IData {

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
    public List<IHighscoreWrapper> getHighscore() {
        //Instanciates a list to return
        List<IHighscoreWrapper> highscoreTable = new ArrayList<>();

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
    public IDomainGame loadGame(boolean newGame) {
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
                    .registerTypeHierarchyAdapter(ICharacterEntity.class, new CharacterEntityDeserializer()) // use read CharacterEntity correct
                    .registerTypeHierarchyAdapter(IItem.class, new ItemDeserializer()) // use read item correct
                    .create();

            //Creates a game instance from the json string
            IDomainGame game = gson.fromJson(jsonReader, IDomainGame.class);

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
    private class CharacterEntityDeserializer implements JsonDeserializer<ICharacterEntity> {

        @Override
        public ICharacterEntity deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {

            Gson g = new Gson();

            //Checks if type is player
            if (type.equals(IPlayer.class)) {
                //Returns a instance of player
                return (IPlayer) g.fromJson(je, IPlayer.class);
            }

            //Creates an NPC so it is possible to access it id
            INPC ce = (INPC) g.fromJson(je, INPC.class);

            //Determines type of CharacterEntity ce is
            switch (ce.getId()) {
                case 1:
                    //Returns instance of NPC
                    return ce;
                case 3:
                    //Returns instance of Shopkeeper
                    return (IShopkeeper) g.fromJson(je, IShopkeeper.class);
                case 4:
                    //Returns instance of MoveableNPC
                    return (IMoveableNPC) g.fromJson(je, IMoveableNPC.class);
            }
            return null;
        }

    }

    /**
     * Custom deserializer for Item. Is used to deserialize
     * Item correctly to right type.
     */
    private class ItemDeserializer implements JsonDeserializer<IItem> {

        @Override
        public IItem deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            Gson g = new Gson();
            
            int id = ((ICharacterEntity)g.fromJson(je, ICharacterEntity.class)).getId();

            //Determinds what type of Item the JsonElement is
            switch (id) {
                case 0:
                    //Returns instance of Armor
                    return (IArmor) g.fromJson(je, IArmor.class);
                case 1:
                    //Returns instance of Consumeable
                    return (IConsumeable) g.fromJson(je, IConsumeable.class);
                case 2:
                    //Returns instance of Key
                    return (IKey) g.fromJson(je, IKey.class);
                case 3:
                    //Returns instance of NormalItem
                    return (INormalItem) g.fromJson(je, INormalItem.class);
                case 4:
                    //Returns instance of Weapon
                    return (IWeapon) g.fromJson(je, IWeapon.class);
            }
            return null;
        }

    }

    @Override
    public void saveScoreTable(List<IHighscoreWrapper> list) {
        //Saves the highScoreTable 
        saveData(list, fileHigh);
    }

    @Override
    public void saveGame(IDomainGame game) {
        //Saves the game
        saveData(game, fileSave);

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
