package Domain;

import Arq.ICharacterEntity;

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
    CharacterEntity(String name, int baseHealth, int armor, int baseAttack, int level, int id) {
        this.baseHealth = baseHealth;
        this.baseAttack = baseAttack;
        this.name = name;
        this.level = level;
        
        setStats();
        this.health = maxHealth;
        this.armor = armor;
        this.id = id;
        
    }

    /**
     * Method used for calculating attributes if CharacterEntity is above level
     * 1
     */
    void setStats() {
        attack = (level * baseAttack) / 10;
        maxHealth = ((level - 1) * 10) + baseHealth;
    }
    
    void setStatsToMax(){
        setStats();
        health = maxHealth;
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

    @Override
    public int getBaseHealth() {
        return baseHealth;
    }

    void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    @Override
    public int getBaseAttack() {
        return baseHealth;
    }

    void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }
}
