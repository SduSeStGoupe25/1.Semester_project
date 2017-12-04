package Arq;

/**
 *
 * 
 */
public interface ICombat {
    ICombatResponse combatLoop(int action); 
    
    void startCombat(ICharacterEntity opponent, IRoom room);
}
