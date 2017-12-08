/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Acq.INormalItem;

/**
 *
 * @author madsd
 */
class DataNormalItem extends  DataItem implements INormalItem{

    DataNormalItem(String name, int sellValue, int count, int MAX_COUNT, int id) {
        super(name, sellValue, count, MAX_COUNT, id);
    }

}
