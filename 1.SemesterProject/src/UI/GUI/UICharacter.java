/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Acq.ICharacterEntity;
import Acq.INPC;
import Acq.IRoom;
import java.awt.event.ActionListener;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author madsd
 */
public class UICharacter extends SplitPane {

    private ICharacterEntity ce;
    private IRoom room;
    private VBox vb;

    public UICharacter(ICharacterEntity ce, IRoom room) {
        this.ce = ce;
        this.room = room;
        ImageView iw = new ImageView();
        vb = new VBox();
        Label name = new Label(ce.getName());
        vb.getChildren().add(name);
        this.getItems().add(iw);

        switch (ce.getId()) {

            case 3:
                Button btnShop = new Button("Shop");
                btnShop.setOnAction((e) -> {
                    startShop();
                });
                vb.getChildren().addAll(btnShop);
            case 4:

            case 1:
                Button btnTalk = new Button("Talk");
                btnTalk.setOnAction((e) -> {
                    startTalk();
                });
                vb.getChildren().addAll(btnTalk);
            case 0:
                Button btnFight = new Button("Attack");
                btnFight.setOnAction((e) -> {
                    startCombat();
                });
                vb.getChildren().addAll(btnFight);
                break;
        }

        this.setOnMouseEntered((e) -> {
            showButtons();
        });

        this.setOnMouseExited((e) -> {
            hideButtons();
        });

        //super();
//        File file = new File("shopkeeperArt.png");
        File f = new File("Img/NPC/merlin.png");
        Image image = new Image(f.toURI().toString(), 100, 100, false, false);
        iw.setImage(image);
//        this.setFitHeight(9);
//        this.setFitWidth(9);
////        int r = (int) (Math.random() * 6000);
////        String c = "#" + r;
////        this.setStyle("-fx-background-color:" + c);
        System.out.println("image");
        showButtons();
        hideButtons();
    }

    private void showButtons() {
        this.getItems().add(vb);
        this.setTranslateX((vb.getWidth() / 2) + this.getTranslateX());
    }

    private void hideButtons() {
        this.getItems().remove(vb);
        this.setTranslateX((-vb.getWidth() / 2) + this.getTranslateX());
    }

    private void startCombat() {
        UI.getInstance().getDomainGame().getCombat().startCombat(ce, room);
        UI.getInstance().setState(UIState.COMBATSCREEN);
    }

    private void startTalk() {
        System.out.println(((INPC) ce).getTalk());
    }

    private void startShop() {
        UI.getInstance().setState(UIState.SHOPSCREEN);
    }
}
