package Game.Model;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

public class Crack{

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;

    private GeneralPath crack;

    private int crackDepth;
    private int steps;

    private Brick brick;
    private Random random;

    public Crack(Brick brick, int crackDepth, int steps){

        random = new Random();
        this.brick = brick;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;

    }

    public GeneralPath draw(){
        return crack;
    }

    public void reset(){
        crack.reset();
    }

    public void makeCrack(Point2D point, int direction){
        Rectangle bounds = brick.getBrickShape().getBounds();

        Point impact = new Point((int)point.getX(),(int)point.getY());
        Point start = new Point();
        Point end = new Point();

        if(direction == LEFT){

            start.setLocation(bounds.getLocation());
            end.setLocation(bounds.x, bounds.y + bounds.height);
            Point tmp = makeRandomPoint(start,end,VERTICAL);
            makeCrack(impact,tmp);

        } else if (direction == RIGHT){

            start.setLocation(bounds.x + bounds.width, bounds.y);
            end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
            Point tmp = makeRandomPoint(start,end,VERTICAL);
            makeCrack(impact,tmp);

        } else if (direction == UP){

            start.setLocation(bounds.getLocation());
            end.setLocation(bounds.x + bounds.width, bounds.y);
            Point tmp = makeRandomPoint(start,end,HORIZONTAL);
            makeCrack(impact,tmp);

        } else if (direction == DOWN){

            start.setLocation(bounds.x, bounds.y + bounds.height);
            end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
            Point tmp = makeRandomPoint(start,end,HORIZONTAL);
            makeCrack(impact,tmp);

        }

    }

    protected void makeCrack(Point start, Point end){

        GeneralPath path = new GeneralPath();

        path.moveTo(start.x,start.y);

        double width = (end.x - start.x) / (double)steps;
        double height = (end.y - start.y) / (double)steps;

        int bound = crackDepth;
        int jump  = bound * 5;

        double x,y;

        for(int i = 1; i < steps;i++){

            x = (i * width) + start.x;
            y = (i * height) + start.y + randomInBounds(bound);

            if(inMiddle(i,CRACK_SECTIONS,steps))
                y += jumps(jump,JUMP_PROBABILITY);

            path.lineTo(x,y);

        }

        path.lineTo(end.x,end.y);
        crack.append(path,true);
    }

    private int randomInBounds(int bound){
        int number = (bound * 2) + 1;
        return random.nextInt(number) - bound;
    }

    private boolean inMiddle(int i,int steps,int divisions){
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }

    private int jumps(int bound,double probability){

        if(random.nextDouble() > probability)
            return randomInBounds(bound);
        return  0;

    }

    private Point makeRandomPoint(Point from,Point to, int direction){

        Point point = new Point();
        int position;

        if (direction == HORIZONTAL){
                position = random.nextInt(to.x - from.x) + from.x;
                point.setLocation(position,to.y);
        } else if (direction == VERTICAL){
                position = random.nextInt(to.y - from.y) + from.y;
                point.setLocation(to.x,position);
        }
        return point;
    }

}
