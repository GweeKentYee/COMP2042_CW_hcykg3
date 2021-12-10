package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 *
 */
abstract public class Ball {

    private Shape ballShape;

    private Point2D center;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        setUp(new Point2D.Double());
        setDown(new Point2D.Double());
        setLeft(new Point2D.Double());
        setRight(new Point2D.Double());

        // up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        // down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        // left.setLocation(center.getX()-(radiusA /2),center.getY());
        // right.setLocation(center.getX()+(radiusA /2),center.getY());

        ballShape = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    public void move(){

        center.setLocation((center.getX() + speedX),(center.getY() + speedY));

        // RectangularShape tmp = (RectangularShape) ballShape;
        // double w = tmp.getWidth();
        // double h = tmp.getHeight();

        // tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        // setPoints(w,h);

        makeTempShape();
    }

    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    public void setXSpeed(int s){
        speedX = s;
    }

    public void setYSpeed(int s){
        speedY = s;
    }

    public void reverseX(){
        speedX *= -1;
    }

    public void reverseY(){
        speedY *= -1;
    }

    public Color getBorderColor(){
        return border;
    }

    public Color getInnerColor(){
        return inner;
    }

    public Point2D getPosition(){
        return center;
    }

    public Shape getballShape(){
        return ballShape;
    }

    public void moveTo(Point p){

        center.setLocation(p);

        // RectangularShape tmp = (RectangularShape) ballShape;
        // double w = tmp.getWidth();
        // double h = tmp.getHeight();

        // tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);

        makeTempShape();
    }

    private void makeTempShape(){

        RectangularShape tmp = (RectangularShape) ballShape;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);

    }

    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }

    /**
     * @return Point2D return the up
     */
    public Point2D getUp() {
        return up;
    }

    /**
     * @param up the up to set
     */
    public void setUp(Point2D up) {
        this.up = up;
    }

    /**
     * @return Point2D return the down
     */
    public Point2D getDown() {
        return down;
    }

    /**
     * @param down the down to set
     */
    public void setDown(Point2D down) {
        this.down = down;
    }

    /**
     * @return Point2D return the left
     */
    public Point2D getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(Point2D left) {
        this.left = left;
    }

    /**
     * @return Point2D return the right
     */
    public Point2D getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(Point2D right) {
        this.right = right;
    }

}
