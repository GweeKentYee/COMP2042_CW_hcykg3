package Game;

import java.awt.geom.Point2D;

public class BallFactory {

    public Ball makeBall(String ballType, Point2D ballPosition){

        if (ballType == null){
            return null;
        }
        if (ballType.equals("RUBBERBALL")){
            return new RubberBall(ballPosition);
        }
        return null;
    }
    
}
