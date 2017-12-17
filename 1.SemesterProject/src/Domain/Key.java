package Domain;

import Acq.IKey;

/**
 * The key class
 */
class Key extends Item implements IKey{
    
    /**
     * The value that has to match a lockID to unlock
     */
    private int keyID;
    
    /**
     * Constructor
     * @param name {@link Item#name}
     * @param sellValue {@link Item#sellValue}
     * @param count {@link Item#count}
     * @param keyID {@link #keyID}
     */
    Key(String name, int sellValue, int count, int keyID) {
        super(name, sellValue, count, 1, 2);
        this.keyID = keyID;
    }

    @Override
    public int getKeyID() {
        return keyID;
    }
}
