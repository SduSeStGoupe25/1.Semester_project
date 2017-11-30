/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IItem;
import Arq.IPlayer;
import Arq.IShopkeeper;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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
    private ListView<IItem> shopSelectionList;
    @FXML
    private ListView<IItem> playerInventoryList;
    
    private IPlayer p = UI.getInstance().getDomainGame().getPlayer();
    
    private IShopkeeper s = UI.getInstance().getDomainGame().getShopkeeper();
    
    private List<IItem> playerInventory;
    @FXML
    private ImageView shopkeeperImage;
    @FXML
    private Text playerGold;
    @FXML
    private Text priceText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPlayerInfo(); 
        loadShopkeeper();
    }    
    
    
    
    void loadPlayerInfo () { //Loads the players current inventory to the listview, and displays players current gold balance
        for (IItem item : p.getEquipableInventory().getInventory()) { //loads players  inventory
            playerInventory.add(item);
        }
        ObservableList<IItem> observableInventoryList = FXCollections.observableArrayList(playerInventory); //makes observablelist of the arraylist, due to listviews restrictions
        playerInventoryList.setItems(observableInventoryList);
        playerGold.setText("Gold: " + p.getGold());
    }
    
    
    void loadShopkeeper() { //loads shopkeepers selection to listview, and adjusts the pricetext according to the selected item from the listview
        shopSelectionList.getItems().setAll(s.getItemsToSell().values()); //adds values from map to listview. 
        priceText.setText(Integer.toString(shopSelectionList.getSelectionModel().getSelectedItem().getSellValue()));
        
    }

    @FXML
    private void buyButtonPressed(ActionEvent event) {
        UI.getInstance().getDomainGame().buy(shopSelectionList.getSelectionModel().getSelectedItem(), Integer.parseInt(amountField.getText()), p);
        loadPlayerInfo();
    }

    @FXML
    private void sellButtonPressed(ActionEvent event) {
        UI.getInstance().getDomainGame().sell(playerInventoryList.getSelectionModel().getSelectedItem(), Integer.parseInt(amountField.getText()), p);
        loadPlayerInfo();
    }
    
}
