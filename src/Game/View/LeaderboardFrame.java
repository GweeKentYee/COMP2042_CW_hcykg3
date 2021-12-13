package Game.View;

import javax.swing.*;

import Game.Controller.LeaderboardController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.awt.*;
import java.io.IOException;

public class LeaderboardFrame extends JFrame{

    private static final String DEF_TITLE = "Brick Destroy";
    private JFXPanel jfxPanel;
    private GameFrame gameFrame;

    public LeaderboardFrame(GameFrame gameFrame){

        super();

        jfxPanel = new JFXPanel();

        this.gameFrame = gameFrame;

        this.setLayout(new BorderLayout());

        this.setUndecorated(false);

        Platform.runLater(() -> {
            initLeaderboardFX(jfxPanel);
        });

    }

    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setResizable(false);
        this.setVisible(true);
    }

    void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    private void initLeaderboardFX(JFXPanel jfxPanel){ 

        this.add(jfxPanel, BorderLayout.CENTER);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GameFrame.class.getResource("Leaderboard.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            LeaderboardController leaderboardController = fxmlLoader.getController();

            leaderboardController.setGameFrame(gameFrame);

            jfxPanel.setScene(scene);
        
        } catch (IOException e) {

            e.printStackTrace();
            System.exit(1);

        }

    }

    
}
