package Domain;

import Acq.ICharacterEntity;
import Acq.ICombatResponse;

/**
 * CombatResponse class
 */
class CombatResponse implements ICombatResponse {

    /**
     * The value of the player attack
     */
    private int playerAttack;

    /**
     * The value of the opponentAttack
     */
    private int opponentAttack;

    /**
     * Whether the combat is running or not
     */
    private boolean isRunning;

    /**
     * The opponent
     */
    private ICharacterEntity opponent;

    /**
     * Constructor
     *
     * @param playerAttack {@link #playerAttack}
     * @param opponentAttack {@link #opponentAttack}
     * @param isRunning {@link #isRunning}
     * @param ce {@link #opponent}
     */
    CombatResponse(int playerAttack, int opponentAttack, boolean isRunning, ICharacterEntity ce) {
        this.playerAttack = playerAttack;
        this.opponentAttack = opponentAttack;
        this.isRunning = isRunning;
        opponent = ce;
    }

    @Override
    public int getPlayerAttack() {
        return playerAttack;
    }

    /**
     * Called to set the player attack
     *
     * @param playerAttack the player attack value
     */
    void setPlayerAttack(int playerAttack) {
        this.playerAttack = playerAttack;
    }

    @Override
    public int getOpponentAttack() {
        return opponentAttack;
    }

    /**
     * Called to set the opponentAttack
     *
     * @param opponentAttack the opponent attack value
     */
    void setOpponentAttack(int opponentAttack) {
        this.opponentAttack = opponentAttack;
    }

    @Override
    public ICharacterEntity getOpponent() {
        return opponent;
    }

    /**
     * Called to set whether combat is running or not
     *
     * @param isRunning whether combat are running
     */
    void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public boolean getIsRunning() {
        return isRunning;
    }
}
