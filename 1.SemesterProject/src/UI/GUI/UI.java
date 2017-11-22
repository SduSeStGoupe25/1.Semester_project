/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IDomainData;
import Arq.IDomainGame;
import Arq.IUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author rasmusstamm
 */
public class UI extends Application implements IUI {

    static UI ui = null;

    private IDomainData domainData;
    private IDomainGame domainGame;

    public UI() {

    }

    public static UI getInstance() {
        if (ui == null) {
            ui = new UI();
        }
        return ui;
    }

    IDomainGame getDomainGame() {
        System.out.println("GAMEDOMAIN");
        System.out.println(domainGame);
        return domainGame;
    }

    IDomainData getDomainData() {
        System.out.println(domainData);
        return domainData;
    }

    @Override
    public void startApplication(String[] args) {
        ui = this;
        launch(args);
    }

    @Override
    public void injectDomainData(IDomainData domainData) {
        System.out.println("IN UI");
        System.out.println(domainData);
        this.domainData = domainData;
        System.out.println(this.domainData);
    }

    @Override
    public void injectDomainGame(IDomainGame domainGame) {
        this.domainGame = domainGame;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
