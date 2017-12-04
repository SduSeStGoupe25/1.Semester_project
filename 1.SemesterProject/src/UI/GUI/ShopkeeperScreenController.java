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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    void loadPlayerInfo() { //Loads the players current inventory to the listview, and displays players current gold balance
        playerInventory = new ArrayList<>();
        for (IItem item : p.getItemInventory().getInventory()) { //loads players  inventory
            playerInventory.add(item);
        }
        ObservableList<IItem> observableInventoryList = FXCollections.observableArrayList(playerInventory); //makes observablelist of the arraylist, due to listviews restrictions
        playerInventoryList.setItems(observableInventoryList);
        playerGold.setText("Gold: " + p.getGold());
    }

    void loadShopkeeper() { //loads shopkeepers selection to listview, and adjusts the pricetext according to the selected item from the listview
        shopSelectionList.getItems().setAll(UI.getInstance().getDomainData().getItemMap().values());
//shopSelectionList.getItems().setAll(s.getItemsToSell().values()); //adds values from map to listview. 

    }

    @FXML
    private void buyButtonPressed(ActionEvent event) {
        if (amountField.getText().isEmpty()) {
            UI.getInstance().getDomainGame().buy(shopSelectionList.getSelectionModel().getSelectedItem(), 1, p);
            loadPlayerInfo();
            return;
        }
        UI.getInstance().getDomainGame().buy(shopSelectionList.getSelectionModel().getSelectedItem(), Integer.parseInt(amountField.getText()), p);

        loadPlayerInfo();
    }

    @FXML
    private void sellButtonPressed(ActionEvent event) {
        if (amountField.getText().isEmpty() ) { 
             UI.getInstance().getDomainGame().sell(playerInventoryList.getSelectionModel().getSelectedItem(), 1, p);
             loadPlayerInfo();
             return;
        }
        UI.getInstance().getDomainGame().sell(playerInventoryList.getSelectionModel().getSelectedItem(), Integer.parseInt(amountField.getText()), p);
        loadPlayerInfo();
    }

    @FXML
    private void updateBuyPriceText(MouseEvent event) {

        if (shopSelectionList.getSelectionModel().getSelectedItem() == null) {  //prevents invocation target exception when listview is clicked without an item being selected
            return;
        }
        int buyPrice = shopSelectionList.getSelectionModel().getSelectedItem().getSellValue() * 2;
        priceText.setText("Price: " + Integer.toString(buyPrice));
    }

    @FXML
    private void updateSellPriceText(MouseEvent event) {

        if (playerInventoryList.getSelectionModel().getSelectedItem() == null) { //prevents invocation target exception when listview is clicked without an item being selected
            return;
        }
        priceText.setText("Price: " + Integer.toString(playerInventoryList.getSelectionModel().getSelectedItem().getSellValue()));
    }

}
