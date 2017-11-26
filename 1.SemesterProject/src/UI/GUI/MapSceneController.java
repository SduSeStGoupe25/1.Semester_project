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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Mikkel Pedersen
 */
public class MapSceneController implements Initializable {

    @FXML
    private BorderPane pane;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        image.fitWidthProperty().bind(pane.widthProperty());
////        //image.fitHeightProperty().bind(pane.heightProperty());
////        image.setPreserveRatio(true);
    }

}
