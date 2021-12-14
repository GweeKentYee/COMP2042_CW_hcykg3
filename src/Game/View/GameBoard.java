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

import Game.Controller.GameBoardController;
import Game.Model.Ball;
import Game.Model.Brick;
import Game.Model.GameTimer;
import Game.Model.Leaderboard;
import Game.Model.Player;
import Game.Model.Wall;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.io.IOException;
import java.text.ParseException;

public class GameBoard extends JComponent{

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String BACK = "Back to Menu";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private GameFrame owner;
    private String mode;
    
    private Timer gameTimer;
    private GameTimer timer;

    private Wall wall;

    private String message;
    private String extraMessage;

    private boolean showPauseMenu;

    private Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle backButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

    private DebugConsole debugConsole;

    private GameBoardController gameBoardController;


    public GameBoard(GameFrame owner, String mode){
        super();
        strLen = 0;
        showPauseMenu = false;

        this.owner = owner;
        this.mode = mode;

        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);


        this.initialize();

        timer = new GameTimer();

        message = "";
        extraMessage = "";

        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),6/2,new Point(300,430), timer);

        debugConsole = new DebugConsole(owner,wall,this);
        //initialize the first level
        wall.nextLevel();
        
        gameTimer = new Timer(10,e ->{

            message = "";

            timer.setGameStatus(true);
            wall.move();
            wall.findImpacts();

            if (mode == "training"){
                message = String.format("Bricks: %d Balls: %d",wall.getBrickCount(),wall.getBallCount());
            } else {
                extraMessage = String.format("Bricks: %d   Balls: %d   Level: %d   Score: %s   Timer: %s:%s",wall.getBrickCount(),wall.getBallCount(),wall.getLevel(),Wall.getScore(),timer.getDdMinute(),timer.getDdSecond());
                if (timer.getMinute() == 0 && timer.getSecond() == 0){
                    gameTimer.stop();
                    timer.setGameStatus(false);
                    message = "Game over";
                    if (mode != "training"){
                        try {
                            if (Leaderboard.Check(wall.getLevel(), Wall.getScore(), timer.getDdMinute() + ":" + timer.getDdSecond()) == true){
                                JFrame popup = new JFrame(); 
    
                                String userName = (String)JOptionPane.showInputDialog(
                                    popup, "New Record!\n"
                                    + "Name:",
                                    "New Record",
                                    JOptionPane.PLAIN_MESSAGE
                                );

                                while (userName.length() > 8){

                                    userName = (String)JOptionPane.showInputDialog(
                                        popup, 
                                        "Please enter less than 9 characters\n"
                                        + "Name:",
                                        "New Record",
                                        JOptionPane.PLAIN_MESSAGE
                                    );

                                }
    
                                if (userName != null){
    
                                    Leaderboard.AddPlayer(userName,wall.getLevel(), Wall.getScore(),timer.getDdMinute() + ":" + timer.getDdSecond());
    
                                }
                                
                            } 
    
                            owner.enableLeaderBoard();
    
                        } catch (IOException | ParseException e1) {
                            e1.printStackTrace();
                        }

                        Wall.setScore(0);

                    }
    
                } 
            }

            if(wall.isBallLost()){
                wall.ballReset();
                gameTimer.stop();
                timer.setGameStatus(false);
                if(wall.ballEnd()){
                    wall.wallReset();
                    message = "Game over";
                    if (mode != "training"){
                        try {
                            if (Leaderboard.Check(wall.getLevel(), Wall.getScore(), timer.getDdMinute() + ":" + timer.getDdSecond()) == true){

                                JFrame popup = new JFrame(); 
        
                                String userName = (String)JOptionPane.showInputDialog(
                                    popup, "New Highscore!\n"
                                    + "Name:",
                                    "Highscore",
                                    JOptionPane.PLAIN_MESSAGE
                                );

                                while (userName.length() > 8){

                                    userName = (String)JOptionPane.showInputDialog(
                                        popup, 
                                        "Please enter less than 9 characters\n"
                                        + "Name:",
                                        "New Record",
                                        JOptionPane.PLAIN_MESSAGE
                                    );

                                }
        
                                if (userName != null){
        
                                    Leaderboard.AddPlayer(userName, wall.getLevel(), Wall.getScore(),timer.getDdMinute() + ":" + timer.getDdSecond());
        
                                }       

                            }

                            owner.enableLeaderBoard();

                        } catch (IOException | ParseException e1) {
                            e1.printStackTrace();
                        }

                        Wall.setScore(0);
                    }
                }
            }
            else if(wall.isDone()){
                if(wall.hasLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    timer.setGameStatus(false);
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                    timer.setGameStatus(false);
                }
            }

            repaint();
        });

    }

    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        gameBoardController = new GameBoardController(this);
        this.addKeyListener(gameBoardController);
        this.addMouseListener(gameBoardController);
        this.addMouseMotionListener(gameBoardController);
    }

    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,250,225);

        g2d.setColor(Color.BLUE);
        g2d.drawString(extraMessage,170,225);

        drawBall(wall.getBall(),g2d);

        for(Brick b : wall.getBricks())
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.getPlayer(),g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrickShape());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrickShape());


        g2d.setColor(tmp);
    }

    private void drawBall(Ball ball,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getballShape();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawPlayer(Player p,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(backButtonRect == null){
            backButtonRect = (Rectangle) continueButtonRect.clone();
            backButtonRect.setLocation(x,y-backButtonRect.height);
        }

        g2d.drawString(BACK,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    public void onLostFocus() {
        gameTimer.stop();
        extraMessage = "";
        message = "Focus Lost";
        repaint();
    }

    /**
     * @return Timer return the gameTimer
     */
    public Timer getGameTimer() {
        return gameTimer;
    }

    /**
     * @param gameTimer the gameTimer to set
     */
    public void setGameTimer(Timer gameTimer) {
        this.gameTimer = gameTimer;
    }

    /**
     * @return Wall return the wall
     */
    public Wall getWall() {
        return wall;
    }

    /**
     * @param wall the wall to set
     */
    public void setWall(Wall wall) {
        this.wall = wall;
    }

    /**
     * @return String return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return boolean return the showPauseMenu
     */
    public boolean isShowPauseMenu() {
        return showPauseMenu;
    }

    /**
     * @param showPauseMenu the showPauseMenu to set
     */
    public void setShowPauseMenu(boolean showPauseMenu) {
        this.showPauseMenu = showPauseMenu;
    }

    /**
     * @return Font return the menuFont
     */
    public Font getMenuFont() {
        return menuFont;
    }

    /**
     * @param menuFont the menuFont to set
     */
    public void setMenuFont(Font menuFont) {
        this.menuFont = menuFont;
    }

    /**
     * @return Rectangle return the continueButtonRect
     */
    public Rectangle getContinueButtonRect() {
        return continueButtonRect;
    }

    /**
     * @param continueButtonRect the continueButtonRect to set
     */
    public void setContinueButtonRect(Rectangle continueButtonRect) {
        this.continueButtonRect = continueButtonRect;
    }

    /**
     * @return Rectangle return the backButtonRect
     */
    public Rectangle getBackButtonRect() {
        return backButtonRect;
    }

    /**
     * @param exitButtonRect the exitButtonRect to set
     */
    public void setBackButtonRect(Rectangle backButtonRect) {
        this.backButtonRect = backButtonRect;
    }

    /**
     * @return Rectangle return the restartButtonRect
     */
    public Rectangle getRestartButtonRect() {
        return restartButtonRect;
    }

    /**
     * @param restartButtonRect the restartButtonRect to set
     */
    public void setRestartButtonRect(Rectangle restartButtonRect) {
        this.restartButtonRect = restartButtonRect;
    }

    /**
     * @return int return the strLen
     */
    public int getStrLen() {
        return strLen;
    }

    /**
     * @param strLen the strLen to set
     */
    public void setStrLen(int strLen) {
        this.strLen = strLen;
    }

    /**
     * @return DebugConsole return the debugConsole
     */
    public DebugConsole getDebugConsole() {
        return debugConsole;
    }

    /**
     * @param debugConsole the debugConsole to set
     */
    public void setDebugConsole(DebugConsole debugConsole) {
        this.debugConsole = debugConsole;
    }


    /**
     * @return String return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return GameFrame return the owner
     */
    public GameFrame getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(GameFrame owner) {
        this.owner = owner;
    }
    

    /**
     * @return GameTimer return the timer
     */
    public GameTimer getTimer() {
        return timer;
    }

    /**
     * @param timer the timer to set
     */
    public void setTimer(GameTimer timer) {
        this.timer = timer;
    }


}
