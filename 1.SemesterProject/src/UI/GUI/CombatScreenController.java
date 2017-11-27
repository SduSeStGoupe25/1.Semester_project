/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.ICharacterEntity;
import Arq.ICombatResponse;
import Arq.IItem;
import Arq.IPlayer;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Mikkel Pedersen
 */
public class CombatScreenController implements Initializable {

    @FXML
    private ImageView imageForest;
    @FXML
    private Button lightAttackButton;
    @FXML
    private Button heavyAttackButton;
    @FXML
    private Button usePotionButton;
    @FXML
    private Button fleeButton;
    @FXML
    private Label opponentHP;
    @FXML
    private Label opponentLevel;
    @FXML
    private Label opponentName;
    @FXML
    private GridPane playerstatGridPane;
    @FXML
    private GridPane oppponentstatGridPane;
    @FXML
    private ListView equippedListView;

    IPlayer p = UI.getInstance().getDomainGame().getPlayer();

    List<IItem> equippedList;
    @FXML
    private ProgressBar opponentHealthBar;

    ICombatResponse c;
    @FXML
    private Text opponentHealthText;
    @FXML
    private Text opponentLevelText;
    @FXML
    private Text opponentNameText;
    @FXML
    private Text attackText;
    @FXML
    private Text defenceText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updatePlayerStats();
        updateOpponentStats();
        updatEquippedInventory();
//        imageForest.fitWidthProperty().bind(stage);
    }

    public void updatEquippedInventory() {
        for (IItem item : p.getEquipableInventory().getInventory()) { //loads players equipped inventory
            equippedList.add(item);
        }
        ObservableList<IItem> observableEquippedList = FXCollections.observableArrayList(equippedList); //makes observablelist of the arraylist, due to listviews restrictions
        equippedListView.setItems(observableEquippedList);
    }

    public void updatePlayerStats() { //Method for updating the players combat-screen stats
        attackText.setText(Integer.toString(p.getAttack()));
        defenceText.setText(Integer.toString(p.getArmor()));

    }

    public void updateOpponentStats() { //updates opponent entitys combat-screen stats
        ICharacterEntity o = c.getOpponent();
        opponentNameText.setText(o.getName());
        opponentLevelText.setText(Integer.toString(o.getLevel()));

        opponentHealthBar.setStyle("-fx-accent: green");
        double healthPercent = ((double) o.getHealth() / (double) o.getMaxHealth());
        opponentHealthBar.setProgress(healthPercent);
        opponentHealthText.setText(o.getHealth() + "/" + o.getMaxHealth());

    }

    public void fleeButtonPressed(ActionEvent event) {
        c = UI.getInstance().getDomainGame().getCombatResponse(2);

    }

    public void lightAttackButtonPressed(ActionEvent event) {
        c = UI.getInstance().getDomainGame().getCombatResponse(0);
        updateOpponentStats();
        updatePlayerStats();
    }

    public void heavyAttackButtonPressed(ActionEvent event) {
        c = UI.getInstance().getDomainGame().getCombatResponse(1);
        updateOpponentStats();
        updatePlayerStats();
    }
    
    public void usePotionButtonPressed (ActionEvent event) {
        
    }

}
