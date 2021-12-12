package Game.Controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import Game.GameFrame;

public class GameFrameController implements WindowFocusListener {

    private GameFrame gameFrame;

    public GameFrameController(GameFrame gameFrame){

        this.gameFrame = gameFrame;

    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        
        gameFrame.setGaming(true);
        
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        
        if (gameFrame.isGaming()){

            gameFrame.getGameBoard().onLostFocus();

        }
        
    }

}
