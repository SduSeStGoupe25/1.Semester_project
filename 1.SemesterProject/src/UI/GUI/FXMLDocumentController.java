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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author rasmusstamm
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private BorderPane borderPane;

    private BorderPane borderPaneDefault;
    @FXML
    private GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderPaneDefault = new BorderPane();
    }

    @FXML
    public void openInventoryButton(ActionEvent e) throws IOException {
        if (gridPane.getChildren().contains(borderPane)) {
            gridPane.getChildren().remove(borderPane);
            gridPane.add(borderPaneDefault, 1, 0);
        }
        borderPaneDefault.setCenter(FXMLLoader.load(getClass().getResource("InventoryScreen.fxml")));
    }

    @FXML
    private void setDefaultView(ActionEvent event) {
        if (gridPane.getChildren().contains(borderPaneDefault)) {
            gridPane.getChildren().remove(borderPaneDefault);
            gridPane.add(borderPane, 1, 0);
        }
    }

    @FXML
    private void openQuestlogButton(ActionEvent event) throws IOException {
        if (gridPane.getChildren().contains(borderPane)) {
            gridPane.getChildren().remove(borderPane);
            gridPane.add(borderPaneDefault, 1, 0);
        }
        borderPaneDefault.setCenter(FXMLLoader.load(getClass().getResource("QuestlogScene.fxml")));
    }

    @FXML
    private void quitGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void openMapButton(ActionEvent event) throws IOException {
        if (gridPane.getChildren().contains(borderPane)) {
            gridPane.getChildren().remove(borderPane);
            gridPane.add(borderPaneDefault, 1, 0);
        }
        borderPaneDefault.setCenter(FXMLLoader.load(getClass().getResource("MapScene.fxml")));
    }
}
