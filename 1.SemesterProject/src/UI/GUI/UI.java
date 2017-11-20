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
public class UI implements DomainFacade{
    
    private transient static UI instance = null;
    
    @Override
    public Room getCurrentRoom(){
        return Game.getInstance().getCurrentRoom();
    }
    
    @Override
    public Player getPlayer(){
        return Game.getInstance().getPlayer();
    }
    
    @Override
    public Combat getCombat(){
        return Game.getInstance().getCombat();
    }
    
    @Override
    public Map<String, Room> getRooms(){
        return Game.getInstance().getRooms();
    }

    @Override
    public void addScore() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HighscoreWrapper> getHighScore() {
        return getHighScore();
    }
    
    public UI getInstance(){
        if (instance == null) { 
            instance = new UI(); 
        }
        return instance;
    }
    
    
    
}
