import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;

public abstract class River {

    private RIVER_TYPE riverType;
    private double speed;

    public River(PApplet pApplet, RIVER_TYPE riverType, double speed) {
        this.riverType = riverType;
        this.speed = speed;

    }

    public RIVER_TYPE getRiverType() {
        return riverType;
    }

    public double getSpeed() {
        return speed;
    }

    public abstract boolean isFrogOnFloating(Hitbox frogHitbox);



    public void draw(PApplet pApplet) {
    }
}
