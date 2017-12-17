package UI.GUI;

import Acq.IItem;
import Acq.IPlayer;
import Acq.IShopkeeper;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
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


    @FXML
    private ImageView shopkeeperImage;
    @FXML
    private Label playerGold;
    @FXML
    private Label priceText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPlayerInfo();
        loadShopkeeper();
    }

    void loadPlayerInfo() { //Loads the players current inventory to the listview, and displays players current gold balance
        playerInventoryList.getItems().clear();
        ObservableList<IItem> observableInventoryList = FXCollections.observableArrayList(p.getItemInventory().getInventory()); //makes observablelist of the arraylist, due to listviews restrictions
        playerInventoryList.setItems(observableInventoryList);
        playerGold.setText("Gold: " + p.getGold());
    }

    void loadShopkeeper() { //loads shopkeepers selection to listview, and adjusts the pricetext according to the selected item from the listview
        ArrayList<IItem> itemsToSell = new ArrayList<>();
        for (String s : UI.getInstance().getDomainGame().getShopkeeper().getItemsToSell()) {
            itemsToSell.add(UI.getInstance().getDomainData().getItem(s));
        }
        
        ObservableList<IItem> ob = FXCollections.observableArrayList(itemsToSell);
        shopSelectionList.setItems(ob);
    }

    @FXML
    private void buyButtonPressed(ActionEvent event) {
        if (shopSelectionList.getSelectionModel().getSelectedItem() == null) { //prevents invocation target exception if sell is pressed without an item selected
            return;
        }
        if (amountField.getText().isEmpty()) {
            UI.getInstance().getDomainGame().buy(shopSelectionList.getSelectionModel().getSelectedItem(), 1);
            loadPlayerInfo();
            return;
        }
        UI.getInstance().getDomainGame().buy(shopSelectionList.getSelectionModel().getSelectedItem(), Integer.parseInt(amountField.getText()));
        loadPlayerInfo();
        UI.getInstance().getMainController().update(false);
    }

    @FXML
    private void sellButtonPressed(ActionEvent event) {
        if (playerInventoryList.getSelectionModel().getSelectedItem() == null) { //prevents invocation target exception if buy is pressed without an item selected
            return;
        }
        if (amountField.getText().isEmpty() ) { 
             UI.getInstance().getDomainGame().sell(playerInventoryList.getSelectionModel().getSelectedItem(), 1);
             loadPlayerInfo();
             return;
        }
        UI.getInstance().getDomainGame().sell(playerInventoryList.getSelectionModel().getSelectedItem(), Integer.parseInt(amountField.getText()));
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
