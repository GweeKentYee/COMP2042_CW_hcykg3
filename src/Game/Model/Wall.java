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
import java.util.Random;


public class Wall {

    private static final int LEVELS_COUNT = 8;

    private static final String CLAY = "Clay Brick";
    private static final String STEEL = "Steel Brick";
    private static final String CEMENT = "Cement Brick";

    private Random random;
    private Rectangle area;

    private Brick[] bricks;
    private Ball ball;
    private Player player;

    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    private static int score = 0;

    private GameTimer timer;

    BallFactory ballFactory;

    public Wall(Rectangle drawArea, double brickDimensionRatio, Point ballPosition, GameTimer timer){

        this.startPoint = new Point(ballPosition);

        this.timer = timer;

        levels = makeLevels(drawArea,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        random = new Random();

        ballFactory = new BallFactory();
        ball = ballFactory.makeBall("RUBBERBALL",ballPosition);

        int speedX,speedY;
        do{
            speedX = random.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -random.nextInt(3);
        }while(speedY == 0);

        getBall().setSpeed(speedX,speedY);

        setPlayer(new Player((Point) ballPosition.clone(),100,10, drawArea));

        area = drawArea;

    }

    private Brick[][] makeLevels(Rectangle drawArea,double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = Level.makeSingleTypeLevel(drawArea,30,3,brickDimensionRatio,CLAY);
        tmp[1] = Level.makeChessboardLevel(drawArea,30,3,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = Level.makeChessboardLevel(drawArea,30,3,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = Level.makeChessboardLevel(drawArea,30,3,brickDimensionRatio,STEEL,CEMENT);
        tmp[4] = Level.makeChessboardLevel(drawArea,40,4,brickDimensionRatio,STEEL,CEMENT);
        tmp[5] = Level.makeSingleTypeLevel(drawArea,40,4,brickDimensionRatio,CEMENT);
        tmp[6] = Level.makeSingleTypeLevel(drawArea,40,4,brickDimensionRatio,STEEL);
        tmp[7] = Level.makeSingleTypeLevel(drawArea,50,5,brickDimensionRatio,CEMENT);
        return tmp;
    }

    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
        timer.setSecond(0);
        if (level == 3)
            {
                timer.setMinute(5);

            } else if (level >= 6) {

                timer.setMinute(8);

            } else if (level >= 4) {

                timer.setMinute(6);

            } else if (level >= 1){

                timer.setMinute(4);

            }
        }
       

    public void move(){
        player.move();
        ball.move();
    }

    public void findImpacts(){
        if(player.impactBall(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            brickCount--;
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    private boolean impactWall(){
        for(Brick brick : bricks){
            switch(brick.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    getBall().reverseY();
                    return brick.setImpact(getBall().getDown(), Crack.DOWN);
                case Brick.DOWN_IMPACT:
                    getBall().reverseY();
                    return brick.setImpact(getBall().getUp(), Crack.UP);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    getBall().reverseX();
                    return brick.setImpact(getBall().getRight(), Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    getBall().reverseX();
                    return brick.setImpact(getBall().getLeft(), Crack.LEFT);
            }
        }
        return false;
    }

    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    public int getBrickCount(){
        return brickCount;
    }

    public int getBallCount(){
        return ballCount;
    }

    public boolean isBallLost(){
        return ballLost;
    }

    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = random.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -random.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isDone(){
        return brickCount == 0;
    }

    public boolean hasLevel(){
        return level < levels.length;
    }

    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * @return Brick[] return the bricks
     */
    public Brick[] getBricks() {
        return bricks;
    }

    /**
     * @param bricks the bricks to set
     */
    public void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    public Ball getBall(){
        return ball;
    }

    public void setBall(Ball ball){
        this.ball = ball;
    }

    /**
     * @return Player return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return int return the score
     */
    public static int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public static void setScore(int score) {
        Wall.score = score;
    }

    /**
     * @return int return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

}
