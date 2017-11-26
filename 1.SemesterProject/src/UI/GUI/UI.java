/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import Arq.IDomainData;
import Arq.IDomainGame;
import Arq.IGame;
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
    private IGame domainGame;

    private FXMLDocumentController mainController;

    public UI() {

    }

    public static UI getInstance() {
        if (ui == null) {
            ui = new UI();
        }
        return ui;
    }

    IGame getDomainGame() {
        System.out.println("MAINCONLLLOER &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& = " + mainController);
        if(mainController != null && domainGame.isInCombat()){
            System.out.println("COMABT%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            mainController.update("combat");
        }
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
        this.domainData = domainData;
        System.out.println(this.domainData);
    }

    @Override
    public void injectDomainGame(IGame domainGame) {
        this.domainGame = domainGame;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public FXMLDocumentController getMainController() {
        return mainController;
    }

    public void setMainController(FXMLDocumentController mainController) {
        this.mainController = mainController;
    }

}
