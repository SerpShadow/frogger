import processing.core.PApplet;

public class LaneTruck extends Lane {

    int width = 32;
    int amount = 2;
    int speed = 1;

    public LaneTruck(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.TRUCK, width, amount, speed);
    }
}
