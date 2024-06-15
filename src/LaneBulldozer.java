import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;

public class LaneBulldozer extends Lane {

    int amount = 1;
    int speed = 1;

    public LaneBulldozer(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.BULLDOZER, amount, speed);
    }

    public boolean checkCollision(Hitbox frogHitbox) {
        return checkCollision(frogHitbox, VEHICLE_TYPE.BULLDOZER.getPositionY());
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.pushMatrix();
        pApplet.translate(0, VEHICLE_TYPE.BULLDOZER.getPositionY());
        super.draw(pApplet);
        pApplet.popMatrix();
    }
}
