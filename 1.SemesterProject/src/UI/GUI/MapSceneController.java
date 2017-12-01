/*
 * class from where the map is updated to match the current loction of the player, and the movable ncp's
 */
package UI.GUI;

import Arq.IRoom;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Mikkel Pedersen
 */
public class MapSceneController implements Initializable {
    private String playerRoom = UI.getInstance().getDomainGame().getCurrentRoom();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void updateMap() {
        
    }
}
