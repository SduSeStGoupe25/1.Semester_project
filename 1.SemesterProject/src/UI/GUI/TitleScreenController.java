/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IDomainData;

import Arq.IDomainGame;
import Arq.IGame;
import Arq.IHighscoreWrapper;
import Domain.DomainGame;
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
    private ListView<IHighscoreWrapper> listHighScores;

    private UI ui;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ui = UI.getInstance();
        ArrayList<IHighscoreWrapper> dataHighScore = new ArrayList<>(ui.getDomainData().getHighScoreTable());
        ObservableList<IHighscoreWrapper> highscoreList = FXCollections.observableArrayList(dataHighScore);
        listHighScores.setItems(highscoreList);
    }

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        IGame g = ui.getDomainData().loadGame(true);
        ui.injectDomainGame(ui.getDomainData().loadGame(false));
        ui.setStage((Stage) nameBox.getScene().getWindow());
        ui.setState(UIState.GAMESCREEN);
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
