import processing.core.PApplet;

public class LaneCoupe extends Lane {

    int width = 16;
    int amount = 2;
    int speed = 1;

    public LaneCoupe(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.COUPE, width, amount, speed);
    }
}
