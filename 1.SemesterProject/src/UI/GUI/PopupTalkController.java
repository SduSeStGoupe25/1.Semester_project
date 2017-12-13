package UI.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 */
public class PopupTalkController implements Initializable {

    @FXML
    private Label talkLabel;
    @FXML
    private ScrollPane PopupPane;

    /**
     * Initializes the controller class.
     * https://stackoverflow.com/questions/24700765/how-to-hide-a-pannable-scrollbar-in-javafx
     * https://stackoverflow.com/questions/24472170/how-can-we-make-text-auto-scroll-continuious-loop
     * https://stackoverflow.com/questions/22952531/scrollpanes-in-javafx-8-always-have-gray-background
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PopupPane.getStylesheets().add(getClass().getResource("CSS/medieval.css").toExternalFor‌​m());
        
        PopupPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        PopupPane.setVbarPolicy(ScrollBarPolicy.NEVER);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyValue kv = new KeyValue(PopupPane.hvalueProperty(), PopupPane.getHmax());
        KeyFrame kf = new KeyFrame(Duration.millis(20000), kv);
        timeline.getKeyFrames().addAll(kf);
        timeline.play();
    }

    /**
     * Called to set the text
     *
     * @param text the text
     */
    public void setText(String text) {
        talkLabel.setText(text);
    }

}
