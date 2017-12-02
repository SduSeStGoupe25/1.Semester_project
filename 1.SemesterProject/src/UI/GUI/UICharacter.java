/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import java.io.File;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author madsd
 */
public class UICharacter extends ImageView {

    public UICharacter() {

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                UI.getInstance().setState(UIState.SHOPSCREEN);
            }
        });

        //super();
//        File file = new File("shopkeeperArt.png");
        File f = new File("Img/farm.png");
        Image image = new Image(f.toURI().toString(), 100, 100, false, false);
        this.setImage(image);
//        this.setFitHeight(9);
//        this.setFitWidth(9);
////        int r = (int) (Math.random() * 6000);
////        String c = "#" + r;
////        this.setStyle("-fx-background-color:" + c);
        System.out.println("image");
    }
}
