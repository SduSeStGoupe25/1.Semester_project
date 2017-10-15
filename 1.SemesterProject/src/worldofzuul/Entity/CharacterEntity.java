/*
 *This is an abstract class that all characters in the game extends from.
 */
package worldofzuul.Entity;

/**
 *
 * @author Victor Gram
 */
public abstract class CharacterEntity {
    protected String name;
    protected int health;
    protected int armor;
    protected int attack;
    protected int level;

    public CharacterEntity(String name, int health, int armor, int attack, int level) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.attack = attack;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }




}
