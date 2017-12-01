/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IGame;
import Arq.IItem;
import Arq.IPlayer;
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
import javafx.scene.control.TextField;
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

    private StatsPanelController statController;

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
    private VBox vbox;

    private HBoxCell selectedItem;

    private ObservableList<HBoxCell> itemsToPickUp;

    @FXML
    private ListView<HBoxCell> listItemsInRoom;

    @FXML
    public AnchorPane searchRoomWindow;
    @FXML
    private Button searchButton;
    @FXML
    private Button btnPickUp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridPane.setPrefSize(800, 600);
        game = UI.getInstance().getDomainGame();

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
    }

    @FXML
    public void openInventoryButton(ActionEvent e) throws IOException {
        if (searchRoomWindow.visibleProperty().get() == true) {
            openSearchRoom(e);
        }
        UI.getInstance().setState(UIState.INVENTORYSCREEN);

    }

    @FXML
    private void setDefaultView(ActionEvent event) {
        if (searchRoomWindow.visibleProperty().get() == true) {
            openSearchRoom(event);
        }
        UI.getInstance().setState(UIState.WORLDSCREEN);
    }

    @FXML
    private void openQuestlogButton(ActionEvent event) throws IOException {
        if (searchRoomWindow.visibleProperty().get() == true) {
            openSearchRoom(event);
        }
        UI.getInstance().setState(UIState.QUESTSCREEN);
    }

    @FXML
    private void quitGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void openMapButton(ActionEvent event) throws IOException {
        if (searchRoomWindow.visibleProperty().get() == true) {
            openSearchRoom(event);
        }
        UI.getInstance().setState(UIState.MAPSCREEN);
    }

    private void buttonUpdate(boolean disable) {
        worldViewButton.setDisable(disable);
        inventoryButton.setDisable(disable);
        searchButton.setDisable(disable);
        questButton.setDisable(disable);
        mapButton.setDisable(disable);
        saveButton.setDisable(disable);
        quitButton.setDisable(disable);
    }

    // searchRoom stuff
    
// Opens the searchRoom pane which allows the player to see and pick up any items
// that might be in the current room
    @FXML
    private void openSearchRoom(ActionEvent event) {
        if (searchRoomWindow.visibleProperty().get() != true) {
            if (selectedItem == null) {
                btnPickUp.setDisable(true);
            }
            searchRoomWindow.setLayoutX(400);
            searchRoomWindow.setLayoutY(300);
            searchRoomWindow.toFront();
            searchRoomWindow.setVisible(true);
            searchRoomWindow.setDisable(false);
            updateItemsInRoom();
        } else {
            searchRoomWindow.setVisible(false);
            searchRoomWindow.setDisable(true);
        }
    }

    // Adds the highlighted item to the players inventory and removes the item from the current room
    @FXML
    private void pickUpSelectedItem(ActionEvent event) {
        game.addItemPlayer(selectedItem.getItem(), selectedItem.getItem().getCount());
        game.getRoomMap().get(game.getCurrentRoom()).getItemList().remove(0);
        btnPickUp.setDisable(true);
        updateItemsInRoom();
    }

    @FXML
    private void getSelectedItem(MouseEvent event) {
        selectedItem = listItemsInRoom.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            btnPickUp.setDisable(false);
        }
    }
    
    // Simply refreshes the list of items in the current room
    private void updateItemsInRoom() {
        List<HBoxCell> list = new ArrayList<>();
        for (IItem item : game.getRoomMap().get(game.getCurrentRoom()).getItemList()) {
            list.add(new HBoxCell(item));
        }
        itemsToPickUp = FXCollections.observableList(list);
        listItemsInRoom.setItems(itemsToPickUp);
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void updateStats() {
        statController.updateBars();
    }

}
