/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Game;

import javax.swing.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame{

    private static final String DEF_TITLE = "Brick Destroy";

    private GameBoard gameBoard;
    private HomeMenu homeMenu;

    private boolean gaming;

    public GameFrame(){

        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        homeMenu = new HomeMenu(this,new Dimension(450,400));

        this.add(homeMenu,BorderLayout.CENTER);

        this.setUndecorated(true);


    }

    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    public void enableGameBoard(String mode){
        this.dispose();
        this.remove(homeMenu);
        gameBoard = new GameBoard(this,mode);
        this.add(gameBoard,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(new GameFrameController(this));

    }

    public void enableLeaderBoard(){
        this.dispose();
        this.remove(homeMenu);
        final JFXPanel jfxPanel = new JFXPanel();
        this.setUndecorated(false);
        Platform.runLater(() -> {
            initLeaderboardFX(jfxPanel);
        });

    }

    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    private void initLeaderboardFX(JFXPanel jfxPanel){

        this.add(jfxPanel, BorderLayout.CENTER);   

        try {
            Parent root = FXMLLoader.load(GameFrame.class.getResource("Leaderboard.fxml"));
            Scene scene = new Scene(root);

            jfxPanel.setScene(scene);
            initialize();
        
        } catch (IOException e) {

            e.printStackTrace();
            System.exit(1);

        }

    }


    /**
     * @return GameBoard return the gameBoard
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * @param gameBoard the gameBoard to set
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * @return HomeMenu return the homeMenu
     */
    public HomeMenu getHomeMenu() {
        return homeMenu;
    }

    /**
     * @param homeMenu the homeMenu to set
     */
    public void setHomeMenu(HomeMenu homeMenu) {
        this.homeMenu = homeMenu;
    }

    /**
     * @return boolean return the gaming
     */
    public boolean isGaming() {
        return gaming;
    }

    /**
     * @param gaming the gaming to set
     */
    public void setGaming(boolean gaming) {
        this.gaming = gaming;
    }

}
