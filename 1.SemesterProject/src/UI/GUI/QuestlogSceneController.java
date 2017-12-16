package UI.GUI;

import Acq.IGame;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 */
public class QuestlogSceneController implements Initializable {

    @FXML
    private ListView<String> QuestListView;
    @FXML
    private TextArea QuestTextArea;
    private IGame game;
    @FXML
    private TextField expField;
    @FXML
    private TextField goldField;
    @FXML
    private TextField questGiverField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = UI.getInstance().getDomainGame();
        QuestListView.setItems(FXCollections.observableList(completedQuests()));
    }
    /**
     * Method used to gather all the quests the Player has completed. It adds (completed) to quest names, to specify they have already been completed.
     * @return Returns the completed quests as a List
     */
    private List completedQuests() {
        int completedQuests = 0;
        List<String> completedQuestNames = new ArrayList<>();
        while (completedQuests < game.getPlayer().getQuestsCompleted()) {
            completedQuestNames.add(game.getPlayer().getMainQuest().get(completedQuests).getName() + " (Completed)");
            completedQuests++;
        }
        completedQuestNames.add(game.getPlayer().getMainQuest().get(game.getPlayer().getQuestsCompleted()).getName());
        return completedQuestNames;
    }

    /**
     * This method updates the quest UI, showing quest description, XP, gold and giver.
     * @param event Fires the event upon clicking with the mouse
     */
    @FXML
    private void setQuestText(MouseEvent event) {
        if (QuestListView.getSelectionModel().getSelectedItem() == null) { //prevents InvocationTargetException in case of the listview getting clicked withput an item being chosen
            return;
        }
        QuestTextArea.setText(game.getPlayer().getMainQuest().get(QuestListView.getSelectionModel().getSelectedIndex()).getDescription());
        expField.setText("" + game.getPlayer().getMainQuest().get(QuestListView.getSelectionModel().getSelectedIndex()).getExp());
        goldField.setText("" + game.getPlayer().getMainQuest().get(QuestListView.getSelectionModel().getSelectedIndex()).getGold());
        questGiverField.setText(game.getPlayer().getMainQuest().get(QuestListView.getSelectionModel().getSelectedIndex()).getGiver());
    }

    /**
     * Method used to complete a quest from UI
     * @param event Fires when a quest has been selected, and button is pressed
     */
    @FXML
    private void completeQuest(ActionEvent event) {
        int questIndex = QuestListView.getSelectionModel().getSelectedIndex();
        if (questIndex == game.getPlayer().getQuestsCompleted()) {
            game.getPlayer().getCompleteQuest(game.getCurrentRoom());
            if (game.getPlayer().getQuestsCompleted() >= game.getPlayer().getMainQuest().size()) {
                UI.getInstance().setState(UIState.GAMEWONSCREEN);
            }else{
                UI.getInstance().setState(UIState.QUESTSCREEN);
                UI.getInstance().getMainController().update(false);
            }
            
        } else {
            System.out.println("Select the right quest!");
        }
    }
}
