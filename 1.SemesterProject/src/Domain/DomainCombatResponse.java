/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Arq.ICombatResponse;

/**
 *
 * @author irus
 */
public class DomainCombatResponse implements ICombatResponse{
    private int playerAttack;
    private int opponentAttack;
    private boolean isRunning;
    private DomainPlayer player;
    private DomainCharacterEntity opponent;

    public DomainCombatResponse(int playerAttack, int opponentAttack, boolean isRunning, DomainPlayer player, DomainCharacterEntity ce) {
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

    public void setPlayerAttack(int playerAttack) {
        this.playerAttack = playerAttack;
    }

    @Override
    public int getOpponentAttack() {
        return opponentAttack;
    }

    public void setOpponentAttack(int opponentAttack) {
        this.opponentAttack = opponentAttack;
    }

    public DomainPlayer getPlayer() {
        return player;
    }

    @Override
    public DomainCharacterEntity getOpponent() {
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
