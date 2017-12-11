package Acq;

/**
 *
 * Combat interface
 */
public interface ICombat {

    /**
     * Called to perform a action in combat
     *
     * @param action is that to do, 0 = lightAttack, 1 = heavyAttack, 2 = flee,
     * 3 is use when combat are started to get start information
     * @return information about the turn
     */
    ICombatResponse combatLoop(int action);

    /**
     * Called to start combat
     *
     * @param opponent the character to fight against
     */
    void startCombat(ICharacterEntity opponent);
}
