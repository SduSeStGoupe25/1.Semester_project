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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author madsd
 */
public class SpawnScreenController implements Initializable {

    private int amount;
    private int amountPerRaw = 4;

    @FXML
    private TilePane tilePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    void update() {
        amount = UI.getInstance().getDomainGame().getRoomMap().get(UI.getInstance().getDomainGame().getCurrentRoom()).getCharactersInRoom().size();

        tilePane.getChildren().clear();
        if (amount > 0) {
            tilePane.setHgap(10);
            tilePane.setVgap(10);

            int amountOfFields = (amount % 2 != 0) ? amount + 1 : amount;
            int rows = (amountOfFields / amountPerRaw == 0) ? 1 : amountOfFields / amountPerRaw;
            int cols = (amountOfFields / rows > amountPerRaw) ? amountOfFields / ++rows : amountOfFields / rows;
            
            tilePane.setPrefRows(rows);
            tilePane.setPrefColumns(cols);
            int count = 0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (count != amount) {
                        tilePane.getChildren().add(new UICharacter(UI.getInstance().getDomainGame().getRoomMap().get(UI.getInstance().getDomainGame().getCurrentRoom()).getCharactersInRoom().get(count)));
                        count++;
                    }
                }

            }
        }
    }
}
