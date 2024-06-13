import processing.core.PApplet;

public class LaneCoupe extends Lane {

    int amount = 2;
    int speed = -1;

    public LaneCoupe(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.COUPE, amount, speed);
    }

    public boolean checkCollision(Hitbox froggerHitbox) {
        return checkCollision(froggerHitbox, 10);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.pushMatrix();
        pApplet.translate(0, UTILS.chunksToPixel(10));
        super.draw(pApplet);
        pApplet.popMatrix();
    }
}
