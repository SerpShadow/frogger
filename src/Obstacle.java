import General.Hitbox;
import General.UTILS;
import SpriteLib.Point;
import processing.core.PApplet;

public abstract class Obstacle {

    private final int width;
    private final int height = UTILS.chunksToPixel(1);

    private final Point position = new Point();
    private final double speed;
    private double movement = 0;

    private Hitbox hitboxRelativ;

    public Obstacle(int width, double speed, int startPositionX, Hitbox hitboxRelativ) {
        this.width = width;
        this.speed = speed;
        this.hitboxRelativ = hitboxRelativ;

        position.setX(startPositionX);
    }

    public Obstacle(int width, int speed, int startPositionX) {
        this(width, speed, startPositionX, new Hitbox(0, 0, 0, 0));
    }

    /**
     * @return width in chunks
     */
    public int getWidthInChunks() {
        return width;
    }

    public int getWidthInPixel() {
        return UTILS.chunksToPixel(width);
    }

    public int getHeight() {
        return height;
    }

    public Point getPosition() {
        return position;
    }

    public int getPositionX() {
        return position.getX();
    }

    public int getPositionY() {
        return position.getY();
    }

    public void setPositionX(int positionX) {
        position.setX(positionX);
    }

    public double getSpeed() {
        return speed;
    }

    public void setHitboxRelativ(Hitbox hitboxRelativ) {
        this.hitboxRelativ = hitboxRelativ;
    }

    /**
     * @param positionY in PX
     * @return Hitbox
     */
    public Hitbox getHitboxAbsolute(int positionY) {
        return new Hitbox(position.getY() + hitboxRelativ.getTop() + positionY, position.getX() + getWidthInPixel() + hitboxRelativ.getRight(), position.getY() + height + hitboxRelativ.getBottom() + positionY, position.getX() + hitboxRelativ.getLeft());
    }

    protected abstract void checkPosition();

    public void draw(PApplet pApplet) {
        movement += speed;

        if (Math.abs(movement) >= 1) {
            position.setX(position.getX() + (int) movement);
            movement = 0;
        }

        checkPosition();
    }
}
