/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Mikkel Pedersen
 */
public class GameWonWindowController implements Initializable {

    private UI ui;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ui = UI.getInstance();
    }    

    @FXML
    private void playAgain(ActionEvent event) {
        ui.setState(UIState.TITLESCREEN);
    }

    @FXML
    private void exitGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void saveHighscore(ActionEvent event) {
        ui.getDomainData().addNewScore("NO NAME", ui.getDomainGame().getPlayer().getExp());
    }
    
}
