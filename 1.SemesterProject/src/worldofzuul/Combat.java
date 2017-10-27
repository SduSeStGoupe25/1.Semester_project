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
    private Room currentRoom; //The current room
    private Player player; //The player
    private boolean running; //Indicating if the combat is running
    private PutHandler puthandler;
    private Game game;

    public Combat(Player player, PutHandler putHandler, Game game) {
        this.player = player;
        this.puthandler = putHandler;
        this.game = game;
    }

    /**
     * This class start the combat against a opponent
     *
     * @param opponent the CharacterEntity to fight
     */
    public void startCombat(CharacterEntity opponent, Room currentRoom) {
        this.opponent = opponent;
        this.currentRoom = currentRoom;
        running = true;
        combatLoop();
    }

    private void combatLoop() {
        while (running) {
            puthandler.printStatsInAttack();

            while (!puthandler.processCommandCombat()) {
            } //Process player command

            if (opponent.getHealth() < 1) {
                currentRoom.removeCharacterFromRoom(opponent);
                running = false;
            }

            if (!running) {
                break; //Break if player flee
            }
            puthandler.printStatsInAttack();
            System.out.println(opponentMove());
        }
        game.moveAllNPC();
    }

    private int attack(int chance, int additionalDamage) {
        if (diceRoll(10) <= chance) {
            int attackValue = player.getAttackValue() * (diceRoll(4) + additionalDamage);
            int damageDealt = 0;
            if (attackValue >= opponent.getArmor()) {
                damageDealt = (attackValue - opponent.getArmor()) + 1;
                opponent.changeHealth(damageDealt * -1);
            }
            return damageDealt;
        }
        return -1;
    }

    public int heavyAttack() {
        return attack(6, 2);
    }

    public int lightAttack() {
        return attack(9, 0);
    }

    private int opponentMove() {
        if (diceRoll(10) <= 9) {
            int attackValue = (opponent.getAttack() * opponent.getLevel()) * diceRoll(4);
            int damageDealt = 0;
            if (attackValue >= player.getArmorValue()) {
                damageDealt = (attackValue - player.getArmorValue()) + 1;
                player.changeHealth(damageDealt * -1);
            }
            return damageDealt;
        }
        return -1;
    }

    private int diceRoll(int sides) {
        return (int) ((Math.random() * sides) + 1);
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
