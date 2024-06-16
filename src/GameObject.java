import General.Hitbox;
import General.UTILS;
import SpriteLib.Point;
import processing.core.PApplet;

public abstract class GameObject {
    private final int width;
    private final int height = 1;

    private double movementSpeed;
    private final Point position = new Point();
    private final Point destination = new Point();
    private double fixedMovementX = 0;

    private Hitbox hitboxRelative;

    public GameObject(int width, double movementSpeed, int startPositionX, Hitbox hitboxRelative) {
        this.width = width;
        this.movementSpeed = movementSpeed;
        this.hitboxRelative = hitboxRelative;

        position.setX(startPositionX);
        destination.setX(startPositionX);
    }

    public int getWidthInChunks() {
        return width;
    }

    public int getWidthInPixel() {
        return UTILS.chunksToPixel(width);
    }

    public int getHeightInChunks() {
        return height;
    }

    public int getHeightInPixel() {
        return UTILS.chunksToPixel(height);
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /**
     * @return position in Point
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @return position in PX
     */
    public int getPositionX() {
        return position.getX();
    }

    /**
     * @return position in PX
     */
    public int getPositionY() {
        return position.getY();
    }

    /**
     * @param positionX in PX
     */
    public void setPositionX(int positionX) {
        position.setX(positionX);
    }

    /**
     * @param positionY in PX
     */
    public void setPositionY(int positionY) {
        position.setY(positionY);
    }

    /**
     * @return position in Point
     */
    public Point getDestination() {
        return destination;
    }

    /**
     * @return position in PX
     */
    public int getDestinationX() {
        return destination.getX();
    }

    /**
     * @return position in PX
     */
    public int getDestinationY() {
        return destination.getY();
    }

    /**
     * @param positionX in PX
     */
    public void setDestinationX(int positionX) {
        destination.setX(positionX);
    }

    /**
     * @param positionY in PX
     */
    public void setDestinationY(int positionY) {
        destination.setY(positionY);
    }

    /**
     * @return fixedMovementX in PX
     */
    public double getFixedMovementX() {
        return fixedMovementX;
    }

    /**
     * @param fixedMovementX in PX
     */
    public void setFixedMovementX(double fixedMovementX) {
        this.fixedMovementX = fixedMovementX;
    }

    /**
     * @param amount in PX
     */
    public void increaseFixedMovementXBy(double amount) {
        fixedMovementX += amount;
    }

    /**
     * @param positionY in PX
     * @return Hitbox
     */
    public Hitbox getHitboxAbsolute(int positionY) {
        return new Hitbox(position.getY() + hitboxRelative.getTop() + positionY, position.getX() + getWidthInPixel() + hitboxRelative.getRight(), position.getY() + getHeightInPixel() + hitboxRelative.getBottom() + positionY, position.getX() + hitboxRelative.getLeft());
    }

    public void setHitboxRelative(Hitbox hitboxRelative) {
        this.hitboxRelative = hitboxRelative;
    }

    public boolean isMoving() {
        return getPositionX() != getDestinationX() || getPositionY() != getDestinationY();
    }


    public void draw(PApplet pApplet) {
        if (Math.abs(fixedMovementX) >= 1) {
            setPositionX(getPositionX() + (int) fixedMovementX);
            setDestinationX(getDestinationX() + (int) fixedMovementX);
            fixedMovementX = 0;
        }
    }
}
