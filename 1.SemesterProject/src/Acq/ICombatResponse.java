package Acq;

/**
 *
 * Interface for combatResponse
 */
public interface ICombatResponse {

    /**
     * Called to get the amount of damage the player has applied
     *
     * @return damage applied
     */
    int getPlayerAttack();

    /**
     * Called to get the amount of damage the opponent has applied
     *
     * @return damage applied
     */
    int getOpponentAttack();

    /**
     * Called to see if combat is running
     *
     * @return true if combat is running, else false
     */
    boolean getIsRunning();

    /**
     * Called to get the opponent
     *
     * @return the opponent
     */
    ICharacterEntity getOpponent();
}
