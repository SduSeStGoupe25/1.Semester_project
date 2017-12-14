package Data;

import Acq.IConsumeable;

/**
 * DataConsumeable class
 */
class DataConsumeable extends DataItem implements IConsumeable {

    private int useValue;
    private int hungerValue;

    DataConsumeable(String name, int sellValue, int count, int MAX_COUNT, int id) {
        super(name, sellValue, count, MAX_COUNT, id);
    }

    @Override
    public int getUseValue() {
        return useValue;
    }

    @Override
    public int getHungerValue() {
        return hungerValue;
    }

}
