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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author rasmusstamm
 */
public class UI extends Application implements IUI {

    static UI ui = null;

    private IDomainData domainData;
    private IGame domainGame;
    private UIState state;
    private Stage stage;
    private FXMLDocumentController mainGameController;

    public UI() {
        state = state.TITLESCREEN;
    }

    public static UI getInstance() {
        if (ui == null) {
            ui = new UI();
        }
        return ui;
    }

    IGame getDomainGame() {
        return domainGame;
    }

    IDomainData getDomainData() {
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
    }

    @Override
    public void injectDomainGame(IGame domainGame) {
        this.domainGame = domainGame;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(getClass().getResourceAsStream("Fonts/breathefire.otf"), 16);

        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("CSS/medieval.css").toExternalFor‌​m());

        stage.setTitle("World of Arthur: Game of The Year Edition");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public UIState getState() {
        return state;
    }

    public void setState(UIState state) {
        try {
            switch (state) {
                case TITLESCREEN:
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("TitleScreen.fxml"))));
                    break;
                case GAMESCREEN:
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                    Pane pane = loader.load();
                    mainGameController = loader.getController();
                    stage.setScene(new Scene(pane));
                    mainGameController.getBorderPane().setCenter(FXMLLoader.load(getClass().getResource("WorldScreen.fxml")));
                    pane.getStylesheets().add(getClass().getResource("CSS/medieval.css").toExternalFor‌​m());
                    break;
                case COMBATSCREEN:
                    mainGameController.getBorderPane().setCenter(FXMLLoader.load(getClass().getResource("CombatScreen.fxml")));
                    break;
                case INVENTORYSCREEN:
                    FXMLLoader InventoryLoader = new FXMLLoader(getClass().getResource("InventoryScreen.fxml"));
                    InventoryLoader.load();
                    ((InventoryScreenController) InventoryLoader.getController()).updateLists();
                    mainGameController.getBorderPane().setCenter(FXMLLoader.load(getClass().getResource("InventoryScreen.fxml")));
                    break;
                case MAPSCREEN:
                    mainGameController.getBorderPane().setCenter(FXMLLoader.load(getClass().getResource("MapScene.fxml")));
                    break;
                case QUESTSCREEN:
                    mainGameController.getBorderPane().setCenter(FXMLLoader.load(getClass().getResource("QuestlogScene.fxml")));
                    break;
                case SHOPSCREEN:
                    mainGameController.getBorderPane().setCenter(FXMLLoader.load(getClass().getResource("ShopkeeperScreen.fxml")));
                    break;
                case WORLDSCREEN:
                    mainGameController.getBorderPane().setCenter(FXMLLoader.load(getClass().getResource("WorldScreen.fxml")));
                    break;
                case GAMEOVERSCREEN:
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("GameOverWindow.fxml"))));
                    break;
                case GAMEWONSCREEN:
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("GameWonWindow.fxml"))));
            }
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    public FXMLDocumentController getMainController() {
        return mainGameController;
    }
}
