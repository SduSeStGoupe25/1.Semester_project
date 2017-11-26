/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IGame;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author madsd
 */
public class WorldSceneController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private GridPane centerGrid;

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

        }

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
//        try {
//            Parent root = loader.load();
//        } catch (IOException ex) {
//            Logger.getLogger(WorldSceneController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        FXMLDocumentController dac = (FXMLDocumentController) loader.getController();
//        dac.update("combat");
//        } else {
//            System.out.println("##################################IN COMBAT #¤#¤#¤#¤#¤#¤#¤#¤#¤#¤");
//            buttonUpdate(true);
//            try {
//                System.out.println("hare------------------------------------");
//                if (gridPane.getChildren().contains(borderPane)) {
//                    gridPane.getChildren().remove(borderPane);
//                    gridPane.add(borderPaneDefault, 1, 0);
//                }
//                borderPaneDefault.setCenter(FXMLLoader.load(getClass().getResource("CombatScreen.fxml")));
//                System.out.println("hare------------------------------------");
//            } catch (IOException ex) {
//                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }
}
