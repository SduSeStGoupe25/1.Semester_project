package Data;

import Arq.ICharacterEntity;
import Arq.IData;
import Arq.IDomainGame;
import Arq.IExit;
import Arq.IHighscoreWrapper;
import Arq.IInventory;
import Arq.IItem;
import Arq.IQuest;
import Arq.IRoom;
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
import java.util.Collection;
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
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //System.out.println(highscoreTable.get(0));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
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
                    .registerTypeHierarchyAdapter(IInventory.class, new InventoryDeserializer())
                    .registerTypeHierarchyAdapter(IRoom.class, new RoomDeserializer())
                    .create();

            //Creates a game instance from the json string
            DataGame game = gson.fromJson(jsonReader, DataGame.class);
            System.out.println("GAME IN LOAD");
            System.out.println(game.toString());
            System.out.println(game == null);

            System.out.println("Done loading");

            //Returns game
            System.out.println("GAME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!GAME");
            System.out.println(game);
            return game;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Returns null if game not was instantiated
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!NULL");
        return null;
    }

    private class RoomDeserializer implements JsonDeserializer<IRoom> {

        @Override
        public IRoom deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            Gson g = new GsonBuilder()
                    .registerTypeHierarchyAdapter(ICharacterEntity.class, new CharacterEntityDeserializer()) // use read CharacterEntity correct
                    .registerTypeHierarchyAdapter(IItem.class, new ItemDeserializer()) // use read item correct
                    .registerTypeHierarchyAdapter(IInventory.class, new InventoryDeserializer())
                    .registerTypeHierarchyAdapter(IExit.class, new ExitDeserializer())
                    .create();
            return (DataRoom) g.fromJson(je, DataRoom.class);
        }

    }

    private class InventoryDeserializer implements JsonDeserializer<IInventory> {

        @Override
        public IInventory deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            Gson g = new GsonBuilder()
                    .registerTypeHierarchyAdapter(IItem.class, new ItemDeserializer()) // use read item correct
                    .create();
            return (DataInventory) g.fromJson(je, DataInventory.class);
        }

    }

    private class QuestDeserializer implements JsonDeserializer<IQuest> {

        @Override
        public IQuest deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            Gson g = new GsonBuilder()
                    .registerTypeHierarchyAdapter(IItem.class, new ItemDeserializer()) // use read item correct
                    .registerTypeHierarchyAdapter(IInventory.class, new InventoryDeserializer())
                    .create();
            return (DataQuest) g.fromJson(je, DataQuest.class);
        }

    }

    private class ExitDeserializer implements JsonDeserializer<IExit> {

        @Override
        public IExit deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            Gson g = new GsonBuilder()
                    .create();
            return (DataExit) g.fromJson(je, DataExit.class);
        }

    }

    /**
     * Custom deserializer for CharacterEntity. Is used to deserialize
     * CharacterEntites correctly to right type.
     */
    private class CharacterEntityDeserializer implements JsonDeserializer<ICharacterEntity> {

        @Override
        public ICharacterEntity deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            Gson g = new GsonBuilder()
                    .registerTypeHierarchyAdapter(IItem.class, new ItemDeserializer()) // use read item correct
                    .registerTypeHierarchyAdapter(IInventory.class, new InventoryDeserializer())
                    .registerTypeHierarchyAdapter(IQuest.class, new QuestDeserializer())
                    .create();
            //Checks if type is player
            if (type.equals(DataPlayer.class)) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                //Returns a instance of player
                System.out.println(g.fromJson(je, DataPlayer.class));
                return (DataPlayer) g.fromJson(je, DataPlayer.class);
            }

            //Creates an NPC so it is possible to access it id
            DataNPC ce = (DataNPC) g.fromJson(je, DataNPC.class);

            //Determines type of CharacterEntity ce is
            switch (ce.getId()) {
                case 1:
                    //Returns instance of NPC
                    return ce;
                case 3:
                    //Returns instance of Shopkeeper
                    return (DataShopkeeper) g.fromJson(je, DataShopkeeper.class);
                case 4:
                    //Returns instance of MoveableNPC
                    return (DataMoveableNPC) g.fromJson(je, DataMoveableNPC.class);
            }
            return null;
        }

    }

    /**
     * Custom deserializer for Item. Is used to deserialize Item correctly to
     * right type.
     */
    private class ItemDeserializer implements JsonDeserializer<IItem> {

        private class tmpItem extends DataItem {

            public tmpItem(String name, int sellValue, int count, int MAX_COUNT, int id) {
                super(name, sellValue, count, MAX_COUNT, id);
            }

        }

        @Override
        public IItem deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            Gson g = new Gson();

            int id = g.fromJson(je, tmpItem.class).getId();

            //Determinds what type of Item the JsonElement is
            switch (id) {
                case 0:
                    //Returns instance of Armor
                    return (DataArmor) g.fromJson(je, DataArmor.class);
                case 1:
                    //Returns instance of Consumeable
                    return (DataConsumeable) g.fromJson(je, DataConsumeable.class);
                case 2:
                    //Returns instance of Key
                    return (DataKey) g.fromJson(je, DataKey.class);
                case 3:
                    //Returns instance of NormalItem
                    return (DataNormalItem) g.fromJson(je, DataNormalItem.class);
                case 4:
                    //Returns instance of Weapon
                    return (DataWeapon) g.fromJson(je, DataWeapon.class);
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
     * Called to save an generic type to a given file
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
     * Called to check if the files that are needed exists, otherwise creates
     * them
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
