/*
 * class from where the map is updated to match the current loction of the player, and the movable ncp's
 */
package UI.GUI;

import Acq.ICharacterEntity;
import Acq.IMoveableNPC;
import Acq.INPC;
import Acq.IRoom;
import java.io.File;
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
    ImageView iw = new ImageView();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        merlinRoom = findMoveableNPC("Merlin");
        princessRoom = findMoveableNPC("Princess");
        File f = new File("src/UI/GUI/Icons/player-icon.png");
                Image image = new Image(f.toURI().toString(), 50, 25, false, false);
                
                iw.setImage(image);

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
                mapGrid.add(iw, 3, 9);
                break;
            case "citycenter":
                //3,4
                
                mapGrid.add(iw, 3, 4);

                break;
            case "shop":
                mapGrid.add(iw, 3, 1);
                break;
            case "tavern":
                //4,4
                mapGrid.add(iw, 5, 4);
                break;
            case "castle":
                //3,6
                mapGrid.add(iw, 3, 7);
                break;
            case "forrest":
                mapGrid.add(iw, 0, 4);
                break;
            case "deepwoods":
                mapGrid.add(iw, 1, 7);
                break;
            case "cave":
                mapGrid.add(iw, 2, 7);
                break;
            case "excalibur":
                mapGrid.add(iw, 5, 8);
                break;
            case "farm":
                mapGrid.add(iw, 2, 4);
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
