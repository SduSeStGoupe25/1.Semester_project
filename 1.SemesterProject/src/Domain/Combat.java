package Domain;

import Arq.ICharacterEntity;
import Arq.ICombat;
import Arq.IDomainGame;
import Arq.IPlayer;
import Arq.IRoom;
import Domain.DomainGame;
import Domain.Room;

/**
 *
 * This class is responsible for the combat between the player and a opponent
 */
public class Combat implements ICombat{

    private CharacterEntity opponent; //The characterEntity to fight against 
    private Room currentRoom; //The current room
    private Player player; //The player
    private boolean running; //Indicating if the combat is running
    private DomainGame game;

    public Combat(Player player, DomainGame game) {
        this.player = player;
        this.game = game;
    }

    /**
     * This method starts the combat against a chosen opponent
     *
     * @param opponent the CharacterEntity to fight
     * @param currentRoom the current room the fight takes place in
     */
    public void startCombat(ICharacterEntity opponent, IRoom currentRoom) {
        this.opponent = (CharacterEntity)opponent;
        this.currentRoom = (Room)currentRoom;
        running = true;
        
    }

    /**
     * This method is our combat loop, where all combat mechanics take place.
     */
    public CombatResponse combatLoop(int action) {
        CombatResponse cr = new CombatResponse(0, 0, running, player, opponent);
        switch (action) {
            case 0:
                cr.setPlayerAttack(lightAttack());
                break;
            case 1:
                cr.setPlayerAttack(heavyAttack());
                break;
            case 2:
                running = false;
                break;

        }
        if (opponent.getHealth() < 1) { //If the opponents health is below 1 ( = dead), the opponent gets removed from the room / game.
            opponent.onDeath();
            currentRoom.removeCharacterFromRoom(opponent);
            running = false; //When our opponent has been removed, we set running to false to stop combat.
        }

        if (running) {
            cr.setOpponentAttack(opponentMove()); //The opponents move
            if(player.getHealth()<1){
                player.onDeath();
            }
        } else {
            
            game.moveAllNPC(); //At the end of combat we call moveAllNPC, to make all our moveableNPC's move around
            player.addHunger(-8); //At the end of combat the player's hungervalue decreases
        }
        return cr;
    }

    /**
     * This method calculates the players attack damage with a given attack
     * (light / heavy)
     *
     * @param chance The chance for an attack to successfully deal damage to
     * opponent
     * @param additionalDamage The additional damage an attack does (heavy does
     * +2 additionalDamage, because it has a smaller chance to hit than light)
     * @return If the attack is successful, we return the total damage dealt.
     * Otherwise we return 0 (no damage dealt)
     */
    private int attack(int chance, int additionalDamage) {
        if (diceRoll(10) <= chance) { //A statement where we call a diceRoll, if we hit under or equals the chance to hit, we calculate the damage
            int attackValue = player.getAttackValue() * (diceRoll(4) + additionalDamage); //Here we get the players attackValue, which then gets multiplied by a diceRoll + the additionalDamage from the chosen attack
            int damageDealt = 0;
            if (attackValue >= opponent.getArmor()) { //We check if our attackValue is greater than or equals to the opponents armor. If it is not, we don't deal any damage.
                damageDealt = (attackValue - opponent.getArmor()) + 1; //If we attack through the opponents armor, we deal the remaining damage + 1. (if we deal 1 damage and opponent has 1 armor, we still deal 1 damage)
                opponent.changeHealth(damageDealt * -1); //Changes the opponents health
            }
            return damageDealt;
        }
        return -1;
    }

    /**
     * This method is called when the player chooses to use a heavy attack
     *
     * @return calls the attack() method, with the specified chance to hit(n out
     * of 10) and additionalDamage
     */
    public int heavyAttack() {
        return attack(6, 2);
    }

    /**
     * This method is called when the player chooses to use a light attack
     *
     * @return calls the attack() method, with the specified chance to hit(n out
     * of 10) and additionalDamage
     */
    public int lightAttack() {
        return attack(9, 0);
    }

    /**
     * This method is used when the opponent has to attack
     *
     * @return If the opponents attack is successful, we return the total damage
     * dealt. Otherwise we return 0.
     */
    private int opponentMove() {
        if (diceRoll(10) <= 9) { //Here we call diceRoll again, and compare it to a number, since the opponent only has 1 type of attack
            int attackValue = (opponent.getAttack() + (opponent.getLevel() / 2)) * diceRoll(4); //We set attackValue equals to the opponents base attack + the opponents level / 2, 
            // and then multiply it by the diceRoll. This way, the opponents damage scales with level as well.
            int damageDealt = 0;
            if (attackValue >= player.getArmorValue()) { //We check if the opponents attack is stronger than players armor
                damageDealt = (attackValue - player.getArmorValue()) + 1; //If the opponent attacks through the players armor, they deal the remaining damage + 1.
                player.changeHealth(damageDealt * -1); //Changes the players health accordingly
            }
            return damageDealt;
        }
        return -1;
    }

    /**
     * This method is used to randomize combat. It generates a random number
     * from 1 to sides.
     *
     * @param sides The number of sides on the dice (how big the range of random
     * numbers is, e.g. 4 is the range 1-4)
     * @return Returns the random number as an integer.
     */
    private int diceRoll(int sides) {
        return (int) ((Math.random() * sides) + 1);
    }

    /**
     * This method is used to check if running is true or false
     *
     * @return Returns true or false
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * This method is used to set the state of running (true/false)
     *
     * @param running Sets the running boolean to true or false
     */
    void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * This method is used to get the opponent CharacterEntity object.
     *
     * @return Returns the opponent object.
     */
    public CharacterEntity getOpponent() {
        return opponent;
    }

}
