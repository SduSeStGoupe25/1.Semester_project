/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Acq.IDomainData;
import Acq.IDomainGame;
import Acq.IGame;
import Acq.IHighscoreWrapper;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
    private ListView<HighscoreBox> listHighScores;

    private UI ui;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ui = UI.getInstance();
        ObservableList<HighscoreBox> highscoreList = FXCollections.observableArrayList();
        for (IHighscoreWrapper h : ui.getDomainData().getHighScoreTable()){
            highscoreList.add(new HighscoreBox(h.getName(), h.getScore()));
        }
        
        listHighScores.setItems(highscoreList);
    }

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        //IGame g = ui.getDomainData().loadGame(true);
        ui.injectDomainGame(ui.getDomainData().loadGame(true));
        ui.setStage((Stage) nameBox.getScene().getWindow());
        ui.setState(UIState.GAMESCREEN);

        //set the player name
        if (nameBox.getText().isEmpty()) {
            ui.getDomainGame().setPlayerName("Arthur");
        } else {
            ui.getDomainGame().setPlayerName(nameBox.getText());
        }
    }

    @FXML
    private void exitGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void loadGame(ActionEvent event) {
        ui.injectDomainGame(ui.getDomainData().loadGame(false));
        ui.setStage((Stage) nameBox.getScene().getWindow());
        ui.setState(UIState.GAMESCREEN);
    }

    private class HighscoreBox extends HBox {

        Label nameLabel = new Label();
        Label scoreLabel = new Label();

        HighscoreBox(String nameText, int score) {
            super();

            nameLabel.setText(nameText);
            nameLabel.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(nameLabel, Priority.ALWAYS);

            scoreLabel.setText("" + score);
            scoreLabel.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(scoreLabel, Priority.ALWAYS);

            this.getChildren().addAll(nameLabel, scoreLabel);
        }
    }

}
