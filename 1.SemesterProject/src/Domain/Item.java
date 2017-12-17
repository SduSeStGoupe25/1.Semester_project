package Domain;

import Acq.IItem;

/**
 * The item class
 */
public class Item implements IItem {

    /**
     * The name of the item
     */
    private String name;

    /**
     * The sell value of the item
     */
    private int sellValue;

    /**
     * The amount of items that are this item
     */
    private int count;

    /**
     * The amount of items that max can be in this item
     */
    private final int MAX_COUNT;

    /**
     * The ID of the item. 0 = armor, 1 = comsum, 2 = key, 3 = normal, 4 =
     * weapon
     */
    private int id;

    /**
     * Constructor
     *
     * @param name {@link #name}
     * @param sellValue {@link #sellValue}
     * @param count {@link #count}
     */
    public Item(String name, int sellValue, int count) {
        this(name, sellValue, count, 10, 3);
    }

    /**
     * Constructor
     *
     * @param name {@link #name}
     * @param sellValue {@link #sellValue}
     * @param count {@link #count}
     * @param MAX_COUNT {@link #MAX_COUNT}
     * @param id {@link #id}
     */
    public Item(String name, int sellValue, int count, int MAX_COUNT, int id) {
        this.name = name;
        this.sellValue = sellValue;
        this.count = count;
        this.MAX_COUNT = MAX_COUNT;
        this.id = id;
    }

    /**
     * Called to add to the count
     *
     * @param countToAdd the amount to add
     */
    void addCount(int countToAdd) {
        count += countToAdd;
    }

    @Override
    public String toString() {
        return name + " \n" + "Sells for: " + sellValue + " gold coins." + "\t" + "Can be bought for: " + (sellValue * 2) + " gold coins " + " AMOUNT " + count;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSellValue() {
        return sellValue;
    }

    @Override
    public int getCount() {
        return count;
    }

    /**
     * Called to set the count
     *
     * @param count the count to set count to
     */
    void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getMAX_COUNT() {
        return MAX_COUNT;
    }
}
