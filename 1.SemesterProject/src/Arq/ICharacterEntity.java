package Arq;

/**
 * CharacterEntity interface
 *
 * This interface describes that all characters in the game have
 *
 */
public interface ICharacterEntity {

    int getBaseHealth();
    int getBaseAttack();
    /**
     * Called to get the name
     *
     * @return the name of the characterEntity as a String
     */
    String getName();

    /**
     * Called to get the health
     *
     * @return the health of the charaterEntity as a integer
     */
    int getHealth();

    /**
     * Called to get the armor
     *
     * @return the armor of the characterEntity as a integer
     */
    int getArmor();

    /**
     * Called to get the attack
     *
     * @return the attack of the characterEntity as a integer
     */
    int getAttack();

    /**
     * Called to get the level
     *
     * @return the level of the characterEntity as a integer
     */
    int getLevel();

    /**
     * Called to get the max health
     *
     * @return the max health of the characterEntity as a integer
     */
    int getMaxHealth();

    /**
     * Called to get the id. The id is use to decide which subtype has to
     * initialized when the game are loaded
     *
     * @return the id of the characterEntity as a integer
     */
    int getId();

}
