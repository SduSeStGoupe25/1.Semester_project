/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IPlayer;
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
    
    IPlayer player;
    
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
        System.out.println("UI");
        System.out.println(UI.getInstance());
        player = UI.getInstance().getDomainGame().getPlayer();
        System.out.println(player);
        hpBar.setStyle("-fx-accent: green");
        
        hungerBar.setStyle("-fx-accent: orange");
        
        xpBar.setStyle("-fx-accent: #5194ff");
        
        
        updateBars();
        
    }
    
    @FXML
    private void labelClick(MouseEvent event) {
    }
    
     void updateBars() {
         System.out.println("PLAYER ");
         System.out.println(player);
        if(player.getHealth() == 0) { 
            hpBar.setProgress(0);
            currentHealth.setText(player.getHealth() + "/" + player.getMaxHealth());
            
        }
            
        else { 
            
        double healthPercent =  (((double)player.getHealth() / player.getMaxHealth()));
        hpBar.setProgress(healthPercent);
        currentHealth.setText(player.getHealth() + "/" + player.getMaxHealth());
        }
        
        if (player.getHunger() == 0) { 
            hungerBar.setProgress(0);
            currentHunger.setText(player.getHunger() + "/" + player.getMaxHunger());
        }
        
        else {
           
        double hungerPercent = (((double)player.getHunger() / player.getMaxHunger()));
            
        hungerBar.setProgress(hungerPercent);
        currentHunger.setText(player.getHunger() + "/" + player.getMaxHunger());
        
        }
        
        if (player.getExp() == 0) { 
            xpBar.setProgress(0);
            currentExp.setText(player.getExp() + "/" + player.getExpToLevelUp());
        }
        else {      
        double expPercent = (((double)player.getExp() / player.getExpToLevelUp()));
        xpBar.setProgress(expPercent);
        currentExp.setText(player.getExp() + "/" + player.getExpToLevelUp());
        }
    }
    
}
