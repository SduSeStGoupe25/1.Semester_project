package worldofzuul;

import worldofzuul.Command.PutHandler;
import worldofzuul.Entity.CharacterEntity;
import worldofzuul.Entity.Player;

/**
 *
 * This class is responsible for the combat between the player and a opponent
 */
public class Combat {

    private CharacterEntity opponent; //The characterEntity to fight against 
    private Player player; //The player
    private boolean running; //Indicating if the combat is running
    private PutHandler puthandler;

    public Combat(Player player, PutHandler putHandler) {
        this.player = player;
        this.puthandler = putHandler;
    }

    /**
     * This class start the combat against a opponent
     *
     * @param opponent the CharacterEntity to fight
     */
    public void startCombat(CharacterEntity opponent) {
        this.opponent = opponent;
        running = true;
        combatLoop();
    }

    private void combatLoop() {
        while (running) {
            puthandler.printStatsInAttack();

            while (!puthandler.processCommandCombat()) {
            } //Process player command

            if (!running) {
                break; //Break if player flee
            }
            puthandler.printStatsInAttack();
            opponentMove();
        }
    }

    public void heavyAttack() {
        System.out.println("Heavy attack");
    }

    public void lightAttack() {
        System.out.println("Light attack");
    }

    private void opponentMove() {
        System.out.println("OPPONENT MOVE ----------");
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public CharacterEntity getOpponent() {
        return opponent;
    }

}
