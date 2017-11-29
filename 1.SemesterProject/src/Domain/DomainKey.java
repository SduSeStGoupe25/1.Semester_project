/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

//@author Mikkel Pedersen

import Arq.IKey;



class DomainKey extends DomainItem implements IKey{
    
    private int keyID;
    public DomainKey(){}
    public DomainKey(String name, int sellValue, int count, int keyID) {
        super(name, sellValue, count, 1, 2);
        this.keyID = keyID;
    }

    @Override
    public int getKeyID() {
        return keyID;
    }
    
    public void setKeyID(int keyID) {
        this.keyID = keyID;
    }

}
