/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author irus
 */
public class CombatResponse {
    private int playerAttack;
    private int opponentAttack;
    private boolean isRunning;
    private Player player;
    private CharacterEntity opponent;

    public CombatResponse(int playerAttack, int opponentAttack, boolean isRunning, Player player, CharacterEntity ce) {
        this.playerAttack = playerAttack;
        this.opponentAttack = opponentAttack;
        this.isRunning = isRunning;
        this.player = player;
        opponent = ce;
    }

    public int getPlayerAttack() {
        return playerAttack;
    }

    public void setPlayerAttack(int playerAttack) {
        this.playerAttack = playerAttack;
    }

    public int getOpponentAttack() {
        return opponentAttack;
    }

    public void setOpponentAttack(int opponentAttack) {
        this.opponentAttack = opponentAttack;
    }

    public Player getPlayer() {
        return player;
    }

    public CharacterEntity getOpponent() {
        return opponent;
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    
    
}
