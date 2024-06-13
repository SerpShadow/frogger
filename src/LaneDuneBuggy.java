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

    public boolean checkCollision(Hitbox froggerHitbox) {
        return checkCollision(froggerHitbox, 12);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.pushMatrix();
        pApplet.translate(0, UTILS.chunksToPixel(12));
        super.draw(pApplet);
        pApplet.popMatrix();
    }
}
