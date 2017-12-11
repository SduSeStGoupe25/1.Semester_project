package Acq;

/**
 * CharacterEntity interface
 *
 * This interface describes that all characters in the game have
 *
 */
public interface ICharacterEntity {

    /**
     * Called to see if the character is hostile
     *
     * @return true if hostile else false
     */
    boolean isHostile();

    /**
     * Called to see if the character is despawning
     *
     * @return true if despawning else false
     */
    boolean isDespawning();

    /**
     * Called to get the base health, the heath at level 1
     *
     * @return base health
     */
    int getBaseHealth();

    /**
     * Called to get the base attack, the attack at level 1
     *
     * @return
     */
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
