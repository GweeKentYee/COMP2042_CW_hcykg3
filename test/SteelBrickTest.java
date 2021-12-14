import Game.Model.SteelBrick;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class SteelBrickTest {

    Point position = new Point(0, 0);
    Dimension dimension = new Dimension(40, 10);
    SteelBrick steelbrick = new SteelBrick(new Point(position), new Dimension(dimension));
    Rectangle expectedBrick = new Rectangle(new Point(position), new Dimension(dimension));
    private static final double STEEL_PROBABILITY = 0.4;

    @Test
    void impact() {
        steelbrick.impact();
        if (steelbrick.getRandom().nextDouble() < STEEL_PROBABILITY){
            assertTrue(steelbrick.isBroken());
        }
        else{
            assertFalse(steelbrick.isBroken());
        }
    }
}