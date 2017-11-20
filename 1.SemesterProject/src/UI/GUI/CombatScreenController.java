/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        imageForest.fitWidthProperty().bind(stage);
    }    
    
}
