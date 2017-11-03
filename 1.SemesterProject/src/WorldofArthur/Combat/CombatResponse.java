/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldofArthur.Combat;

import WorldofArthur.Entity.CharacterEntity;
import WorldofArthur.Entity.Player;

/**
 *
 * @author irus
 */
public class CombatResponse {
    private int playerAttack;
    private int opponentAttack;
    private Player player;
    private CharacterEntity opponent;

    public CombatResponse(int playerAttack, int opponentAttack, Player player, CharacterEntity ce) {
        this.playerAttack = playerAttack;
        this.opponentAttack = opponentAttack;
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
    
    
}
