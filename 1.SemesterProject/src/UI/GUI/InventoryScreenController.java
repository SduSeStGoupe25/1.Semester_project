/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IDomainGame;
import Arq.IGame;
import Arq.IItem;
import Arq.IPlayer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mikkel Pedersen
 */
public class InventoryScreenController implements Initializable {

    @FXML
    private Button btnUse;
    @FXML
    private Button btnEquip;
    @FXML
    private Button btnDrop;
    @FXML
    private ListView<HBoxCell> listInventory;
    @FXML
    private ListView<HBoxCell> listEquipedItems;

    //ObservableList<HBoxCell> items;
    //ObservableList<HBoxCell> equipableItems;

    HBoxCell selectedItem;

    int selectedIndex;

    IPlayer player;

    IGame game;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = UI.getInstance().getDomainGame();
        player = game.getPlayer();

        btnUse.setDisable(true);
        btnEquip.setDisable(true);
        btnDrop.setDisable(true);
        updateLists();
    }

    @FXML
    private void UseItemButton(ActionEvent event) {
        game.restoreHpPlayer(player.getItemInventory().getInventory().get(selectedIndex));
        updateLists();
    }

    @FXML
    private void EquipItemButton(ActionEvent event) {
        game.equipItemPlayer(player.getItemInventory().getInventory().get(selectedIndex));
        updateLists();
    }

    @FXML
    private void DropItemButton(ActionEvent event) {
        game.getRoomMap().get(game.getCurrentRoom()).getItemList().add(player.getItemInventory().getInventory().get(selectedIndex));
        game.removeItemPlayer(player.getItemInventory().getInventory().get(selectedIndex), 1);
        updateLists();
    }

    public void updateLists() {
        List<HBoxCell> items = new ArrayList<>();
        
        for (IItem item : player.getItemInventory().getInventory()) {
            items.add(new HBoxCell(item));
        }
        listInventory.setItems(FXCollections.observableList(items));
        
        List<HBoxCell> equipableItems = new ArrayList<>();
        
        for (IItem item : player.getEquipableInventory().getInventory()) {
            equipableItems.add(new HBoxCell(item));
        }
        listEquipedItems.setItems(FXCollections.observableList(equipableItems));
    }

    @FXML
    private void CheckSelectedItem(MouseEvent event) {
        selectedItem = listInventory.getSelectionModel().getSelectedItem();
        selectedIndex = listInventory.getSelectionModel().getSelectedIndex();
        if (selectedItem != null) {

            // Checks if item is a consumeable
            if (selectedItem.getItemId() == 1) {
                btnUse.setDisable(false);
                btnEquip.setDisable(true);
                btnDrop.setDisable(false);
            }
            // Checks if item is a weapon or armor
            if (selectedItem.getItemId() == 4 || selectedItem.getItemId() == 0) {
                btnUse.setDisable(true);
                btnEquip.setDisable(false);
                btnDrop.setDisable(false);
            }

            //Checks if item is a normalItem
            if (selectedItem.getItemId() == 3) {
                btnUse.setDisable(true);
                btnEquip.setDisable(true);
                btnDrop.setDisable(false);
            }

            // Checks if item is a key
            if (selectedItem.getItemId() == 2) {
                btnUse.setDisable(true);
                btnEquip.setDisable(true);
                btnDrop.setDisable(true);
            }

        }
    }

}
