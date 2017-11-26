/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IDomainGame;
import Arq.IGame;

import Domain.DomainGame;
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author rasmusstamm
 */
public class FXMLDocumentController implements Initializable {

    StatsPanelController bobLarsen = new StatsPanelController();
    
    WorldSceneController wc = new WorldSceneController();

    @FXML
    private GridPane gridPane;

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
    private BorderPane scenePane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridPane.setPrefSize(800, 600);
        game = UI.getInstance().getDomainGame();
    }

    @FXML
    public void openInventoryButton(ActionEvent e) throws IOException {
        scenePane.setCenter(FXMLLoader.load(getClass().getResource("InventoryScreen.fxml")));
    }

    @FXML
    private void setDefaultView(ActionEvent event) throws IOException {
        scenePane.setCenter(FXMLLoader.load(getClass().getResource("WorldScene.fxml")));
    }

    @FXML
    private void openQuestlogButton(ActionEvent event) throws IOException {
        scenePane.setCenter(FXMLLoader.load(getClass().getResource("QuestlogScene.fxml")));
    }

    @FXML
    private void quitGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void openMapButton(ActionEvent event) throws IOException {
        scenePane.setCenter(FXMLLoader.load(getClass().getResource("MapScene.fxml")));
    }

//    private void updateUI() {
//        if (!game.isInCombat()) {
//            if (game.getExitCurrentRoom("north") != null) {
//                btnNorth.setDisable(false);
//            } else {
//                btnNorth.setDisable(true);
//            }
//
//            if (game.getExitCurrentRoom("east") != null) {
//                btnEast.setDisable(false);
//            } else {
//                btnEast.setDisable(true);
//            }
//
//            if (game.getExitCurrentRoom("south") != null) {
//                btnSouth.setDisable(false);
//            } else {
//                btnSouth.setDisable(true);
//            }
//
//            if (game.getExitCurrentRoom("west") != null) {
//                btnWest.setDisable(false);
//            } else {
//                btnWest.setDisable(true);
//            }
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
//
//        }
//    }
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

    public void update(String toDo) {
        System.out.println("IS HERE ###############################################");
        if (toDo.equals("combat")) {
            System.out.println("combat");
            try {
                scenePane.setCenter(FXMLLoader.load(getClass().getResource("CombatScreen.fxml")));
                buttonUpdate(true);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
