package Domain;

import Acq.IConsumeable;

/**
 * Consumeable class
 */
class Consumeable extends Item implements IConsumeable {

    /**
     * The amount of hp that will be restored
     */
    private int useValue;

    /**
     * The amount of hunger that will be restored
     */
    private int hungerValue;

    /**
     * Constructor
     *
     * @param name {@link Item#name}
     * @param sellValue {@link Item#sellValue}
     * @param count {@link Item#count}
     * @param useValue {@link #useValue}
     * @param hungerValue {@link #hungerValue}
     */
    Consumeable(String name, int sellValue, int count, int useValue, int hungerValue) {
        super(name, sellValue, count, 20, 1);
        this.useValue = useValue;
        this.hungerValue = hungerValue;
    }

    @Override
    public int getUseValue() {
        return useValue;
    }

    @Override
    public int getHungerValue() {
        return hungerValue;
    }

    @Override
    public String toString() {
        return super.toString() + " useValue=" + useValue + ", hungerValue=" + hungerValue;
    }

}
