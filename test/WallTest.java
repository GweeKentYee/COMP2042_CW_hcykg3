import Game.Model.*;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.geom.Point2D;
import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    private Point2D center = new Point2D.Double(200, 200);
    private Point point = new Point(0, 0);
    Dimension dimension = new Dimension(40, 10);

    GameTimer timer = new GameTimer();
    Ball rubberBall = new RubberBall(center);
    Wall wall = new Wall(new Rectangle(0,0,20,40),6/2, new Point(0,0),timer);
    Player player = new Player(point, 2, 2, new Rectangle(0, 0, 200, 300));
    Brick[] bricks;
    BrickFactory brickFactory = new BrickFactory();

    @Test
    void getBallCount() {
        assertEquals(3, wall.getBallCount());
    }

    @Test
    void isBallLost() {
        assertFalse(wall.isBallLost());
    }

    @Test
    void ballReset() {
        wall.ballReset();
        assertFalse(wall.isBallLost());
    }

    @Test
    void wallReset() {
        assertEquals(3, wall.getBallCount());
    }

    @Test
    void ballEnd() {
        if(wall.getBallCount() != 0)
            assertFalse(wall.ballEnd());
        else
            assertTrue(wall.ballEnd());
    }

    @Test
    void isDone() {
        wall.setBrickCount(10);
        assertFalse(wall.isDone());
        wall.setBrickCount(0);
        assertTrue(wall.isDone());
    }

    @Test
    void setBallXSpeed() {
        rubberBall = wall.getBall();
        wall.setBallXSpeed(20);
        assertEquals(20, rubberBall.getSpeedX());
    }

    @Test
    void setBallYSpeed() {
        rubberBall = wall.getBall();
        wall.setBallYSpeed(20);
        assertEquals(20, rubberBall.getSpeedY());
    }

    @Test
    void resetBallCount() {
        wall.resetBallCount();
        assertEquals(3, wall.getBallCount());
    }

    @Test
    void makeBrick() {
        Brick brick = new CementBrick(point, dimension);
        assertNotEquals(brick, brickFactory.makeBrick("Clay Brick", point, dimension));
    }

    @Test
    void getBricks() {
        wall.setBricks(bricks);
        assertEquals(bricks, wall.getBricks());
    }

    @Test
    void setBricks() {
        wall.setBricks(bricks);
        assertEquals(bricks, wall.getBricks());
    }

    @Test
    void getBall() {
        wall.setBall(rubberBall);
        assertEquals(rubberBall, wall.getBall());
    }

    @Test
    void setBall() {
        wall.setBall(rubberBall);
        assertEquals(rubberBall, wall.getBall());
    }

    @Test
    void getPlayer() {
        wall.setPlayer(player);
        assertEquals(player, wall.getPlayer());
    }

    @Test
    void setPlayer() {
        wall.setPlayer(player);
        assertEquals(player, wall.getPlayer());
    }

    @Test
    void setBrickCount() {
        wall.setBrickCount(30);
        assertEquals(30, wall.getBrickCount());
    }
}