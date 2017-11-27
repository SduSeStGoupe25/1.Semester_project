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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author rasmusstamm
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private BorderPane borderPane;

    private BorderPane borderPaneDefault;

    private StatsPanelController statController;

    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane defaultView;
    @FXML
    private Button btnNorth;
    @FXML
    private Button btnWest;
    @FXML
    private Button btnSouth;
    @FXML
    private Button btnEast;

    private IGame game;
    @FXML
    private Button worldViewButton;
    @FXML
    private Button inventoryButton;
    @FXML
    private Button questButton;
    @FXML
    private Button mapButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button quitButton;
    @FXML
    private VBox vbox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridPane.setPrefSize(800, 600);
        game = UI.getInstance().getDomainGame();
        borderPaneDefault = new BorderPane();

        /**
         * Inspireret from https://stackoverflow.com/questions/30814258/javafx-pass-parameters-while-instantiating-controller-class
         */
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatsPanel.fxml"));
        try {
            GridPane statPane = loader.load();
            vbox.getChildren().add(statPane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Create a controller instance
        statController = loader.getController();

    }

    @FXML
    public void openInventoryButton(ActionEvent e) throws IOException {
        if (gridPane.getChildren().contains(borderPane)) {
            gridPane.getChildren().remove(borderPane);
            gridPane.add(borderPaneDefault, 1, 0);
        }
        borderPaneDefault.setCenter(FXMLLoader.load(getClass().getResource("InventoryScreen.fxml")));
    }

    @FXML
    private void setDefaultView(ActionEvent event) {
        if (gridPane.getChildren().contains(borderPaneDefault)) {
            gridPane.getChildren().remove(borderPaneDefault);
            gridPane.add(borderPane, 1, 0);
        }
    }

    @FXML
    private void openQuestlogButton(ActionEvent event) throws IOException {
        if (gridPane.getChildren().contains(borderPane)) {
            gridPane.getChildren().remove(borderPane);
            gridPane.add(borderPaneDefault, 1, 0);
        }
        borderPaneDefault.setCenter(FXMLLoader.load(getClass().getResource("QuestlogScene.fxml")));
    }

    @FXML
    private void quitGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void openMapButton(ActionEvent event) throws IOException {
        if (gridPane.getChildren().contains(borderPane)) {
            gridPane.getChildren().remove(borderPane);
            gridPane.add(borderPaneDefault, 1, 0);
        }
        borderPaneDefault.setCenter(FXMLLoader.load(getClass().getResource("MapScene.fxml")));
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
        statController.updateBars();
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
            buttonUpdate(true);
            try {
                if (gridPane.getChildren().contains(borderPane)) {
                    gridPane.getChildren().remove(borderPane);
                    gridPane.add(borderPaneDefault, 1, 0);
                }
                borderPaneDefault.setCenter(FXMLLoader.load(getClass().getResource("CombatScreen.fxml")));
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void saveButtonCLicked(ActionEvent event) {
    }

    private void buttonUpdate(boolean disable) {
        worldViewButton.setDisable(disable);
        inventoryButton.setDisable(disable);
        questButton.setDisable(disable);
        mapButton.setDisable(disable);
        saveButton.setDisable(disable);
        quitButton.setDisable(disable);
    }
}
