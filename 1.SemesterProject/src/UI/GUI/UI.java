/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Domain.Combat.Combat;
import Domain.DomainFacade;
import Domain.Entity.Player;
import Domain.Game;
import Domain.HighscoreWrapper;
import Domain.Room;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rasmusstamm
 */
public class UI {

    static UI ui = null;

    private DomainFacade DF = Game.getInstance();

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
}
