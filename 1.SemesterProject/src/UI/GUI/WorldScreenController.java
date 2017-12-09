/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Acq.IGame;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author madsd
 */
public class WorldScreenController implements Initializable {

    @FXML
    private BorderPane borderPane;

    private SpawnScreenController Scon;

    private IGame game;
    @FXML
    private Button btnNorth;
    @FXML
    private Button btnWest;
    @FXML
    private Button btnSouth;
    @FXML
    private Button btnEast;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = UI.getInstance().getDomainGame();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SpawnScreen.fxml"));
        try {
            Pane pane = loader.load();
            borderPane.setCenter(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        File f = new File("Img/" + game.getCurrentRoom() + ".png");
        if (!f.exists()) {
            f = new File("Img/place.png");
        }
        //https://stackoverflow.com/questions/41188217/javafx-replacing-background-image-of-a-borderpane
        Image image = new Image(f.toURI().toString(), 100, 100, false, false);
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        borderPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));

        // Create a controller instance
        Scon = loader.getController();

        updateUI();
    }

    @FXML
    private void GoNorthButton(ActionEvent event) {
        if (game.movePlayer("north")) {
            updateUI();
        }
    }

    @FXML
    private void GoWestButton(ActionEvent event) {
        if (game.movePlayer("west")) {
            updateUI();
        }
    }

    @FXML
    private void GoSouthButton(ActionEvent event) {
        if (game.movePlayer("south")) {
            updateUI();
        }
    }

    @FXML
    private void GoEastButton(ActionEvent event) {
        if (game.movePlayer("east")) {
            updateUI();
        }
    }

    private void updateUI() {
        Scon.update();
        game = UI.getInstance().getDomainGame();
        if (!game.isInCombat()) {
            if (game.getExitCurrentRoom("north") != null) {
                btnNorth.setDisable(false);
            } else {
                btnNorth.setDisable(true);
            }

            if (game.getExitCurrentRoom("east") != null) {
                btnEast.setDisable(false);
            } else {
                btnEast.setDisable(true);
            }

            if (game.getExitCurrentRoom("south") != null) {
                btnSouth.setDisable(false);
            } else {
                btnSouth.setDisable(true);
            }

            if (game.getExitCurrentRoom("west") != null) {
                btnWest.setDisable(false);
            } else {
                btnWest.setDisable(true);
            }

        } else {
            UI.getInstance().setState(UIState.COMBATSCREEN);
            return;
        }
        UI.getInstance().getMainController().update(false);
    }
}
