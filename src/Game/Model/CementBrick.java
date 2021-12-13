package Game.Model;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


public class CementBrick extends Brick {


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickShape;

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(this,DEF_CRACK_DEPTH,DEF_STEPS);
        brickShape = super.getBrickShape();
    }

    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }

    @Override
    public Shape getBrickShape() {
        return this.brickShape;
    }

    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath path = crack.draw();
            path.append(super.getBrickShape(),false);
            brickShape = path;
        }
    }

    public void repair(){
        super.repair();
        crack.reset();
        brickShape = super.getBrickShape();
    }

}
