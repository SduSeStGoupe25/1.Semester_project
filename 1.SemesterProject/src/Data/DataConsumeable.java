/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.IConsumeable;

/**
 *
 * @author madsd
 */
public class DataConsumeable extends DataItem implements IConsumeable {

    private int useValue;
    private int hungerValue;

    public DataConsumeable(String name, int sellValue, int count, int MAX_COUNT, int id) {
        super(name, sellValue, count, MAX_COUNT, id);
    }

    @Override
    public int getUseValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHungerValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
