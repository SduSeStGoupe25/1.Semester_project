package Data;

import Acq.IKey;

/**
 * DataKey class
 */
class DataKey extends DataItem implements IKey {

    private int keyID;

    DataKey(String name, int sellValue, int count, int MAX_COUNT, int id) {
        super(name, sellValue, count, MAX_COUNT, id);
    }

    @Override
    public int getKeyID() {
        return keyID;
    }

}
