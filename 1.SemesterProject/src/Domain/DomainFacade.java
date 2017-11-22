/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.List;
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
    List<HighscoreWrapper> getHighScore();
    
    void saveGame();
    void loadGame(boolean newGame);
    void addScore(HighscoreWrapper hw);
    
    void goRoom(String direction);
    
}
