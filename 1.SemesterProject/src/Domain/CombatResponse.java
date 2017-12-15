package Domain;

import Acq.ICombatResponse;

/**
 * CombatResponse class
 */
class CombatResponse implements ICombatResponse{
    private int playerAttack;
    private int opponentAttack;
    private boolean isRunning;
    private Player player;
    private CharacterEntity opponent;

    CombatResponse(int playerAttack, int opponentAttack, boolean isRunning, Player player, CharacterEntity ce) {
        this.playerAttack = playerAttack;
        this.opponentAttack = opponentAttack;
        this.isRunning = isRunning;
        this.player = player;
        opponent = ce;
    }

    @Override
    public int getPlayerAttack() {
        return playerAttack;
    }

    void setPlayerAttack(int playerAttack) {
        this.playerAttack = playerAttack;
    }

    @Override
    public int getOpponentAttack() {
        return opponentAttack;
    }

    public void setOpponentAttack(int opponentAttack) {
        this.opponentAttack = opponentAttack;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public CharacterEntity getOpponent() {
        return opponent;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public boolean getIsRunning() {
        return isRunning;
    }
}
