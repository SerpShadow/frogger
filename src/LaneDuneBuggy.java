import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;

public class LaneDuneBuggy extends Lane {

    int amount = 2;
    int vehicleSpeed = -2;

    public LaneDuneBuggy(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.DUNE_BUGGY, amount, vehicleSpeed);
    }

    public boolean checkCollision(Hitbox frogHitbox) {
        return checkCollision(frogHitbox, VEHICLE_TYPE.DUNE_BUGGY.getPositionY());
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.pushMatrix();
        pApplet.translate(0, VEHICLE_TYPE.DUNE_BUGGY.getPositionY());
        super.draw(pApplet);
        pApplet.popMatrix();
    }
}
