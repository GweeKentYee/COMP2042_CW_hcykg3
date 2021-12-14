package Game.Model;

import java.awt.*;
import java.util.Random;


public class SteelBrick extends Brick {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double PROBABILITY = 0.4;

    private Random random;
 
    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        random = new Random();
    }

    public void impact(){
        if(random.nextDouble() < PROBABILITY){
            super.impact();
            if(super.isBroken()){
                if (random.nextDouble() < PROBABILITY){
                    Wall.setScore(Wall.getScore() + 3);
                } else {
                    Wall.setScore(Wall.getScore() + 1);
                }
            }
        }
    }

    public Random getRandom() {
        return random;
    }

}
