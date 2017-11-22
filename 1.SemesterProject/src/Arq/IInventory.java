/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arq;

import java.util.ArrayList;

/**
 *
 * @author madsd
 */
public interface IInventory {
    ArrayList<IItem> getInventory();
    int getMaxSlots();
}
