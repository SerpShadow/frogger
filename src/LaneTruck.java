import processing.core.PApplet;

public class LaneTruck extends Lane {

    int amount = 2;
    int speed = -1;

    public LaneTruck(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.TRUCK, amount, speed);
    }

    public boolean checkCollision(Hitbox froggerHitbox) {
        return checkCollision(froggerHitbox, 8);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.pushMatrix();
        pApplet.translate(0, UTILS.chunksToPixel(8));
        super.draw(pApplet);
        pApplet.popMatrix();
    }
}
