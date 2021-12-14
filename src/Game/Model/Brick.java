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
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Created by filippo on 04/09/16.
 *
 */
abstract public class Brick  {

    public static final int MIN_CRACK = 1;

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private String name;
    private Shape brickShape;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        broken = false;
        this.name = name;
        brickShape = makebrickShape(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return broken;
    }

    // public abstract Shape getBrick();

    public Color getBorderColor(){
        return  border;
    }

    public Color getInnerColor(){
        return inner;
    }


    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickShape.contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(brickShape.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(brickShape.contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(brickShape.contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    public final boolean isBroken(){
        return broken;
    }

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    public void impact(){
        strength--;
        broken = (strength == 0);
        if (broken == true){
            Wall.setScore(Wall.getScore() + 1);
        }
    }

    private Shape makebrickShape(Point pos,Dimension size){
        return new Rectangle(pos,size);
    };

    /**
     * @return Shape return the brickShape
     */
    public Shape getBrickShape() {
        return brickShape;
    }

    /**
     * @param brickShape the brickShape to set
     */
    public void setBrickShape(Shape brickShape) {
        this.brickShape = brickShape;
    }

}





