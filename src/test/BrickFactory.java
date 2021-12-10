package test;

import java.awt.*;

public class BrickFactory {

    public Brick makeBrick(String brickType, Point point, Dimension brickSize){

        if (brickType == null){

            return null;

        }
        if (brickType.equals("Clay Brick")){

            return new ClayBrick(point,brickSize);

        } else if (brickType.equals("Steel Brick")){

            return new SteelBrick(point,brickSize);

        } else if (brickType.equals("Cement Brick")){

            return new CementBrick(point,brickSize);

        }

        return null;
    }
    
}
