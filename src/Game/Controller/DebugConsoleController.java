package Game.Controller;

import Game.Model.Ball;
import Game.View.DebugConsole;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DebugConsoleController implements WindowListener {
    
    private DebugConsole debugConsole;

    public DebugConsoleController(DebugConsole debugConsole){

        this.debugConsole = debugConsole;

    }

    private void setLocation(){
        int x = ((debugConsole.getOwner().getWidth() - debugConsole.getWidth()) / 2) + debugConsole.getOwner().getX();
        int y = ((debugConsole.getOwner().getHeight() - debugConsole.getHeight()) / 2) + debugConsole.getOwner().getY();
        debugConsole.setLocation(x,y);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

        debugConsole.getGameBoard().repaint();

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = debugConsole.getWall().getBall();
        debugConsole.getDebugPanel().setValues(b.getSpeedX(),b.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
    
}
