/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Inventory;

//@author Mikkel Pedersen


public class NormalItem extends Item{

    public NormalItem(String name, int sellValue, int count) {
        super(name, sellValue, count, 10, 3);
    }
}
