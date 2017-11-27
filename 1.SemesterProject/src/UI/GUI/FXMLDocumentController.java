/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IGame;
import Arq.IItem;
import Domain.DomainGame;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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

    private HBoxCell selectedItem;

    private ObservableList<HBoxCell> itemsToPickUp;

    @FXML
    private ListView<HBoxCell> listItemsInRoom;

    @FXML
    private AnchorPane searchRoomWindow;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridPane.setPrefSize(800, 600);
        game = UI.getInstance().getDomainGame();
        borderPaneDefault = new BorderPane();

        /**
         * Inspired by
         * https://stackoverflow.com/questions/30814258/javafx-pass-parameters-while-instantiating-controller-class
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

        gridPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    if (game.movePlayer("north")) {
                        updateUI();
                    }
                    break;
                case A:
                    if (game.movePlayer("west")) {
                        updateUI();
                    }
                    break;
                case S:
                    if (game.movePlayer("south")) {
                        updateUI();
                    }
                    break;
                case D:
                    if (game.movePlayer("east")) {
                        updateUI();
                    }
                    break;
                default:
                    break;
            }
        });
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

    // searchRoom stuff
    @FXML
    private void openSearchRoom(ActionEvent event) {
        if (searchRoomWindow.visibleProperty().get() != true) {
            //buttonUpdate(true);
            searchRoomWindow.setLayoutX(400);
            searchRoomWindow.setLayoutY(300);
            searchRoomWindow.toFront();
            searchRoomWindow.setVisible(true);
            searchRoomWindow.setDisable(false);
            updateItemsInRoom();
        } else {
            //buttonUpdate(false);
            searchRoomWindow.setVisible(false);
            searchRoomWindow.setDisable(true);
        }
    }

    @FXML
    private void pickUpSelectedItem(ActionEvent event) {
        //game.addItemPlayer(selectedItem, selectedItem.getCount());
        game.getRoomMap().get(game.getCurrentRoom()).getItemList().remove(0);
        updateItemsInRoom();
    }

    @FXML
    private void getSelectedItem(MouseEvent event) {
        selectedItem = listItemsInRoom.getSelectionModel().getSelectedItem();
    }

    private void updateItemsInRoom() {
        List<HBoxCell> list = new ArrayList<>();
        for (IItem item : game.getRoomMap().get(game.getCurrentRoom()).getItemList()) {
            list.add(new HBoxCell(item));
        }
        itemsToPickUp = FXCollections.observableList(list);
        listItemsInRoom.setItems(itemsToPickUp);
    }
}
