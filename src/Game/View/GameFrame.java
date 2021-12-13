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
package Game.View;

import javax.swing.*;

import Game.Controller.GameFrameController;

import java.awt.*;

public class GameFrame extends JFrame{

    private static final String DEF_TITLE = "Brick Destroy";

    private GameBoard gameBoard;
    private HomeMenu homeMenu;

    private LeaderboardFrame leaderboardFrame;

    private boolean gaming;

    private String tab;

    public GameFrame(){

        super();

        leaderboardFrame = new LeaderboardFrame(this);

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
        this.setResizable(false);
    }

    public void enableGameBoard(String mode, String tab){
        this.tab = tab;
        this.dispose();
        if(tab == "HomeMenu"){
            this.remove(homeMenu);
        } else {
            this.remove(gameBoard);
        }
        gameBoard = new GameBoard(this,mode);
        this.add(gameBoard,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(new GameFrameController(this));

    }

    public void enableLeaderBoard(){
        this.dispose();
        leaderboardFrame.initialize();

    }

    void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
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


    /**
     * @return JFrame return the leaderboardFrame
     */
    public JFrame getLeaderboardFrame() {
        return leaderboardFrame;
    }

    /**
     * @param leaderboardFrame the leaderboardFrame to set
     */
    public void setLeaderboardFrame(LeaderboardFrame leaderboardFrame) {
        this.leaderboardFrame = leaderboardFrame;
    }

}
