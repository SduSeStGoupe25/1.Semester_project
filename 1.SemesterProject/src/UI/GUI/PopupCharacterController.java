/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Acq.ICharacterEntity;
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

/**
 * FXML Controller class
 *
 * @author madsd
 */
public class PopupCharacterController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private HBox buttonBox;
    @FXML
    private Button talkButton;
    @FXML
    private Button attackButton;
    @FXML
    private Label healthLabel;
    @FXML
    private VBox popUpVBox;
    
    private ICharacterEntity ce;
    private Popup popup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popUpVBox.getStylesheets().add(getClass().getResource("CSS/medieval.css").toExternalFor‌​m());
    }    
    
    private void update() {
        nameLabel.setText(ce.getName());
        levelLabel.setText("" + ce.getLevel());
        healthLabel.setText("" +ce.getHealth());
        
        if(ce.getId() == 3){
            Button shopBut = new Button("Shop");
            buttonBox.getChildren().add(shopBut);
            shopBut.setOnMouseClicked((event) -> {
                UI.getInstance().setState(UIState.SHOPSCREEN);
                popup.hide();
            });
        }
    }
    
    public void setCE(ICharacterEntity ce) {
        this.ce = ce;
        update();
    }
    
    public void setPopup(Popup popup){
        this.popup = popup;
    }

    @FXML
    private void talkClicked(MouseEvent event) {
    }

    @FXML
    private void attackClicked(MouseEvent event) {
        UI.getInstance().getDomainGame().getCombat().startCombat(ce);
        UI.getInstance().setState(UIState.COMBATSCREEN);
        popup.hide();
    }
}
