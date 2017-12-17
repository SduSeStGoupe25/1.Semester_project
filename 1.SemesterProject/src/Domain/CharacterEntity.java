package Domain;

import Acq.ICharacterEntity;

/*
 * This is an abstract class that all characters in the game extends from.
 */
abstract class CharacterEntity implements ICharacterEntity {

    /**
     * The name of the character
     */
    private String name;

    /**
     * The character health
     */
    private int health;

    /**
     * The armor of the character
     */
    private int armor;

    /**
     * The level of the character
     */
    private int level;

    /**
     * The maximal healt the chracter can get
     */
    private int maxHealth;

    /**
     * The character healt at level 1
     */
    private int baseHealth;

    /**
     * The character attack at level 1
     */
    private int baseAttack;

    /**
     * The attack of the character
     */
    private int attack;

    /**
     * whether the character are hostile
     */
    private boolean hostile;

    /**
     * Whether the character are despawning
     */
    private boolean despawning;

    /**
     * The character id. 1 = NPC, 2 = Player, 3 = Shopkeeper, 4 = MovableNPC
     */
    private int id;

    /**
     * Constructor
     *
     * @param name {@link #name}
     * @param baseHealth {@link #baseHealth}
     * @param armor {@link #armor}
     * @param baseAttack {@link #baseAttack}
     * @param level {@link #level}
     * @param id {@link #id}
     */
    CharacterEntity(String name, int baseHealth, int armor, int baseAttack, int level, int id) {
        this(name, baseHealth, armor, baseAttack, level, id, false, false);
    }

    /**
     * Constructor
     *
     * @param name {@link #name}
     * @param baseHealth {@link #baseHealth}
     * @param armor {@link #armor}
     * @param baseAttack {@link #baseAttack}
     * @param level {@link #level}
     * @param id {@link #id}
     * @param hostile {@link #hostile}
     * @param despawning {@link #despawning}
     */
    CharacterEntity(String name, int baseHealth, int armor, int baseAttack, int level, int id, boolean hostile, boolean despawning) {
        this.baseHealth = baseHealth;
        this.baseAttack = baseAttack;
        this.name = name;
        this.level = level;
        this.hostile = hostile;
        this.despawning = despawning;

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

    /**
     * Called to set stats to max
     */
    void setStatsToMax() {
        setStats();
        health = maxHealth;
    }

    /**
     * Called when the characterEntity deaths
     */
    abstract void onDeath();

    /**
     * Called to change the characters health
     *
     * @param amount the amount the change the health with
     */
    void changeHealth(int amount) {
        health += amount;
    }

    /**
     * Called to get the percent of maxHealth
     *
     * @return the healthProcent
     */
    public int getHealthPercent() {
        return maxHealth / health;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Called to set the player name
     *
     * @param name the name to set to
     */
    void setName(String name) {
        this.name = name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Called to set the health
     *
     * @param health the health to set health to
     */
    void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    /**
     * Called to set the armor
     *
     * @param armor the armor to set armor to
     */
    void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    /**
     * Called to set attack
     *
     * @param attack the attack to set attack to
     */
    void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Called to set level
     *
     * @param level the level to set level to
     */
    void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getBaseHealth() {
        return baseHealth;
    }

    @Override
    public int getBaseAttack() {
        return baseHealth;
    }

    @Override
    public boolean isDespawning() {
        return despawning;
    }

    @Override
    public boolean isHostile() {
        return hostile;
    }
}
