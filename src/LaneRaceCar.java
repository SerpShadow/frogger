import processing.core.PApplet;

public class LaneRaceCar extends Lane {

    int width = 16;
    int amount = 2;
    int speed = 2;

    public LaneRaceCar(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.RACE_CAR, width, amount, speed);
    }
}
