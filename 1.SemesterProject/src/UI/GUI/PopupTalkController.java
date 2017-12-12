package UI.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 */
public class PopupTalkController implements Initializable {

    @FXML
    private Label talkLabel;
    @FXML
    private Pane PopupPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PopupPane.getStylesheets().add(getClass().getResource("CSS/medieval.css").toExternalFor‌​m());
    }    
    
    /**
     * Called to set the text
     * @param text the text
     */
    public void setText(String text) {
        talkLabel.setText(text);
    }
    
}
