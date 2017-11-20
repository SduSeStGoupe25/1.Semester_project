/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IDomainData;
import Arq.IDomainGame;
import Arq.IUI;
import Domain.Combat.Combat;
import Domain.DomainFacade;
import Domain.Entity.Player;
import Domain.DomainGame;
import Domain.HighscoreWrapper;
import Domain.Room;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rasmusstamm
 */
public class UI implements IUI{

    static UI ui = null;

    private DomainFacade DF = DomainGame.getInstance();

    public UI() {

    }

    public static UI getInstance() {
        if (ui == null) {
            ui = new UI();
        }
        return ui;
    }
    
    public DomainFacade getGame(){
        return DF;
    }

    @Override
    public void startApplication(String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void injectDomainData(IDomainData domainData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void injectDomainGame(IDomainGame domainGame) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
