/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Game.Model;

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

        ballShape = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;

    }

    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    public void move(){

        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        makeTempShape();

    }

    public void moveTo(Point p){

        center.setLocation(p);
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
        
        getUp().setLocation(center.getX(),center.getY()-(height / 2));
        getDown().setLocation(center.getX(),center.getY()+(height / 2));

        getLeft().setLocation(center.getX()-(width / 2),center.getY());
        getRight().setLocation(center.getX()+(width / 2),center.getY());
    }

    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    public void setXSpeed(int speed){
        speedX = speed;
    }

    public void setYSpeed(int speed){
        speedY = speed;
    }

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
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
