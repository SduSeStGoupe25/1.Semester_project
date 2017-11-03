/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldofArthur.Inventory;

//@author Mikkel Pedersen


public class Consumeable extends Item{
    
    private int useValue;

    public Consumeable(String name, int sellValue, int count, int useValue) {
        super(name, sellValue, count, 20);
        this.useValue = useValue;
    }

    public int getUseValue() {
        return useValue;
    }
}
