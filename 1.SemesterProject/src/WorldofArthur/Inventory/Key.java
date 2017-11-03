/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldofArthur.Inventory;

//@author Mikkel Pedersen


public class Key extends Item{
    private int keyID;
    
    public Key(String name, int sellValue, int count, int keyID) {
        super(name, sellValue, count, 1);
        this.keyID = keyID;
    }

    public int getKeyID() {
        return keyID;
    }
    
    
}
