import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;

public class LaneCoupe extends Lane {

    int amount = 2;
    int speed = -1;

    public LaneCoupe(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.COUPE, amount, speed);
    }

    public boolean checkCollision(Hitbox frogHitbox) {
        return checkCollision(frogHitbox, VEHICLE_TYPE.COUPE.getPositionY());
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.pushMatrix();
        pApplet.translate(0, VEHICLE_TYPE.COUPE.getPositionY());
        super.draw(pApplet);
        pApplet.popMatrix();
    }
}
