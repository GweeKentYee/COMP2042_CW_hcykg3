package Game.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONObject;

import Game.Model.Leaderboard;
import Game.View.GameFrame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LeaderboardController {

    private GameFrame gameFrame;

    @FXML
    private Button btnBack;

    @FXML
    private Label lblName;

    @FXML
    private Label lblName_1;

    @FXML
    private Label lblName_2;

    @FXML
    private Label lblName_3;

    @FXML
    private Label lblName_4;

    @FXML
    private Label lblName_5;

    @FXML
    private Label lblName_6;

    @FXML
    private Label lblName_7;

    @FXML
    private Label lblScore;

    @FXML
    private Label lblScore_1;

    @FXML
    private Label lblScore_2;

    @FXML
    private Label lblScore_3;

    @FXML
    private Label lblScore_4;

    @FXML
    private Label lblScore_5;

    @FXML
    private Label lblScore_6;

    @FXML
    private Label lblScore_7;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTime_1;

    @FXML
    private Label lblTime_2;

    @FXML
    private Label lblTime_3;

    @FXML
    private Label lblTime_4;

    @FXML
    private Label lblTime_5;

    @FXML
    private Label lblTime_6;

    @FXML
    private Label lblTime_7;

    @FXML
    private Label lblTitle;

    @FXML
    void btnOkClicked() {

        gameFrame.getLeaderboardFrame().dispose();
        gameFrame.dispose();

        new GameFrame().initialize();
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

        lblTitle.setText("LEADERBOARD");

        lblName.setText("Name");
        lblScore.setText("Score");
        lblTime.setText("Time");
        
        lblName_1.setText("1. "+(String) leaderboard.get(0).get("name"));
        lblName_2.setText("2. "+(String) leaderboard.get(1).get("name"));
        lblName_3.setText("3. "+(String) leaderboard.get(2).get("name"));
        lblName_4.setText("4. "+(String) leaderboard.get(3).get("name"));
        lblName_5.setText("5. "+(String) leaderboard.get(4).get("name"));  
        lblName_6.setText("6. "+(String) leaderboard.get(5).get("name"));  
        lblName_7.setText("7. "+(String) leaderboard.get(6).get("name"));

        lblScore_1.setText((String) leaderboard.get(0).get("score"));  
        lblScore_2.setText((String) leaderboard.get(1).get("score"));
        lblScore_3.setText((String) leaderboard.get(2).get("score")); 
        lblScore_4.setText((String) leaderboard.get(3).get("score"));
        lblScore_5.setText((String) leaderboard.get(4).get("score"));  
        lblScore_6.setText((String) leaderboard.get(5).get("score"));  
        lblScore_7.setText((String) leaderboard.get(6).get("score"));
        
        lblTime_1.setText((String) leaderboard.get(0).get("time"));
        lblTime_2.setText((String) leaderboard.get(1).get("time"));
        lblTime_3.setText((String) leaderboard.get(2).get("time"));
        lblTime_4.setText((String) leaderboard.get(3).get("time"));
        lblTime_5.setText((String) leaderboard.get(4).get("time"));
        lblTime_6.setText((String) leaderboard.get(5).get("time"));
        lblTime_7.setText((String) leaderboard.get(6).get("time"));

        btnBack.setText("Back");
        
    }


    /**
     * @return GameFrame return the gameFrame
     */
    public GameFrame getGameFrame() {
        return gameFrame;
    }

    /**
     * @param gameFrame the gameFrame to set
     */
    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

}
