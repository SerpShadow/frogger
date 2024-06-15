import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;

public class LaneTruck extends Lane {

    int amount = 2;
    int speed = -1;

    public LaneTruck(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.TRUCK, amount, speed);
    }

    public boolean checkCollision(Hitbox frogHitbox) {
        return checkCollision(frogHitbox, VEHICLE_TYPE.TRUCK.getPositionY());
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.pushMatrix();
        pApplet.translate(0, VEHICLE_TYPE.TRUCK.getPositionY());
        super.draw(pApplet);
        pApplet.popMatrix();
    }
}
