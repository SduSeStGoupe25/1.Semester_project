/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IDomainData;

import Arq.IDomainGame;

import Domain.DomainGame;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mikkel Pedersen
 */
public class TitleScreenController implements Initializable {

    @FXML
    private TextField nameBox;
    @FXML
    private ListView<?> listHighScores;
    
    private UI ui;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ui = UI.getInstance();
    }    

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        System.out.println("STARTGAME...........................");
        IDomainGame g = ui.getDomainData().loadGame(true);
        System.out.println("..................");
        System.out.println(g);
        System.out.println("....................");
        ui.injectDomainGame(ui.getDomainData().loadGame(false));
        Stage stage = (Stage)nameBox.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"))));
    }

    @FXML
    private void exitGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void loadGame(ActionEvent event) {
        ui.injectDomainGame(ui.getDomainData().loadGame(true));
    }
    
}
