/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.IExit;

/**
 *
 * @author madsd
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
