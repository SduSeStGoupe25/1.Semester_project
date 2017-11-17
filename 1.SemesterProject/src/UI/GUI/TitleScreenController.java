/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        Stage stage = (Stage)nameBox.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"))));
    }

    @FXML
    private void exitGame(ActionEvent event) {
        System.exit(0);
    }
    
}
