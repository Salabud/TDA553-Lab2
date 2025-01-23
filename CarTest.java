import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class  CarTest {
    @Test
    public void testMoveForward(){
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        volvo.move();
        volvo.move();
        assertEquals(0.2, volvo.getY());
    }

    @Test
    public void testTurnLeft(){
        Saab95 saab = new Saab95();
        saab.turnLeft();
        saab.turnLeft();
        assertEquals("SOUTH",saab.getDirection());
    }

    @Test
    public void testSpeedVolvo(){
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        volvo.incrementSpeed(80);
        volvo.decrementSpeed(20);
        assertEquals(75, volvo.getCurrentSpeed());
    }
}
