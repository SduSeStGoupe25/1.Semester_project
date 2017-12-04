/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IGame;
import Arq.IItem;
import Arq.IPlayer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author rasmusstamm
 */
public class SearchRoomController implements Initializable {

    @FXML
    private ListView<IItem> listItemsInRoom;
    
    IGame game;
    
    IPlayer player;
    
    int selectedItem;
    
    ObservableList<IItem> items;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = UI.getInstance().getDomainGame();
        player = game.getPlayer();
        items = FXCollections.observableArrayList(game.getRoomMap().get(game.getCurrentRoom()).getItemList());
        listItemsInRoom.setItems(items);
        // TODO
    }    

    @FXML
    private void pickUpSelectedItem(ActionEvent event) {
        game.addItemPlayer(items.get(selectedItem), items.get(selectedItem).getCount());
    }

    @FXML
    private void getSelectedItem(MouseEvent event) {
        selectedItem = listItemsInRoom.getSelectionModel().getSelectedIndex();
    }
    
}
