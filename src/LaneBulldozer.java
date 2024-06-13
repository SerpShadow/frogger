import processing.core.PApplet;

public class LaneBulldozer extends Lane {

    int amount = 1;
    int speed = 1;

    public LaneBulldozer(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.BULLDOZER, amount, speed);
    }

    public boolean checkCollision(Hitbox froggerHitbox) {
        return checkCollision(froggerHitbox, 11);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.pushMatrix();
        pApplet.translate(0, UTILS.chunksToPixel(11));
        super.draw(pApplet);
        pApplet.popMatrix();
    }
}
