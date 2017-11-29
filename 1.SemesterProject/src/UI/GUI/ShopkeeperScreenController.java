/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IItem;
import Arq.IPlayer;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mikkel Pedersen
 */
public class ShopkeeperScreenController implements Initializable {

    @FXML
    private Button buyButton;
    @FXML
    private TextField amountField;
    @FXML
    private Button sellButton;
    @FXML
    private ListView<?> shopSelectionList;
    @FXML
    private ListView<IItem> playerInventoryList;
    
    private IPlayer p = UI.getInstance().getDomainGame().getPlayer();
    
    private List<IItem> playerInventory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void loadPlayerInventory () { 
        for (IItem item : p.getEquipableInventory().getInventory()) { //loads players  inventory
            playerInventory.add(item);
        }
        ObservableList<IItem> observableInventoryList = FXCollections.observableArrayList(playerInventory); //makes observablelist of the arraylist, due to listviews restrictions
        playerInventoryList.setItems(observableInventoryList);
    }
    
    //instans af Shoopekeper fra domæne? 
    
}
