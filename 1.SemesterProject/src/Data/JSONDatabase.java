package Data;

import Acq.ICharacterEntity;
import Acq.IData;
import Acq.IDomainGame;
import Acq.IExit;
import Acq.IHighscoreWrapper;
import Acq.IInventory;
import Acq.IItem;
import Acq.IQuest;
import Acq.IRoom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private final File fileConfigData = new File("SaveFiles/ConfigData.json");

    /**
     * The file the high score table is saved in.
     */
    private final File fileHigh = new File("SaveFiles/highScore.json");

    /**
     * The file the game are saved in.
     */
    private final File fileSave = new File("SaveFiles/save.json");

    /**
     * The file the lootTable is in
     */
    private final File fileLoot = new File("SaveFiles/Loot.json");

    /**
     * This file the NPC's is in
     */
    private final File fileNPC = new File("SaveFiles/NPC.json");

    /**
     * Creates a new JSONDatabase instance. And checks files need for the
     * database.
     */
    public JSONDatabase() {
        loadDatabase();
    }

    //////////////////////////
    /// LOAD NPC
    //////////////////////////
    @Override
    public ICharacterEntity getNPC(String name) {
        try (FileReader reader = new FileReader(fileNPC)) {

            //Reades the file as a json file
            JsonReader jsonReader = new JsonReader(reader);
            Gson gson = new GsonBuilder()
                    .registerTypeHierarchyAdapter(ICharacterEntity.class, new CharacterEntityDeserializer()) // use read item correct
                    .create();

            //Gets the type
            Type type = new TypeToken<Map<String, ICharacterEntity>>() {
            }.getType();

            //Converts the json string to a map
            Map<String, ICharacterEntity> obj = gson.fromJson(jsonReader, type);
            //Return npc
            return obj.get(name);
        } catch (IOException e) {
        }
        return null;
    }

    //////////////////////////
    /// LOAD ITEMS
    //////////////////////////
    @Override
    public Map<String, IItem> getItem() {
        try (FileReader reader = new FileReader(fileLoot)) {

            //Reades the file as a json file
            JsonReader jsonReader = new JsonReader(reader);
            Gson gson = new GsonBuilder()
                    .registerTypeHierarchyAdapter(IItem.class, new ItemDeserializer()) // use read item correct
                    .create();

            //Gets the type
            Type type = new TypeToken<Map<String, IItem>>() {
            }.getType();

            //Converts the json string to a map
            Map<String, IItem> obj = gson.fromJson(jsonReader, type);
            //Return map
            return obj;
        } catch (IOException e) {
        }
        return null;
    }

    //////////////////////////
    /// LOAD HIGHSCORE
    //////////////////////////
    @Override
    public List<IHighscoreWrapper> getHighscore() {
        //Instanciates a list to return
        List<IHighscoreWrapper> highscoreTable = new ArrayList<>();

        //Try to read file
        try (FileReader reader = new FileReader(fileHigh)) {

            //Reades the file as a json file
            JsonReader jsonReader = new JsonReader(reader);
            Gson gson = new GsonBuilder()
                    .registerTypeHierarchyAdapter(IHighscoreWrapper.class, new ScoreDeserializer()) // use read CharacterEntity correct
                    .create();

            //Converts the string to a list
            highScoreObject ho = gson.fromJson(jsonReader, highScoreObject.class);
            highscoreTable = ho.list;

            System.out.println("Done loading");
        } catch (IOException e) {
        }

        //Returns the high score table
        return highscoreTable;
    }

    /**
     * Used to deserialize IHighscoreWrapper to DataHighScoreWrapper. Is
     * necessary because IHighscireWrapper can't be instantiated.
     */
    private class ScoreDeserializer implements JsonDeserializer<IHighscoreWrapper> {

        @Override
        public IHighscoreWrapper deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            Gson g = new Gson();
            //Cast the JsonElement to a DataHighScoreWrapper and returns it
            return (DataHighScoreWrapper) g.fromJson(je, DataHighScoreWrapper.class);
        }

    }

    /**
     * Private inner class to avoid exception when saving and loading. The only
     * use it to store the List
     */
    private class highScoreObject {

        List<IHighscoreWrapper> list;

        public highScoreObject(List<IHighscoreWrapper> list) {
            this.list = list;
        }
    }

    //////////////////////////
    /// LOAD GAME
    //////////////////////////
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
                    .registerTypeHierarchyAdapter(ICharacterEntity.class, new CharacterEntityDeserializer()) // used to read CharacterEntity correct
                    .registerTypeHierarchyAdapter(IItem.class, new ItemDeserializer()) // used to read item correct
                    .registerTypeHierarchyAdapter(IInventory.class, new InventoryDeserializer()) // used to read Inventory correct
                    .registerTypeHierarchyAdapter(IRoom.class, new RoomDeserializer()) // used to read Room correct
                    .create();

            //Creates a game instance from the json string
            DataGame game = gson.fromJson(jsonReader, DataGame.class);

            System.out.println("Done loading");

            //Returns game
            return game;
        } catch (IOException e) {
        }

        //Returns null if game not was instantiated
        return null;
    }

    /**
     * Used to deserialize IRoom to DataRoom. Is necessary because IRoom can't
     * be instantiated.
     */
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

    /**
     * Used to deserialize IInventory to DataInventory. Is necessary because
     * IInventory can't be instantiated.
     */
    private class InventoryDeserializer implements JsonDeserializer<IInventory> {

        @Override
        public IInventory deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            Gson g = new GsonBuilder()
                    .registerTypeHierarchyAdapter(IItem.class, new ItemDeserializer()) // use read item correct
                    .create();
            return (DataInventory) g.fromJson(je, DataInventory.class);
        }
    }

    /**
     * Used to deserialize IInventory to DataInventory. Is necessary because
     * IInventory can't be instantiated.
     */
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

    /**
     * Used to deserialize IInventory to DataInventory. Is necessary because
     * IInventory can't be instantiated.
     */
    private class ExitDeserializer implements JsonDeserializer<IExit> {

        @Override
        public IExit deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            Gson g = new GsonBuilder()
                    .create();
            return (DataExit) g.fromJson(je, DataExit.class);
        }
    }

    /**
     * Used to deserialize ICharacterEntity to DataCharacterEntity. Is necessary
     * because ICharacterEntity can't be instantiated.
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
     * Used to deserialize IItem to DataItem. Is necessary because IItem can't
     * be instantiated.
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

    //////////////////////////
    /// SAVE HIGHSCORETABLE
    //////////////////////////
    @Override
    public void saveScoreTable(List<IHighscoreWrapper> list) {
        //Saves the highScoreTable
        saveData(new highScoreObject(list), fileHigh);
    }

    //////////////////////////
    /// SAVE GAME
    //////////////////////////
    @Override
    public void saveGame(IDomainGame game) {
        //Saves the game
        saveData(game, fileSave);
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

        //Try to write to file
        try (FileWriter writer = new FileWriter(file)) {

            //Writes o to the file
            gson.toJson(o, writer);

            System.out.println("Done saving");
        } catch (IOException e) {
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
    }
}
