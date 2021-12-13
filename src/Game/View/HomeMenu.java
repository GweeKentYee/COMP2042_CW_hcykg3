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

import Game.Controller.HomeMenuController;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


public class HomeMenu extends JComponent {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Refactored Version";
    private static final String START_TEXT = "Start";
    private static final String TRAINING_TEXT = "Training";
    private static final String LEADERBOARD_TEXT = "Leaderboard";
    private static final String INFO_TEXT = "Info";
    private static final String EXIT_TEXT = "Exit";

    private static final Color BG_COLOR = Color.CYAN.darker();
    private static final Color TEXT_COLOR = new Color(16, 52, 166);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    private Rectangle menuFace;
    private Rectangle trainingButton;
    private Rectangle startButton;
    private Rectangle infoButton;
    private Rectangle exitButton;
    private Rectangle leaderboardButton;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean startClicked;
    private boolean trainingClicked;
    private boolean infoClicked;
    private boolean exitClicked;
    private boolean leaderboardClicked;

    private HomeMenuController homeMenuController;


    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        homeMenuController = new HomeMenuController(this);

        this.addMouseListener(homeMenuController);
        this.addMouseMotionListener(homeMenuController);

        this.owner = owner;


        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension((area.width / 3) + 70, area.height / 12);
        startButton = new Rectangle(btnDim);
        trainingButton = new Rectangle(btnDim);
        exitButton = new Rectangle(btnDim);
        leaderboardButton = new Rectangle(btnDim);
        infoButton = new Rectangle(btnDim);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.PLAIN,trainingButton.height-8);

    }


    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);
        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();

        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);
    }

    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);


    }

    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D StartRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D TrainingRect = buttonFont.getStringBounds(TRAINING_TEXT,frc);
        Rectangle2D ExitRect = buttonFont.getStringBounds(EXIT_TEXT,frc);
        Rectangle2D LeaderboardRect = buttonFont.getStringBounds(LEADERBOARD_TEXT,frc);
        Rectangle2D InfoRect = buttonFont.getStringBounds(INFO_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - startButton.height) * 0.5);



        startButton.setLocation(x,y);

        x = (int)(startButton.getWidth() - StartRect.getWidth()) / 2;
        y = (int)(startButton.getHeight() - StartRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.8);

        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(startButton);
            g2d.drawString(START_TEXT,x,y);
        }

        x = startButton.x;
        y = startButton.y;

        int space = 45;

        y += space;



        trainingButton.setLocation(x,y);

        x = (int)(trainingButton.getWidth() - TrainingRect.getWidth()) / 2;
        y = (int)(trainingButton.getHeight() - TrainingRect.getHeight()) / 2;

        x += trainingButton.x;
        y += trainingButton.y + (trainingButton.height * 0.8);

        if(trainingClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(trainingButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(TRAINING_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(trainingButton);
            g2d.drawString(TRAINING_TEXT,x,y);
        }

        x = startButton.x;
        y = trainingButton.y;

        y += space;

        leaderboardButton.setLocation(x,y);

        x = (int)(leaderboardButton.getWidth() - LeaderboardRect.getWidth()) / 2;
        y = (int)(leaderboardButton.getHeight() - LeaderboardRect.getHeight()) / 2;

        x += leaderboardButton.x;
        y += leaderboardButton.y + (trainingButton.height * 0.8);

        if(leaderboardClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(leaderboardButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(LEADERBOARD_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(leaderboardButton);
            g2d.drawString(LEADERBOARD_TEXT,x,y);
        }

        x = trainingButton.x;
        y = leaderboardButton.y;

        y+= space;


        infoButton.setLocation(x,y);

        x = (int)(infoButton.getWidth() - InfoRect.getWidth()) / 2;
        y = (int)(infoButton.getHeight() - InfoRect.getHeight()) / 2;

        x += infoButton.x;
        y += infoButton.y + (leaderboardButton.height * 0.8);

        if(infoClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(infoButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(INFO_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(infoButton);
            g2d.drawString(INFO_TEXT,x,y);
        }

        x = leaderboardButton.x;
        y = infoButton.y;

        y+= space;


        exitButton.setLocation(x,y);

        x = (int)(exitButton.getWidth() - ExitRect.getWidth()) / 2;
        y = (int)(exitButton.getHeight() - ExitRect.getHeight()) / 2;

        x += exitButton.x;
        y += exitButton.y + (leaderboardButton.height * 0.8);

        if(exitClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(exitButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(EXIT_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(exitButton);
            g2d.drawString(EXIT_TEXT,x,y);
        }

        
    }


    /**
     * @return Rectangle return the menuFace
     */
    public Rectangle getMenuFace() {
        return menuFace;
    }

    /**
     * @param menuFace the menuFace to set
     */
    public void setMenuFace(Rectangle menuFace) {
        this.menuFace = menuFace;
    }

    /**
     * @return Font return the greetingsFont
     */
    public Font getGreetingsFont() {
        return greetingsFont;
    }

    /**
     * @param greetingsFont the greetingsFont to set
     */
    public void setGreetingsFont(Font greetingsFont) {
        this.greetingsFont = greetingsFont;
    }

    /**
     * @return Font return the gameTitleFont
     */
    public Font getGameTitleFont() {
        return gameTitleFont;
    }

    /**
     * @param gameTitleFont the gameTitleFont to set
     */
    public void setGameTitleFont(Font gameTitleFont) {
        this.gameTitleFont = gameTitleFont;
    }

    /**
     * @return Font return the creditsFont
     */
    public Font getCreditsFont() {
        return creditsFont;
    }

    /**
     * @param creditsFont the creditsFont to set
     */
    public void setCreditsFont(Font creditsFont) {
        this.creditsFont = creditsFont;
    }

    /**
     * @return Font return the buttonFont
     */
    public Font getButtonFont() {
        return buttonFont;
    }

    /**
     * @param buttonFont the buttonFont to set
     */
    public void setButtonFont(Font buttonFont) {
        this.buttonFont = buttonFont;
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
     * @return Rectangle return the startButton
     */
    public Rectangle getStartButton() {
        return startButton;
    }

    /**
     * @param startButton the startButton to set
     */
    public void setStartButton(Rectangle startButton) {
        this.startButton = startButton;
    }

    /**
     * @return boolean return the startClicked
     */
    public boolean isStartClicked() {
        return startClicked;
    }

    /**
     * @param startClicked the startClicked to set
     */
    public void setStartClicked(boolean startClicked) {
        this.startClicked = startClicked;
    }

    /**
     * @return Rectangle return the trainingButton
     */
    public Rectangle getTrainingButton() {
        return trainingButton;
    }

    /**
     * @param trainingButton the trainingButton to set
     */
    public void setTrainingButton(Rectangle trainingButton) {
        this.trainingButton = trainingButton;
    }

    /**
     * @return boolean return the trainingClicked
     */
    public boolean isTrainingClicked() {
        return trainingClicked;
    }

    /**
     * @param trainingClicked the trainingClicked to set
     */
    public void setTrainingClicked(boolean trainingClicked) {
        this.trainingClicked = trainingClicked;
    }

    /**
     * @return Rectangle return the exitButton
     */
    public Rectangle getExitButton() {
        return exitButton;
    }

    /**
     * @param exitButton the exitButton to set
     */
    public void setExitButton(Rectangle exitButton) {
        this.exitButton = exitButton;
    }

    /**
     * @return boolean return the menuClicked
     */
    public boolean isExitClicked() {
        return exitClicked;
    }

    /**
     * @param menuClicked the menuClicked to set
     */
    public void setExitClicked(boolean exitClicked) {
        this.exitClicked = exitClicked;
    }

    /**
     * @return Rectangle return the leaderboardButton
     */
    public Rectangle getLeaderboardButton() {
        return leaderboardButton;
    }

    /**
     * @param leaderboardButton the leaderboardButton to set
     */
    public void setLeaderboardButton(Rectangle leaderboardButton) {
        this.leaderboardButton = leaderboardButton;
    }

    /**
     * @return boolean return the leaderboardClicked
     */
    public boolean isLeaderboardClicked() {
        return leaderboardClicked;
    }

    /**
     * @param leaderboardClicked the leaderboardClicked to set
     */
    public void setLeaderboardClicked(boolean leaderboardClicked) {
        this.leaderboardClicked = leaderboardClicked;
    }


    /**
     * @return Rectangle return the infoButton
     */
    public Rectangle getInfoButton() {
        return infoButton;
    }

    /**
     * @param infoButton the infoButton to set
     */
    public void setInfoButton(Rectangle infoButton) {
        this.infoButton = infoButton;
    }

    /**
     * @return boolean return the infoClicked
     */
    public boolean isInfoClicked() {
        return infoClicked;
    }

    /**
     * @param infoClicked the infoClicked to set
     */
    public void setInfoClicked(boolean infoClicked) {
        this.infoClicked = infoClicked;
    }

}
