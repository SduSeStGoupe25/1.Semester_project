package UI.GUI;

import Acq.ICharacterEntity;
import Acq.ICombatResponse;
import Acq.IItem;
import Acq.IPlayer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class CombatScreenController implements Initializable {

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
    private GridPane playerstatGridPane;
    @FXML
    private GridPane oppponentstatGridPane;
    @FXML
    private ListView equippedListView;

    private IPlayer p = UI.getInstance().getDomainGame().getPlayer();

    private List<IItem> equippedList;

    private StatsPanelController spc;

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
    @FXML
    private Pane imagePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        equippedList = new ArrayList<>();
        c = UI.getInstance().getDomainGame().getCombatResponse(3);
        updatePlayerStats();
        updateOpponentStats();
        updatEquippedInventory();

        File f = new File("Img/combat.png");
        Image image = new Image(f.toURI().toString());
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        imagePane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
        imagePane.getChildren().add(new UICharacter(c.getOpponent()));
    }

    public void updatEquippedInventory() {
        for (IItem item : p.getEquipableInventory().getInventory()) { //loads players equipped inventory
            equippedList.add(item);
        }
        ObservableList<IItem> observableEquippedList = FXCollections.observableArrayList(equippedList); //makes observablelist of the arraylist, due to listviews restrictions
        equippedListView.setItems(observableEquippedList);
    }

    public void updatePlayerStats() { //Method for updating the players combat-screen stats
        if (p.getHealth() < 1) {
            UI.getInstance().setState(UIState.GAMEOVERSCREEN);
        } else {
            attackText.setText(Integer.toString(p.getAttack()));
            defenceText.setText(Integer.toString(p.getArmor()));
        }
        UI.getInstance().getMainController().update(true);

    }

    public void updateOpponentStats() { //updates opponent entity's combat-screen stats
        ICharacterEntity o = c.getOpponent();
        if (o.getHealth() < 1) {
            exitCombat();
        }
        opponentNameText.setText(o.getName());
        opponentLevelText.setText(Integer.toString(o.getLevel()));

        opponentHealthBar.setStyle("-fx-accent: green");
        double healthPercent = ((double) o.getHealth() / (double) o.getMaxHealth());
        opponentHealthBar.setProgress(healthPercent);
        opponentHealthText.setText(o.getHealth() + "/" + o.getMaxHealth());

    }

    @FXML
    public void fleeButtonPressed(ActionEvent event) {
        c = UI.getInstance().getDomainGame().getCombatResponse(2);
        exitCombat();

    }

    @FXML
    public void lightAttackButtonPressed(ActionEvent event) {
        c = UI.getInstance().getDomainGame().getCombatResponse(0);
        updatePlayerStats();
        updateOpponentStats();
    }

    public void exitCombat() {
        UI.getInstance().setState(UIState.WORLDSCREEN);
    }

    @FXML
    public void heavyAttackButtonPressed(ActionEvent event) {
        c = UI.getInstance().getDomainGame().getCombatResponse(1);
        updatePlayerStats();
        updateOpponentStats();
    }

    @FXML
    public void usePotionButtonPressed(ActionEvent event) throws IOException {
        if (UI.getInstance().getDomainGame().usePotion() == false) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CombatPotionErrorWindow.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                root1.getStylesheets().add(getClass().getResource("CSS/medieval.css").toExternalFor‌​m());
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            updatePlayerStats();
        }
    }
}
