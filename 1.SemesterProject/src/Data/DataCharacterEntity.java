package Data;

import Acq.ICharacterEntity;

/**
 * DataCharacterEntity class
 */
abstract class DataCharacterEntity implements ICharacterEntity {

    private String name;
    private int health;
    private int armor;
    private int level;
    private int maxHealth;
    private int baseHealth;
    private int baseAttack;
    private int attack;
    private boolean hostile;
    private boolean despawning;
    private int id; // 0 = CharacterEntity, 1 = NPC, 2 = Player, 3 = Shopkeeper, 4 = MovableNPC

    DataCharacterEntity(String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id) {
        this(name, health, armor, level, maxHealth, baseHealth, baseAttack, attack, id, false, false);
    }

    DataCharacterEntity(String name, int health, int armor, int level, int maxHealth, int baseHealth, int baseAttack, int attack, int id, boolean hostile, boolean despawning) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.level = level;
        this.maxHealth = maxHealth;
        this.baseHealth = baseHealth;
        this.baseAttack = baseAttack;
        this.attack = attack;
        this.id = id;
        this.hostile = hostile;
        this.despawning = despawning;
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

    @Override
    public boolean isDespawning(){
        return despawning;
    }

    @Override
    public boolean isHostile(){
        return hostile;
    }

    
}
