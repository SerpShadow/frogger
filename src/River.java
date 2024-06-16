import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;

public abstract class River extends Floor {

    private final RIVER_TYPE riverType;
    private double getMovementSpeed;

    public River(PApplet pApplet, RIVER_TYPE riverType, double getMovementSpeed) {
        this.riverType = riverType;
        this.getMovementSpeed = getMovementSpeed;

    }

    public RIVER_TYPE getRiverType() {
        return riverType;
    }

    public double getMovementSpeed() {
        return getMovementSpeed;
    }

    public abstract boolean checkCollision(Hitbox frogHitbox);



    public void draw(PApplet pApplet) {
    }
}
