package Arq;

import java.util.Map;

/**
 *
 * Interface NPC
 */
public interface INPC extends ICharacterEntity {

    /**
     * Called to get the talk of the NPC
     *
     * @return the talk as a String
     */
    String getTalk();

    /**
     * Called to get the exp the NPC drops when it dies
     *
     * @return the exp drop as a integer
     */
    int getExpDrop();

    /**
     * Called to get a map for names of item and a drop chance
     *
     * @return a map with String (Item name) and Integer (drop chance)
     */
    Map<String, Integer> getItemDropMap();
}
