package Game.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONObject;

import Game.Model.Leaderboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LeaderboardController {

    @FXML
    private Label Place_1;

    @FXML
    private Label Place_10;

    @FXML
    private Label Place_2;

    @FXML
    private Label Place_3;

    @FXML
    private Label Place_4;

    @FXML
    private Label Place_5;

    @FXML
    private Label Place_6;

    @FXML
    private Label Place_7;

    @FXML
    private Label Place_8;

    @FXML
    private Label Place_9;

    @FXML
    void btnOkClicked(ActionEvent event) throws IOException, ParseException {

    }

    @FXML
    void tfName(ActionEvent event) {

    }

    @FXML
    void tfScore(ActionEvent event) {

    }

    @FXML
    public void initialize() throws IOException, ParseException{

        ArrayList<JSONObject> leaderboard = new ArrayList<>();

        leaderboard = Leaderboard.PlayerList();
        
        Place_1.setText((String) leaderboard.get(0).get("name") + " - " +(String) leaderboard.get(0).get("score"));
        Place_2.setText((String) leaderboard.get(1).get("name") + " - " +(String) leaderboard.get(1).get("score"));
        Place_3.setText((String) leaderboard.get(2).get("name") + " - " +(String) leaderboard.get(2).get("score"));
        Place_4.setText((String) leaderboard.get(3).get("name") + " - " +(String) leaderboard.get(3).get("score"));
        Place_5.setText((String) leaderboard.get(4).get("name") + " - " +(String) leaderboard.get(4).get("score"));
        Place_6.setText((String) leaderboard.get(5).get("name") + " - " +(String) leaderboard.get(5).get("score"));
        Place_7.setText((String) leaderboard.get(6).get("name") + " - " +(String) leaderboard.get(6).get("score"));
        Place_8.setText((String) leaderboard.get(7).get("name") + " - " +(String) leaderboard.get(7).get("score"));
        Place_9.setText((String) leaderboard.get(8).get("name") + " - " +(String) leaderboard.get(8).get("score"));
        Place_10.setText((String) leaderboard.get(9).get("name") + " - " +(String) leaderboard.get(9).get("score"));
  
    }

}
