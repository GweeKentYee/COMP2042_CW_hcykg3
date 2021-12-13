package Game.Controller;

import java.awt.*;
import java.awt.event.*;

import Game.Model.Wall;
import Game.View.GameBoard;
import Game.View.GameFrame;

public class GameBoardController implements KeyListener,MouseListener,MouseMotionListener {

    private GameBoard gameBoard;

    // private Timer gameTimer;

    // private Wall wall;

    // private boolean showPauseMenu;

    public GameBoardController(GameBoard gameBoard){

        this.gameBoard = gameBoard;
        // gameTimer = gameBoard.getGameTimer();
        // wall = gameBoard.getWall();
        // showPauseMenu = gameBoard.isShowPauseMenu();

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                gameBoard.getWall().getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
                gameBoard.getWall().getPlayer().movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                gameBoard.setShowPauseMenu(!gameBoard.isShowPauseMenu());
                gameBoard.repaint();
                gameBoard.getGameTimer().stop();
                gameBoard.getTimer().setGameStatus(false);
                break;
            case KeyEvent.VK_SPACE:
                if(!gameBoard.isShowPauseMenu()){
                    if(gameBoard.getGameTimer().isRunning()){
                        gameBoard.getGameTimer().stop();
                        gameBoard.getTimer().setGameStatus(false);
                    }
                    else{
                        gameBoard.getGameTimer().start();
                        gameBoard.getTimer().setGameStatus(true);
                    }
                }
                break;
            case KeyEvent.VK_F1:
                if (gameBoard.getMode() == "training"){
                    if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                        gameBoard.getDebugConsole().setVisible(true);
                }
            default:
                gameBoard.getWall().getPlayer().stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gameBoard.getWall().getPlayer().stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!gameBoard.isShowPauseMenu())
            return;
        if(gameBoard.getContinueButtonRect().contains(p)){
            gameBoard.setShowPauseMenu(false);
            gameBoard.repaint();
        }
        else if(gameBoard.getRestartButtonRect().contains(p)){
            gameBoard.setMessage("Restarting Game...");
            Wall.setScore(0);
            if (gameBoard.getMode() == "training"){
                gameBoard.getOwner().enableGameBoard("training", "Game");
            } else {
                gameBoard.getOwner().enableGameBoard("ranked", "Game");
            }
            gameBoard.setShowPauseMenu(false);
        }
        else if(gameBoard.getBackButtonRect().contains(p)){
            Wall.setScore(0);
            gameBoard.getOwner().dispose();
            new GameFrame().initialize();
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(gameBoard.getBackButtonRect() != null && gameBoard.isShowPauseMenu()) {
            if (gameBoard.getBackButtonRect().contains(p) || gameBoard.getContinueButtonRect().contains(p) || gameBoard.getRestartButtonRect().contains(p))
                gameBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                gameBoard.setCursor(Cursor.getDefaultCursor());
        }
        else{
            gameBoard.setCursor(Cursor.getDefaultCursor());
        }
    }
}