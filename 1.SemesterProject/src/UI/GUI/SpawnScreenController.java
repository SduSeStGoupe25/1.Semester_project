/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Acq.IGame;
import Acq.IRoom;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author madsd
 */
public class SpawnScreenController implements Initializable {

    private int amount = 3;
    @FXML
    private StackPane tilePane;

    IGame game;
    IRoom currentRoom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = UI.getInstance().getDomainGame();
        update();
    }

    public void update() {
        tilePane.getChildren().clear();
        currentRoom = game.getRoomMap().get(game.getCurrentRoom());
        System.out.println("WIDTH " + tilePane.getWidth());
        //tilePane.setHgap(10);
        //tilePane.setVgap(10);

        int amountOfFields = currentRoom.getCharactersInRoom().size();
        //int amountOfFields = (amount % 2 != 0) ? amount++ : amount;
        System.out.println("AAA " + amountOfFields);

        if (amountOfFields < 5) {
            //tilePane.setPrefRows(1);
            //tilePane.setPrefColumns(amountOfFields);
            for (int c = 0; c < amountOfFields; c++) {
                tilePane.getChildren().add(new UICharacter(currentRoom.getCharactersInRoom().get(c), currentRoom));
                tilePane.getChildren().get(c).setTranslateX(100 * c);
                tilePane.getChildren().get(c).toBack();
                //tilePane.getChildren().add(new Button("HEJ"));
                System.out.println("HERE");
            }
        } else {
            for (int r = 0; r < amountOfFields / 2; r++) {
                //tilePane.setPrefColumns(r);
                //tilePane.setPrefRows(r);
                for (int c = 0; c < amountOfFields / 2; c++) {
                    tilePane.getChildren().add(new UICharacter(currentRoom.getCharactersInRoom().get(c), currentRoom));
                }
            }
        }
        System.out.println("WIDTH " + tilePane.getLayoutBounds());

    }

//    private void cleanPane() {
//        while (gridPane.getRowConstraints().size() > 0) {
//            gridPane.getRowConstraints().remove(0);
//        }
//
//        while (gridPane.getColumnConstraints().size() > 0) {
//            gridPane.getColumnConstraints().remove(0);
//        }
//    }
}
