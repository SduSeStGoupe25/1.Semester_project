package UI.GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *Popup class
 */
public class UIPopup extends Popup {

    //https://stackoverflow.com/questions/18669209/javafx-what-is-the-best-way-to-display-a-simple-message
    public UIPopup(Initializable controller, String fxml, Stage stage, double x, double y) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            loader.setController(controller);
            this.getContent().add((Parent) loader.load());

            this.setAutoFix(true);
            this.setAutoHide(true);
            this.setHideOnEscape(true);

            this.setX(x + stage.getX() - this.getWidth() / 2);
            this.setY(y + stage.getY() - this.getHeight() / 2);
            this.show(stage);
        } catch (IOException ex) {
            Logger.getLogger(UICharacter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
