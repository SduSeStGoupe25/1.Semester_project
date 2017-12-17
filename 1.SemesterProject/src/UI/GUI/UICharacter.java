package UI.GUI;

import Acq.ICharacterEntity;
import java.io.File;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;

/**
 * UICharacter class
 */
public class UICharacter extends ImageView {

    UICharacter(ICharacterEntity ce) {

        //Listener on mouse clicked on imageview
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!UI.getInstance().getDomainGame().isInCombat()) {
                    PopupCharacterController pController = new PopupCharacterController();
                    Popup popup = new UIPopup(pController, "PopupCharacter.fxml", UI.getInstance().getStage(), event.getSceneX(), event.getSceneY());
                    pController.setCE(ce);
                    pController.setPopup(popup);
                }
            }
        });

        //Sets the character image
        File f = new File("Img/NPC/" + ce.getName() + ".png");
        if (!f.exists()) {
            f = new File("Img/place.png");
        }
        Image image = new Image(f.toURI().toString(), 100, 100, false, false);
        this.setImage(image);

    }
}
