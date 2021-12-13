package Game.View;


import javax.swing.*;

import Game.Controller.GameInfoController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.awt.*;
import java.io.IOException;

public class GameInfoFrame extends JFrame {
    
    private static final String DEF_TITLE = "Brick Destroy";
    private JFXPanel jfxPanel;
    private GameFrame gameFrame;

    public GameInfoFrame (GameFrame gameFrame){

        super();

        jfxPanel = new JFXPanel();

        this.gameFrame = gameFrame;

        this.setLayout(new BorderLayout());

        this.setUndecorated(false);

        Platform.runLater(() -> {
            initGameInfoFX(jfxPanel);
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

    private void initGameInfoFX(JFXPanel jfxPanel){ 

        this.add(jfxPanel, BorderLayout.CENTER);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GameFrame.class.getResource("GameInfo.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            GameInfoController  gameInfoController  = fxmlLoader.getController();

            gameInfoController .setGameFrame(gameFrame);

            jfxPanel.setScene(scene);
        
        } catch (IOException e) {

            e.printStackTrace();
            System.exit(1);

        }

    }


}
