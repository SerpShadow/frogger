import processing.core.PApplet;

public class LaneRaceCar extends Lane {

    int amount = 2;
    int speed = 3;

    public LaneRaceCar(PApplet pApplet) {
        super(pApplet);

        generateVehicles(VEHICLE_TYPE.RACE_CAR, amount, speed);
    }

    public boolean checkCollision(Hitbox froggerHitbox) {
        return checkCollision(froggerHitbox, 9);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.pushMatrix();
        pApplet.translate(0, UTILS.chunksToPixel(9));
        super.draw(pApplet);
        pApplet.popMatrix();
    }
}
