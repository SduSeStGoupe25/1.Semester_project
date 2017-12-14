package UI.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 */
public class GameOverWindowController implements Initializable {
    
    private UI ui;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ui = UI.getInstance();
    }    

    @FXML
    private void exitGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void playAgain(ActionEvent event) {
        ui.setState(UIState.TITLESCREEN);
    }

    @FXML
    private void saveHighscore(ActionEvent event) {
        ui.getDomainData().addNewScore(ui.getDomainGame().getPlayer().getName(), ui.getDomainGame().getPlayer().getScoreValue());
    }
    
}
