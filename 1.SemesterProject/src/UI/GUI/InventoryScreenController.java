package UI.GUI;

import Acq.IGame;
import Acq.IItem;
import Acq.IPlayer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
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
    // Uses the selected item (adds hp and hunger to the player).
    private void UseItemButton(ActionEvent event) {
        game.restoreHpPlayer(player.getItemInventory().getInventory().get(selectedIndex));
        updateLists();
    }

    @FXML
    // Moves the selected item to the players equipable inventory.
    private void EquipItemButton(ActionEvent event) {
        game.equipItemPlayer(player.getItemInventory().getInventory().get(selectedIndex));
        updateLists();
    }

    @FXML
    // Adds the selected item to the current room.
    // Removes the selected item from the players inventory.
    private void DropItemButton(ActionEvent event) {
        game.getRoomMap().get(game.getCurrentRoom()).getItemList().add(player.getItemInventory().getInventory().get(selectedIndex));
        game.removeItemPlayer(player.getItemInventory().getInventory().get(selectedIndex), 1);
        updateLists();
    }

    // Responseable for updating the both the ListViews on the inventoryscreen
    void updateLists() {

        List<HBoxCell> items = new ArrayList<>();

        // Adds item to the ArrayList "items" for each item in the players inventory.
        for (IItem item : player.getItemInventory().getInventory()) {
            items.add(new HBoxCell(item));
        }
        listInventory.setItems(FXCollections.observableList(items));

        List<HBoxCell> equipableItems = new ArrayList<>();

        // Adds item to the ArrayList "equipableItems" for each item in the players equipableInventory.
        for (IItem item : player.getEquipableInventory().getInventory()) {
            equipableItems.add(new HBoxCell(item));
        }
        listEquipedItems.setItems(FXCollections.observableList(equipableItems));

        UI.getInstance().getMainController().update(false);
        // Sets the selected item to null and calls updateButtons so that the player
        // is not able to use the buttons without having a selected item.
        selectedItem = null;
        updateButtons();

    }

    // Sets selectedItem to the item the player has clicked within the ListView
    @FXML
    private void CheckSelectedItem(MouseEvent event) {
        selectedItem = listInventory.getSelectionModel().getSelectedItem();
        selectedIndex = listInventory.getSelectionModel().getSelectedIndex();
        updateButtons();
    }

    // Responseable for enabling/disabling the buttons on the inventory screen
    private void updateButtons() {
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
        } else {
            btnUse.setDisable(true);
            btnDrop.setDisable(true);
            btnEquip.setDisable(true);
        }
    }
}
