package Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class HomeMenuController implements MouseListener, MouseMotionListener {

    private HomeMenu homeMenu;

    public HomeMenuController(HomeMenu homeMenu){

        this.homeMenu = homeMenu;

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenu.getStartButton().contains(p)){
            homeMenu.getOwner().enableGameBoard("ranked");
         }
        else if(homeMenu.getTrainingButton().contains(p)){
           homeMenu.getOwner().enableGameBoard("training");
        }
        else if(homeMenu.getExitButton().contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        } 
        else if(homeMenu.getLeaderboardButton().contains(p)){
            homeMenu.getOwner().enableLeaderBoard();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenu.getStartButton().contains(p)){
            homeMenu.setStartClicked(true);
            homeMenu.repaint(homeMenu.getStartButton().x,homeMenu.getStartButton().y,homeMenu.getStartButton().width+1,homeMenu.getStartButton(). height + 1);

        }
        else if(homeMenu.getTrainingButton().contains(p)){
            homeMenu.setTrainingClicked(true);
            homeMenu.repaint(homeMenu.getTrainingButton().x,homeMenu.getTrainingButton().y,homeMenu.getTrainingButton().width+1,homeMenu.getTrainingButton(). height + 1);

        }
        else if(homeMenu.getExitButton().contains(p)){
            homeMenu.setExitClicked(true);
            homeMenu.repaint(homeMenu.getExitButton().x,homeMenu.getExitButton().y,homeMenu.getExitButton().width+1,homeMenu.getExitButton().height+1);
        }
        else if(homeMenu.getLeaderboardButton().contains(p)){
            homeMenu.setLeaderboardClicked(true);
            homeMenu.repaint(homeMenu.getLeaderboardButton().x,homeMenu.getLeaderboardButton().y,homeMenu.getLeaderboardButton().width+1,homeMenu.getLeaderboardButton().height+1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(homeMenu.isStartClicked()){
            homeMenu.setStartClicked(false);
            homeMenu.repaint(homeMenu.getStartButton().x,homeMenu.getStartButton().y,homeMenu.getStartButton().width+1,homeMenu.getStartButton().height+1);
             homeMenu.setCursor(Cursor.getDefaultCursor());
        }
        else if(homeMenu.isTrainingClicked()){
            homeMenu.setTrainingClicked(false);
            homeMenu.repaint(homeMenu.getTrainingButton().x,homeMenu.getTrainingButton().y,homeMenu.getTrainingButton().width+1,homeMenu.getTrainingButton().height+1);
             homeMenu.setCursor(Cursor.getDefaultCursor());
        }
        else if(homeMenu.isExitClicked()){
            homeMenu.setExitClicked(false);
            homeMenu.repaint(homeMenu.getExitButton().x,homeMenu.getExitButton().y,homeMenu.getExitButton().width+1,homeMenu.getExitButton().height+1);
            homeMenu.setCursor(Cursor.getDefaultCursor());
        }
        else if(homeMenu.isLeaderboardClicked()){
            homeMenu.setLeaderboardClicked(false);
            homeMenu.repaint(homeMenu.getLeaderboardButton().x,homeMenu.getLeaderboardButton().y,homeMenu.getLeaderboardButton().width+1,homeMenu.getLeaderboardButton().height+1);
            homeMenu.setCursor(Cursor.getDefaultCursor());
        }
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
        if(homeMenu.getStartButton().contains(p) || homeMenu.getTrainingButton().contains(p) || homeMenu.getExitButton().contains(p) || homeMenu.getLeaderboardButton().contains(p))
            homeMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            homeMenu.setCursor(Cursor.getDefaultCursor());

    }
    
}
