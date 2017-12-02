package Domain;

import Arq.ICharacterEntity;

/**
 *
 * @author Victor Gram
 */
/*
 *This is an abstract class that all characters in the game extends from.
 */
abstract class CharacterEntity implements ICharacterEntity {

    private String name;
    private int health;
    private int armor;
    private int level;
    private int maxHealth;
    private int baseHealth;
    private int baseAttack;
    private int attack;
    private int id; // 0 = CharacterEntity, 1 = NPC, 2 = Player, 3 = Shopkeeper, 4 = MovableNPC

    //Constructor
    CharacterEntity(String name, int health, int armor, int attack, int level, int id) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.level = level;
        maxHealth = health;
        baseHealth = health;
        baseAttack = attack;
        this.id = id;
        setStats();
    }

    /**
     * Method used for calculating attributes if CharacterEntity is above level
     * 1
     */
    protected void setStats() {
        //health = ((level - 1) * 10) + baseHealth - (maxHealth - health);
        attack = level * baseAttack;
        maxHealth = ((level - 1) * 10) + baseHealth;
    }

    abstract void onDeath();

    void changeHealth(int amount) {
        health += amount;
    }

    public int getHealthPercent() {
        return maxHealth / health;
    }

    @Override
    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getLevel() {
        return level;
    }

    void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public int getId() {
        return id;
    }
    
    void setId(int id) {
        this.id = id;
    }
}
