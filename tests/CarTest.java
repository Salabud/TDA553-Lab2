import org.junit.jupiter.api.Test;

import java.awt.*;

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
        assertEquals(Car.Direction.SOUTH,saab.getDirection());
    }

    @Test
    public void testSpeed_Volvo(){
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        volvo.incrementSpeed(80);
        volvo.decrementSpeed(20);
        assertEquals(75, volvo.getCurrentSpeed());
    }

    @Test
    public void testPaintCar(){
        Volvo240 volvo = new Volvo240();
        volvo.setColor(Color.cyan);
        assertEquals(Color.cyan,volvo.getColor());
    }

    @Test
    public void testGetters(){
        Saab95 saab = new Saab95();
        assertEquals(2,saab.getNrDoors());
        assertEquals(125,saab.getEnginePower());
        assertEquals("Saab95",saab.getModelName());
    }

    @Test
    public void testGas_TurboOn(){
        Saab95 saab = new Saab95();
        saab.setTurboOn();
        saab.gas(1);
        saab.gas(0.5);
        assertEquals(2.4375, saab.getCurrentSpeed());
    }

    @Test
    public void testGas_TurboOff(){
        Saab95 saab = new Saab95();
        saab.setTurboOff();
        saab.gas(1);
        saab.gas(0.5);
        assertEquals(1.875, saab.getCurrentSpeed());
    }

    @Test
    public void testGas_IgnoreLessThanZero(){
        Volvo240 volvo = new Volvo240();
        volvo.gas(-10); // Kommer ignoreras
        assertEquals(0,volvo.getCurrentSpeed());
    }

    @Test
    public void testBrake_IgnoreGreaterThanOne(){
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        volvo.brake(20); // Kommer ignoreras
        volvo.brake(1);
        assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    public void testRaisePlatform_IgnoreRaiseWhenDriving(){
        Scania scania = new Scania();
        scania.gas(10);
        scania.raisePlatform(10);
        assertEquals(0,scania.getCurrentAngle());
    }

    @Test
    public void testLoadCar_DumpyUp(){
        CarTransporter carTransporter = new CarTransporter();
        Volvo240 volvo = new Volvo240();
        carTransporter.loadCar(volvo);
        assertEquals(0, carTransporter.getLoadedCars().size());
    }

    @Test
    public void testLoadCar_BigVehicle(){
        Scania scania = new Scania();
        CarTransporter carTransporter = new CarTransporter();

        carTransporter.loadCar(scania);
        assertEquals(0,carTransporter.getLoadedCars().size());
    }
    
    @Test
    public void testUnloadCar(){
        Volvo240 volvo = new Volvo240();
        CarTransporter ct = new CarTransporter();

        ct.lowerPlatform();
        ct.loadCar(volvo);
        ct.raisePlatform();
        ct.gas(1);
        ct.move();
        ct.brake(1);
        ct.stopEngine();
        ct.lowerPlatform();
        ct.unloadCar();
        assertEquals(ct.getY()+1,volvo.getY());

    }
}
