import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;

public class LaneRaceCar extends Lane {

    int amount = 2;
    int speed = 3;

    public LaneRaceCar(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.RACE_CAR, amount, speed);
    }

    public boolean checkCollision(Hitbox frogHitbox) {
        return checkCollision(frogHitbox, VEHICLE_TYPE.RACE_CAR.getPositionY());
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.pushMatrix();
        pApplet.translate(0, VEHICLE_TYPE.RACE_CAR.getPositionY());
        super.draw(pApplet);
        pApplet.popMatrix();
    }
}
