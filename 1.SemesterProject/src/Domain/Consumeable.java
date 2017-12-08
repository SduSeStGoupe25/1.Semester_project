/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

//@author Mikkel Pedersen

import Acq.IConsumeable;


class Consumeable extends Item implements IConsumeable{
    
    private int useValue;
    private int hungerValue;

    Consumeable(String name, int sellValue, int count, int useValue, int hungerValue) {
        super(name, sellValue, count, 20, 1);
        this.useValue = useValue;
        this.hungerValue = hungerValue;
    }

    @Override
    public int getUseValue() {
        return useValue;
    }

    void setUseValue(int useValue) {
        this.useValue = useValue;
    }

    @Override
    public int getHungerValue() {
        return hungerValue;
    }

    void setHungerValue(int hungerValue) {
        this.hungerValue = hungerValue;
    }
}
