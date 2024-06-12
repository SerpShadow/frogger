import processing.core.PApplet;

public class LaneDuneBuggy extends Lane {

    int width = 16;
    int amount = 2;
    int speed = 1;

    public LaneDuneBuggy(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.DUNE_BUGGY, width, amount, speed);
    }
}
