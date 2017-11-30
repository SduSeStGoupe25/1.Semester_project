package Arq;

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
}
