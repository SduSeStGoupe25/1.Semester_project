/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Arq.IArmor;
import Arq.ICharacterEntity;
import Arq.IConsumeable;
import Arq.IDomainGame;
import Arq.IExit;
import Arq.IHighscoreWrapper;
import Arq.IInventory;
import Arq.IItem;
import Arq.IKey;
import Arq.IMoveableNPC;
import Arq.INPC;
import Arq.IPlayer;
import Arq.IQuest;
import Arq.IRoom;
import Arq.IShopkeeper;
import Arq.IWeapon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author madsd
 */
class GameMapper {

    DomainGame map(IDomainGame toBeMapped) {
        if (toBeMapped != null) {
            DomainGame g = DomainGame.getInstance();
            g.setCurrentRoom(toBeMapped.getCurrentRoom());
            g.setRoomMap(mapR(toBeMapped.getRoomMap()));
           // g.setItemNames(toBeMapped.getItemNames());
            g.setPlayer(map(toBeMapped.getPlayer()));
            g.makeCombat();
            return g;
        } else {
            return null;
        }
    }

    ArrayList<IHighscoreWrapper> mapScore(List<IHighscoreWrapper> toBeMapped) {
        ArrayList<IHighscoreWrapper> list = new ArrayList<>();
        for (IHighscoreWrapper hs : toBeMapped) {
            HighscoreWrapper h = new HighscoreWrapper(hs.getScore(), hs.getName());
            list.add(h);
        }
        return list;
    }

    private Room map(IRoom toBeMapped) {
        Room r = new Room(
                toBeMapped.getName(),
                toBeMapped.getShortDescription());
        r.setExits(mapT(((IRoom) toBeMapped).getExits()));
        r.setCharactersInRoom(map(((IRoom) toBeMapped).getCharactersInRoom()));
        r.setItemList(map(((IRoom) toBeMapped).getItemList()));
        r.setAllowesMonsters(map(((IRoom) toBeMapped).getAllowesMonsters()));
        return r;
    }

    private Exit map(IExit toBeMapped) {
        if (toBeMapped.getLocked()) {
            return new Exit(
                    toBeMapped.getName1(),
                    toBeMapped.getName2(),
                    true,
                    toBeMapped.getlockID());
        } else {
            return new Exit(
                    toBeMapped.getName1(),
                    toBeMapped.getName2());
        }
    }

    Item map(IItem toBeMapped) {
        if (toBeMapped != null) {
            switch (toBeMapped.getId()) {
                case 0:
                    return new Armor(
                            toBeMapped.getName(),
                            toBeMapped.getSellValue(),
                            toBeMapped.getCount(),
                            ((IArmor) toBeMapped).getBaseArmor(),
                            ((IArmor) toBeMapped).getItemLevel());
                case 1:
                    return new Consumeable(
                            toBeMapped.getName(),
                            toBeMapped.getSellValue(),
                            toBeMapped.getCount(),
                            ((IConsumeable) toBeMapped).getUseValue(),
                            ((IConsumeable) toBeMapped).getHungerValue());
                case 2:
                    return new Key(
                            toBeMapped.getName(),
                            toBeMapped.getSellValue(),
                            toBeMapped.getCount(),
                            ((IKey) toBeMapped).getKeyID());
                case 3:
                    return new NormalItem(
                            toBeMapped.getName(),
                            toBeMapped.getSellValue(),
                            toBeMapped.getCount());
                case 4:
                    return new Weapon(
                            toBeMapped.getName(),
                            toBeMapped.getSellValue(),
                            toBeMapped.getCount(),
                            ((IWeapon) toBeMapped).getBaseAttack(),
                            ((IWeapon) toBeMapped).getItemLevel());
            }
        }

        return null;
    }

    private Player map(IPlayer toBeMapped) {
        Player p = new Player(
                toBeMapped.getName(),
                toBeMapped.getBaseHealth(),
                toBeMapped.getArmor(),
                toBeMapped.getBaseAttack(),
                toBeMapped.getLevel(),
                ((IPlayer) toBeMapped).getGold(),
                ((IPlayer) toBeMapped).getExp());

        p.setExpToLevelUp(((IPlayer) toBeMapped).getExpToLevelUp());
        p.setHunger(((IPlayer) toBeMapped).getHunger());
        p.setMaxHunger(((IPlayer) toBeMapped).getMaxHunger());
        //p.setMaxHealth(((IPlayer) toBeMapped).getMaxHealth());
        p.setHealth(((IPlayer) toBeMapped).getHealth());
        p.setItemInventory(map(((IPlayer) toBeMapped).getItemInventory()));
        p.setEquipableInventory(map(((IPlayer) toBeMapped).getEquipableInventory()));
        p.setMainQuest(map(((IPlayer) toBeMapped).getMainQuest()));
        p.setSideQuest(map(((IPlayer) toBeMapped).getSideQuest()));
        p.setMaxHunger(((IPlayer) toBeMapped).getMaxHunger());
        p.setQuestsCompleted(((IPlayer) toBeMapped).getQuestsCompleted());

        p.setMaxHunger(100);

        return p;
    }

    private CharacterEntity map(ICharacterEntity toBeMapped) {
        System.out.println(toBeMapped.getName());
        switch (toBeMapped.getId()) {
            case 1:
                return new NPC(
                        toBeMapped.getName(),
                        toBeMapped.getHealth(),
                        toBeMapped.getArmor(),
                        toBeMapped.getAttack(),
                        toBeMapped.getLevel(),
                        ((INPC) toBeMapped).getExpDrop(),
                        ((INPC) toBeMapped).getTalk());
            case 2:
                break;
            case 3:
                Shopkeeper s = new Shopkeeper(
                        toBeMapped.getName(),
                        toBeMapped.getHealth(),
                        toBeMapped.getArmor(),
                        toBeMapped.getAttack(),
                        toBeMapped.getLevel(),
                        ((IShopkeeper) toBeMapped).getExpDrop(),
                        ((IShopkeeper) toBeMapped).getTalk());
                s.setItemsToSell(map(((IShopkeeper) toBeMapped).getItemsToSell()));
                return s;
            case 4:
                return new MoveableNPC(
                        toBeMapped.getName(),
                        toBeMapped.getHealth(),
                        toBeMapped.getArmor(),
                        toBeMapped.getAttack(),
                        toBeMapped.getLevel(),
                        ((IMoveableNPC) toBeMapped).getExpDrop(),
                        ((IMoveableNPC) toBeMapped).getTalk(),
                        ((IMoveableNPC) toBeMapped).getAllowedRooms());
        }
        return null;
    }

    private Quest map(IQuest toBeMapped) {
        return new Quest(
                toBeMapped.getName(),
                toBeMapped.getDescription(),
                toBeMapped.getGold(),
                toBeMapped.getExp(),
                map(toBeMapped.getItems()),
                toBeMapped.getGiver());
    }

    private Inventory map(IInventory toBeMapped) {
        Inventory i = new Inventory(toBeMapped.getMaxSlots());
        if (!toBeMapped.getInventory().isEmpty()) {
            i.setInventory(map(toBeMapped.getInventory()));
        }
        return i;
    }

    private ArrayList<ICharacterEntity> map(List<ICharacterEntity> toBeMapped) {
        ArrayList<ICharacterEntity> l = new ArrayList<>();
        if (!toBeMapped.isEmpty()) {
            for (ICharacterEntity c : toBeMapped) {
                l.add(map(c));
            }
        }
        return l;
    }

    private ArrayList<IItem> map(ArrayList<IItem> toBeMapped) {
        ArrayList<IItem> l = new ArrayList<>();
        if (!toBeMapped.isEmpty()) {
            for (IItem iItem : toBeMapped) {
                l.add(map(iItem));
            }
        }
        return l;
    }

    private LinkedHashMap<Integer, IQuest> map(LinkedHashMap<Integer, IQuest> toBeMapped) {
        LinkedHashMap<Integer, IQuest> m = new LinkedHashMap<>();
        if (!toBeMapped.isEmpty()) {
            for (int i : toBeMapped.keySet()) {
                m.put(i, map(toBeMapped.get(i)));
            }
        }
        return m;
    }

    private HashMap<String, IQuest> map(HashMap<String, IQuest> toBeMapped) {
        HashMap<String, IQuest> m = new LinkedHashMap<>();
        System.out.println(toBeMapped);
        if (toBeMapped != null && !toBeMapped.isEmpty()) {
            for (String s : toBeMapped.keySet()) {
                m.put(s, map(toBeMapped.get(s)));
            }
        }
        return m;
    }

    HashMap<String, IItem> map(Map<String, IItem> toBeMapped) {
        HashMap<String, IItem> m = new LinkedHashMap<>();
        if (toBeMapped != null && !toBeMapped.isEmpty()) {
            for (String s : toBeMapped.keySet()) {
                m.put(s, map(toBeMapped.get(s)));
            }
        }
        return m;
    }

    private HashMap<String, IExit> mapT(Map<String, IExit> toBeMapped) {
        HashMap<String, IExit> m = new LinkedHashMap<>();
        if (!toBeMapped.isEmpty()) {
            for (String s : toBeMapped.keySet()) {
                m.put(s, map(toBeMapped.get(s)));
            }
        }
        return m;
    }

    private HashMap<String, IRoom> mapR(Map<String, IRoom> toBeMapped) {
        HashMap<String, IRoom> m = new LinkedHashMap<>();
        if (!toBeMapped.isEmpty()) {
            for (String s : toBeMapped.keySet()) {
                m.put(s, map(toBeMapped.get(s)));
            }
        }
        return m;
    }

    private HashSet<String> map(Set<String> toBeMapped) {
        HashSet<String> s = new HashSet<>();
        if (!toBeMapped.isEmpty()) {
            for (String st : toBeMapped) {
                s.add(st);
            }
        }
        return s;
    }
//    private <K, V> Map<K, V> map(Map<K, V> toBeMapped) {
//        Map<K, V> m;
//        if (toBeMapped.getClass().equals(HashMap.class)) {
//            m = new HashMap<>();
//        } else if (toBeMapped.getClass().equals(LinkedHashMap.class)) {
//            m = new LinkedHashMap<>();
//        } else {
//            return null;
//        }
//
//        for (K k : toBeMapped.keySet()) {
//            m.put(k, map(toBeMapped.get(k)));
//        }
//        return m;
//    }
}
