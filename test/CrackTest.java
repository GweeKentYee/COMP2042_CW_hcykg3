import Game.Model.Brick;
import Game.Model.CementBrick;
import Game.Model.Crack;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class CrackTest {
    private Point2D center = new Point2D.Double(200, 200);
    Point position = new Point(0, 0);
    Dimension dimension = new Dimension(40, 10);
    Brick brick = new CementBrick(position, dimension);
    Crack crack = new Crack(brick, 3, 3);

    Point start = new Point(0,0);

    @Test
    void draw() {
        assertNotNull(crack.draw());
    }

    @Test
    void reset() {
        crack.reset();
        assertNotNull(crack);
    }

    @Test
    void makeCrack() {
        crack.makeCrack(center, 10);
        assertNotNull(crack);
    }

    @Test
    void testMakeCrack() {
        crack.makeCrack(start,1);
        assertNotNull(crack);
    }
}