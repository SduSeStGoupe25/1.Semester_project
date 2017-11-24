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
abstract class DataCharactorEntity implements ICharacterEntity {

    private String name;
    private int health;
    private int armor;
    private int level;
    private int maxHealth;
    private int baseHealth;
    private int baseAttack;
    private int attack;
    private int id; // 0 = CharacterEntity, 1 = NPC, 2 = Player, 3 = Shopkeeper, 4 = MovableNPC

    DataCharactorEntity(String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id) {
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
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getId() {
        return id;
    }

}
