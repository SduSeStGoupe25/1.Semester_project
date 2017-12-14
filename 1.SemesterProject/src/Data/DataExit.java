package Data;

import Acq.IExit;

/**
 * DataExit class
 */
class DataExit implements IExit{

    private String name1;
    private String name2;
    private boolean locked;
    private int lockID;

    @Override
    public String getName1() {
        return name1;
    }

    @Override
    public String getName2() {
        return name2;
    }

    @Override
    public boolean getLocked() {
        return locked;
    }

    @Override
    public int getlockID() {
        return lockID;
    }
}
