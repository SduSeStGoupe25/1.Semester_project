/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Acq.ICharacterEntity;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author madsd
 */
public class UICharacter extends ImageView {

    private ICharacterEntity ce;
    private Popup popup;

    public UICharacter(ICharacterEntity ce) {

        this.ce = ce;

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showPopupMessage(UI.getInstance().getStage(), event);
            }
        });

        File f = new File("Img/NPC/" + ce.getName() + ".png");
        if (!f.exists()) {
            f = new File("Img/place.png");
        }
        Image image = new Image(f.toURI().toString(), 100, 100, false, false);
        this.setImage(image);
        
        System.out.println("image");
    }

    public Popup createPopup() {
        try {
            popup = new Popup();
            PopupCharacterController controller = new PopupCharacterController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupCharacter.fxml"));
            loader.setController(controller);
            popup.getContent().add((Parent) loader.load());
            controller.setCE(ce);
            controller.setPopup(popup);

            popup.setAutoFix(true);
            popup.setAutoHide(true);
            popup.setHideOnEscape(true);

            return popup;
        } catch (IOException ex) {
            Logger.getLogger(UICharacter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void showPopupMessage(Stage stage, MouseEvent a) {
        Popup popup = createPopup();
        popup.setX(a.getSceneX() + stage.getX() - popup.getWidth() / 2);
        popup.setY(a.getSceneY() + stage.getY() - popup.getHeight() / 2);
        popup.show(stage);
    }
}
