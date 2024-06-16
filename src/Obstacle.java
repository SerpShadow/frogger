import General.Hitbox;
import processing.core.PApplet;

public abstract class Obstacle extends GameObject {

    public Obstacle(int width, double movementSpeed, int startPositionX, Hitbox hitboxRelative) {
        super(width, movementSpeed, startPositionX, hitboxRelative);
    }

    public Obstacle(int width, int movementSpeed, int startPositionX) {
        this(width, movementSpeed, startPositionX, new Hitbox(0, 0, 0, 0));
    }

    protected abstract void checkPosition();

    public void draw(PApplet pApplet) {
        setFixedMovementX(getFixedMovementX() + getMovementSpeed());
        checkPosition();
        super.draw(pApplet);
    }
}
