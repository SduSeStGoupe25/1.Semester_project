/*
 * class from where the map is updated to match the current loction of the player, and the movable ncp's
 */
package UI.GUI;

import Arq.ICharacterEntity;
import Arq.IMoveableNPC;
import Arq.INPC;
import Arq.IRoom;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Mikkel Pedersen
 */
public class MapSceneController implements Initializable {

    private String playerRoom = UI.getInstance().getDomainGame().getCurrentRoom();
    private String merlinRoom;
    private String princessRoom;
    @FXML
    private GridPane mapGrid;
    @FXML
    private ImageView mapImageView;
    private Image playerIcon = new Image("File:Icons/player-icon.png");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapImageView.fitWidthProperty().bind(mapGrid.widthProperty());
        mapImageView.fitHeightProperty().bind(mapGrid.heightProperty());
        merlinRoom = findMoveableNPC("Merlin");
        princessRoom = findMoveableNPC("Princess");

        updateMap();
    }

    public void updateMap() {
        switch (merlinRoom) {
            case "tower":
                break;
            case "citycenter":
                break;
            case "shop":
                break;
            case "tavern":
                break;
            case "castle":
                break;
            case "dead":
                break;
        }
        switch (princessRoom) {
            case "tower":
                break;
            case "citycenter":
                break;
            case "shop":
                break;
            case "tavern":
                break;
            case "castle":
                break;
            case "dead":
                break;
        }
        switch (playerRoom) {
            case "tower":
                break;
            case "citycenter":
                //3,4
                ImageView pi = new ImageView("File:Icons/player-icon.png");
                mapGrid.add(pi, 3, 4);
                pi.toFront();
                System.out.println("nfernfæenejef BILLEDE TILFØJET BAJERFEST");

                break;
            case "shop":
                break;
            case "tavern":
                break;
            case "castle":
                break;
            case "forrest":
                break;
            case "deepwoods":
                break;
            case "cave":
                break;
            case "excalibur":
                break;
            case "farm":
                break;
        }

    }

    public String findMoveableNPC(String name) {
        Map<String, IRoom> roomMap = UI.getInstance().getDomainGame().getRoomMap();
        for (Map.Entry<String, IRoom> room : roomMap.entrySet()) {
            for (ICharacterEntity npc : room.getValue().getCharactersInRoom()) {
                if (npc instanceof IMoveableNPC) {
                    if (npc.getName().equals(name)) {
                        return room.getKey();

                    }

                }

            }
        }
        return "dead";

    }
}
