/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        List<HighscoreWrapper> highscoreTable = new ArrayList<>();

        try (FileReader reader = new FileReader(fileHigh)) {

            JsonReader jsonReader = new JsonReader(reader);
            Gson gson = new Gson();
            highscoreTable = gson.fromJson(jsonReader, ArrayList.class);

            System.out.println("Done loading");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highscoreTable;
    }

    public Game loadGame() {

        try (FileReader reader = new FileReader(fileSave)) {

            JsonReader jsonReader = new JsonReader(reader);
            Gson gson = new GsonBuilder()
                    .registerTypeHierarchyAdapter(CharacterEntity.class, new CharacterEntityCreator())
                    .registerTypeHierarchyAdapter(Item.class, new ItemCreator())
                    .create();

            //Gson gson = gsonBuilder.create();
            Game game = gson.fromJson(jsonReader, Game.class);

            System.out.println("Done loading");

            return game;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private class CharacterEntityCreator implements JsonDeserializer<CharacterEntity> {

        @Override
        public CharacterEntity deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {

            Gson g = new Gson();

            if (type.equals(Player.class)) {
                return (Player) g.fromJson(je, Player.class);
            }

            NPC ce = (NPC) g.fromJson(je, NPC.class);

            switch (ce.getId()) {
                case 1:
                    return ce;

                case 3:
                    return (Shopkeeper) g.fromJson(je, Shopkeeper.class);

                case 4:
                    return (MoveableNPC) g.fromJson(je, MoveableNPC.class);
            }
            return null;
        }

    }

    private class ItemCreator implements JsonDeserializer<Item> {
        
        private class tmpItem extends Item{
            
            public tmpItem(String name, int sellValue, int count, int MAX_COUNT, int id) {
                super(name, sellValue, count, MAX_COUNT, id);
            }
            
        }

        @Override
        public Item deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            System.out.println("Item type " + je);
            Gson g = new Gson();
            tmpItem n = g.fromJson(je, tmpItem.class);
            

            switch (n.getId()) {
                case 0:
                    return (Armor) g.fromJson(je, Armor.class);
                case 1:
                    return (Consumeable) g.fromJson(je, Consumeable.class);
                case 2:
                    return (Key) g.fromJson(je, Key.class);
                case 3:
                    return (NormalItem) g.fromJson(je, NormalItem.class);
                case 4:
                    return (Weapon) g.fromJson(je, Weapon.class);
            }
            return null;
        }

    }

    @Override
    public void saveProgress(List<HighscoreWrapper> scoreTable, Player player) {
        ArrayList<HighscoreWrapper> list = new ArrayList<>();
        list.add(new HighscoreWrapper(23, "Bob"));
        list.add(new HighscoreWrapper(2, "Lars"));

        System.out.println("HighScore List");
        System.out.println(list);

        saveData(list, fileHigh);

        System.out.println("HighScore ");

        saveData(Game.getInstance(), fileSave);

        System.out.println("HighScores");
        System.out.println(getHighscore());

        System.out.println("LOAD GAME__________________");
        System.out.println(loadGame().toString());
    }

    private <E> void saveData(E o, File file) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(o);
        System.out.println(json);

        try (FileWriter writer = new FileWriter(file)) {

            gson.toJson(o, writer);

            System.out.println("Done saving");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private <E> E loadData(E c, File file) {
//        E o = null;
//        try (FileReader reader = new FileReader(file)) {
//
//            JsonReader jsonReader = new JsonReader(reader);
//            Gson gson = new Gson();
//            o = gson.fromJson(jsonReader, c.getClass());
//            //System.out.println(json);
//
//            System.out.println("Done loading");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return o;
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
