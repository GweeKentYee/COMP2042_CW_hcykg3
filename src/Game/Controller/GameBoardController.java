package Game.Controller;

import java.awt.*;
import java.awt.event.*;

import Game.View.GameBoard;

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
                break;
            case KeyEvent.VK_SPACE:
                if(!gameBoard.isShowPauseMenu())
                    if(gameBoard.getGameTimer().isRunning())
                        gameBoard.getGameTimer().stop();
                    else
                        gameBoard.getGameTimer().start();
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
            gameBoard.getWall().ballReset();
            gameBoard.getWall().wallReset();
            gameBoard.setShowPauseMenu(false);
            gameBoard.repaint();
        }
        else if(gameBoard.getExitButtonRect().contains(p)){
            System.exit(0);
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
        if(gameBoard.getExitButtonRect() != null && gameBoard.isShowPauseMenu()) {
            if (gameBoard.getExitButtonRect().contains(p) || gameBoard.getContinueButtonRect().contains(p) || gameBoard.getRestartButtonRect().contains(p))
                gameBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                gameBoard.setCursor(Cursor.getDefaultCursor());
        }
        else{
            gameBoard.setCursor(Cursor.getDefaultCursor());
        }
    }
}