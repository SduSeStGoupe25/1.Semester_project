/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IConsumeable;
import Arq.IDomainGame;
import Arq.IItem;
import Arq.IPlayer;
import java.net.URL;
import java.util.ResourceBundle;
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
    private ListView<IItem> listInventory;
    @FXML
    private ListView<IItem> listEquipedItems;

    ObservableList<IItem> items;
    ObservableList<IItem> equipableItems;
    
    IItem selectedItem;

    IPlayer player;
    
    IDomainGame game;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!_____________________");
        System.out.println(UI.getInstance().getDomainGame());
        System.out.println("----------------------");
        game = UI.getInstance().getDomainGame();
        player = game.getPlayer();
        
        btnUse.setDisable(true);
        btnEquip.setDisable(true);
        btnDrop.setDisable(true);
        updateLists();
    }

    @FXML
    private void UseItemButton(ActionEvent event) {
        IConsumeable c = (IConsumeable)(selectedItem);
        //player.restoreHp(c);
    }

    @FXML
    private void EquipItemButton(ActionEvent event) {
        //player.equip(selectedItem);
        //updateLists();
    }

    @FXML
    private void DropItemButton(ActionEvent event) {
        //player.getItemInventory().removeItem(listInventory.getSelectionModel().getSelectedItem(), 1);
    }

    private void updateLists() {
//        items = FXCollections.observableArrayList(player.getItemInventory().getInventory());
//        equipableItems = FXCollections.observableArrayList(player.getEquipableInventory().getInventory());
//        listInventory.setItems(items);
//        listEquipedItems.setItems(equipableItems);
    }

    @FXML
    private void CheckSelectedItem(MouseEvent event) {
//        selectedItem = listInventory.getSelectionModel().getSelectedItem();
//        if (selectedItem != null) {
//            
//            if (selectedItem instanceof Consumeable) {
//                btnUse.setDisable(false);
//                btnEquip.setDisable(true);
//                btnDrop.setDisable(false);
//            }
//            
//            if(selectedItem instanceof Weapon){
//                btnUse.setDisable(true);
//                btnEquip.setDisable(false);
//                btnDrop.setDisable(false);
//            }
//            
//        }
    }

}
