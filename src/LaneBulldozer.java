import processing.core.PApplet;

public class LaneBulldozer extends Lane {

    int width = 16;
    int amount = 1;
    int speed = 1;

    public LaneBulldozer(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.BULLDOZER, width, amount, speed);
    }
}
