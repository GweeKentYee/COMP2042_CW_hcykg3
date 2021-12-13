package Game.Controller;

import Game.View.GameFrame;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class GameInfoController {

    private GameFrame gameFrame;

    @FXML
    private AnchorPane GameInfoPane;

    @FXML
    void BackToMenu() {

        gameFrame.getGameInfoFrame().dispose();
        gameFrame.dispose();

        new GameFrame().initialize();

    }

    @FXML
    public void initialize(){

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
