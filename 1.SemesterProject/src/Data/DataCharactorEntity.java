/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.ICharacterEntity;

/**
 *
 * @author madsd
 */
public abstract class DataCharactorEntity implements ICharacterEntity {

    private String name;
    private int health;
    private int armor;
    private int level;
    private int maxHealth;
    private int baseHealth;
    private int baseAttack;
    private int attack;
    private int id; // 0 = CharacterEntity, 1 = NPC, 2 = Player, 3 = Shopkeeper, 4 = MovableNPC

    public DataCharactorEntity(String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.level = level;
        this.maxHealth = maxHealth;
        this.baseHealth = baseHealth;
        this.baseAttack = baseAttack;
        this.attack = attack;
        this.id = id;
    }
    
    

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHealth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHealthPercent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getArmor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getAttack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLevel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxHealth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId() {
        return id;
    }

}
