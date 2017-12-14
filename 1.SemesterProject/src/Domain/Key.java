package Domain;

import Acq.IKey;

/**
 * The key class
 */
class Key extends Item implements IKey{
    
    private int keyID;
    
    Key(String name, int sellValue, int count, int keyID) {
        super(name, sellValue, count, 1, 2);
        this.keyID = keyID;
    }

    @Override
    public int getKeyID() {
        return keyID;
    }
    
    void setKeyID(int keyID) {
        this.keyID = keyID;
    }

}
