package UI.GUI;

import Acq.ICharacterEntity;
import Acq.INPC;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class PopupCharacterController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private HBox buttonBox;
    @FXML
    private Label healthLabel;
    @FXML
    private VBox popUpVBox;

    //The CharacterEntity to show data about
    private ICharacterEntity ce; 
    //The popup
    private Popup popup;
    //The popups controller
    private PopupTalkController pController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popUpVBox.getStylesheets().add(getClass().getResource("CSS/medieval.css").toExternalFor‌​m());
    }

    /**
     * Called to update the popup content
     */
    private void update() {
        nameLabel.setText(ce.getName());
        levelLabel.setText("" + ce.getLevel());
        healthLabel.setText("" + ce.getHealth());

        //Checks if the character is a shopkeeper
        if (ce.getId() == 3) { // if it is
            Button shopBut = new Button("Shop");
            buttonBox.getChildren().add(shopBut);
            shopBut.setStyle("-fx-font-family: 'Breathe Fire';"); //adds font to button added in runtime
            shopBut.setOnMouseClicked((event) -> { //Lambda-udtryk for eventhandling af mouseclick
                UI.getInstance().setState(UIState.SHOPSCREEN);
                popup.hide();
            });
        }
    }

    /**
     * Called to set the character to show data about
     * @param ce the character
     */
    void setCE(ICharacterEntity ce) {
        this.ce = ce;
        update();
    }

    /**
     * Called to set the popup
     * @param popup the popup
     */
    void setPopup(Popup popup) {
        this.popup = popup;
    }

    @FXML
    private void talkClicked(MouseEvent event) {
        popup.hide();
        pController = new PopupTalkController();
        Stage stage = UI.getInstance().getStage();
        popup = new UIPopup(pController, "PopupTalk.fxml", stage, stage.getWidth() / 2.3, stage.getHeight() - (stage.getHeight() / 5));
        pController.setText(((INPC) ce).getTalk());
    }

    @FXML
    private void attackClicked(MouseEvent event) {
        UI.getInstance().getDomainGame().getCombat().startCombat(ce);
        UI.getInstance().setState(UIState.COMBATSCREEN);
        popup.hide();
    }
}
