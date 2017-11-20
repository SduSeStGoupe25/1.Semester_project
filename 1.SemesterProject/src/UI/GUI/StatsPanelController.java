/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Domain.DomainFacade;
import Domain.Entity.Player;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author rasmusstamm
 */
public class StatsPanelController implements Initializable {
    
    @FXML
    private ProgressBar hpBar;
    @FXML
    private ProgressBar hungerBar;
    @FXML
    private ProgressBar xpBar;
    @FXML
    private Label armourLabel;
    @FXML
    private Label attackLabel;
    @FXML
    private Label hpLabel;
    @FXML
    private Label hungerLabel;
    @FXML
    private Label xpLabel;
    
    DomainFacade game;
    Player player;
    @FXML
    private Text currentHealth;
    @FXML
    private Text currentHunger;
    @FXML
    private Text currentExp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = UI.getInstance().getGame();
        player = game.getPlayer();
        hpBar.setStyle("-fx-accent: green");
        
        hungerBar.setStyle("-fx-accent: orange");
        
        xpBar.setStyle("-fx-accent: #5194ff");
        
        updateBars();
        // TODO
    }
    
    @FXML
    private void labelClick(MouseEvent event) {
    }
    
    private void updateBars() {
        hpBar.setProgress(player.getHealthPercent());
        currentHealth.setText(player.getHealth() + "/" + player.getMaxHealth());
        
        hungerBar.setProgress(player.getHungerPercent());
        currentHunger.setText(player.getHunger() + "/" + player.getMaxHunger());
        
        xpBar.setProgress(player.getExpPercent());
        currentExp.setText(player.getExp() + "/" + player.getExpToLevelUp());
    }
    
}
