/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.IKey;

/**
 *
 * @author madsd
 */
public class DataKey extends DataItem implements IKey {

    private int keyID;

    public DataKey(String name, int sellValue, int count, int MAX_COUNT, int id) {
        super(name, sellValue, count, MAX_COUNT, id);
    }

    @Override
    public int getKeyID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
