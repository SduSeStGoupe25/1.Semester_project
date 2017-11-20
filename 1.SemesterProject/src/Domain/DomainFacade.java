/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Domain.Combat.Combat;
import Domain.Entity.Player;
import java.util.Map;

/**
 *
 * @author madsd
 */
public interface DomainFacade {
    Room getCurrentRoom();
    Player getPlayer();
    Combat getCombat();
    Map<String, Room> getRooms();
    
    void saveGame();
    void loadGame();
    void getHighScore();
    void addScore();
    
}
