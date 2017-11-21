/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Arq.INormalItem;

/**
 *
 * @author madsd
 */
public class DataNormalItem extends  DataItem implements INormalItem{

    public DataNormalItem(String name, int sellValue, int count, int MAX_COUNT, int id) {
        super(name, sellValue, count, MAX_COUNT, id);
    }

}
