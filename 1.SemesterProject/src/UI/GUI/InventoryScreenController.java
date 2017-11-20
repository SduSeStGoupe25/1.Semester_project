/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Domain.Entity.Player;
import Domain.Game;
import Domain.Inventory.Consumeable;
import Domain.Inventory.Inventory;
import Domain.Inventory.Item;
import java.net.URL;
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
    private ListView<Item> listInventory;
    @FXML
    private ListView<Item> listEquipedItems;

    ObservableList<Item> items;
    ObservableList<Item> equipableItems;
    
    Item selectedItem;

    Player player;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnUse.setDisable(true);
        btnEquip.setDisable(true);
        btnDrop.setDisable(true);
        items = FXCollections.observableArrayList(player.getItemInventory().getInventory());
        equipableItems = FXCollections.observableArrayList(player.getItemInventory().getInventory());
        updateLists();
    }

    @FXML
    private void UseItemButton(ActionEvent event) {
        //player.restoreHp(selectedItem.)   
    }

    @FXML
    private void EquipItemButton(ActionEvent event) {
        player.equip(selectedItem);
    }

    @FXML
    private void DropItemButton(ActionEvent event) {
        player.getItemInventory().removeItem(listInventory.getSelectionModel().getSelectedItem(), 1);
    }

    private void updateLists() {
        listInventory.setItems(items);
        listEquipedItems.setItems(equipableItems);
    }

    @FXML
    private void CheckSelectedItem(MouseEvent event) {
        selectedItem = listInventory.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            
            if (selectedItem instanceof Consumeable) {
                btnUse.setDisable(false);
                btnEquip.setDisable(true);
                btnDrop.setDisable(false);
            }
            
        }
    }

}
