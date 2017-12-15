/*
 * class from where the map is updated to match the current loction of the player, and the movable ncp's
 */
package UI.GUI;

import Acq.ICharacterEntity;
import Acq.IMoveableNPC;
import Acq.IRoom;
import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 */
public class MapSceneController implements Initializable {

    private String playerRoom = UI.getInstance().getDomainGame().getCurrentRoom();
    private String merlinRoom;
    private String princessRoom;
    @FXML
    private GridPane mapGrid;
    @FXML
    private ImageView mapImageView;
    ImageView pw = new ImageView();
    ImageView princessView = new ImageView();
    ImageView merlinView = new ImageView();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        merlinRoom = findMoveableNPC("Merlin");
        princessRoom = findMoveableNPC("Princess");
        File f = new File("src/UI/GUI/Icons/player-icon.png");
                Image image = new Image(f.toURI().toString(), 50, 25, false, false);
                pw.setImage(image);
        
        File f1 = new File("src/UI/GUI/Icons/princess-icon.png");
                Image image1 = new Image(f1.toURI().toString(), 50, 25, false, false);
                princessView.setImage(image1);
                
       File f2 = new File("src/UI/GUI/Icons/merlin-icon.png");
                Image image2 = new Image(f2.toURI().toString(), 50, 25, false, false);
                merlinView.setImage(image2);

        updateMap();
    }

    void updateMap() {
        switch (merlinRoom) {
            case "tower":
                mapGrid.add(merlinView, 4, 7);
                break;
            case "citycenter":
                mapGrid.add(merlinView, 4, 3);
                break;
            case "shop":
                mapGrid.add(merlinView, 4, 1);
                break;
            case "tavern":
                mapGrid.add(merlinView, 6, 2);
                break;
            case "castle":
                mapGrid.add(merlinView, 4, 4);
                break;
            case "dead":
                break;
        }
        switch (princessRoom) {
            case "tower":
                mapGrid.add(princessView, 5,6);
                break;
            case "citycenter":
                mapGrid.add(princessView, 5,2);
                break;
            case "shop":
                mapGrid.add(princessView, 5,0);
                break;
            case "tavern":
                mapGrid.add(princessView, 7,2);
                break;
            case "castle":
                mapGrid.add(princessView, 5,4);
                break;
            case "dead":
                break;
        }
        switch (playerRoom) {
            case "tower":
                mapGrid.add(pw, 5, 7);
                break;
            case "citycenter":
                mapGrid.add(pw, 5, 3);
                break;
            case "shop":
                mapGrid.add(pw, 5, 1);
                break;
            case "tavern":
                mapGrid.add(pw, 7, 3);
                break;
            case "castle":
                mapGrid.add(pw, 5, 5);
                break;
            case "forrest":
                mapGrid.add(pw, 1, 3);
                break;
            case "deepwoods":
                mapGrid.add(pw, 1, 5);
                break;
            case "cave":
                mapGrid.add(pw, 1, 7);
                break;
            case "excalibur":
                mapGrid.add(pw, 7, 5);
                break;
            case "farm":
                mapGrid.add(pw, 3, 3);
                break;
        }

    }

    String findMoveableNPC(String name) {
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
