package Domain;

import Acq.INPC;
import java.util.HashMap;
import java.util.Map;

/**
 * NPC class
 */
class NPC extends CharacterEntity implements INPC {

    /**
     * What the NPC says
     */
    private String talk;

    /**
     * The amount of exp the player gets by killing the NPC
     */
    private int expDrop;

    /**
     * A Map where the key is the name of the item and the value is the amount
     * the NPC drops when killed
     */
    private Map<String, Integer> itemMap = new HashMap<>();

    /**
     * Constructor
     *
     * @param name {@link CharacterEntity#name}
     * @param health {@link CharacterEntity#health}
     * @param armor {@link CharacterEntity#armor}
     * @param attack {@link CharacterEntity#attack}
     * @param level {@link CharacterEntity#level}
     * @param expDrop {@link #expDrop}
     * @param talk {@link #talk}
     * @param itemMap {@link #itemMap}
     * @param hostile {@link CharacterEntity#hostile}
     * @param despawning {@link CharacterEntity#despawning}
     */
    NPC(String name, int health, int armor, int attack, int level, int expDrop, String talk, Map<String, Integer> itemMap, boolean hostile, boolean despawning) {
        this(name, health, armor, attack, level, expDrop, 1, talk, itemMap, hostile, despawning);
    }

    /**
     * Constructor
     *
     * @param name {@link CharacterEntity#name}
     * @param health {@link CharacterEntity#health}
     * @param armor {@link CharacterEntity#armor}
     * @param attack {@link CharacterEntity#attack}
     * @param level {@link CharacterEntity#level}
     * @param expDrop {@link #expDrop}
     * @param id {@link CharacterEntity#id}
     * @param talk {@link #talk}
     * @param itemMap {@link #itemMap}
     * @param hostile {@link CharacterEntity#hostile}
     * @param despawning {@link CharacterEntity#despawning}
     */
    NPC(String name, int health, int armor, int attack, int level, int expDrop, int id, String talk, Map<String, Integer> itemMap, boolean hostile, boolean despawning) {
        super(name, health, armor, attack, level, id, hostile, despawning);
        this.talk = talk;
        this.expDrop = expDrop;
        this.itemMap = itemMap;
    }

    @Override
    public String getTalk() {
        return talk;
    }

    @Override
    public int getExpDrop() {
        return expDrop;
    }

    @Override
    public void onDeath() {
        if (!itemMap.isEmpty()) {
            for (String name : itemMap.keySet()) {
                if ((int) (Math.random() * 100) <= itemMap.get(name)) {
                    DomainGame.getInstance().getCurrentIRoom().getItemList().add(DomainData.getInstance().getItem(name));
                }
            }
        }
        ((Player) DomainGame.getInstance().getPlayer()).addScore(this.expDrop);
    }

    @Override
    public Map<String, Integer> getItemDropMap() {
        return itemMap;
    }
}
