import General.Hitbox;
import General.UTILS;
import SpriteLib.Point;
import processing.core.PApplet;


/**
 * Represents an abstract game object in a 2D game environment.
 * This class provides the basic properties and behaviors that game objects
 * share, such as position, movement speed, and dimensional information.
 * <p>
 * The class is designed to be extended by more specific game objects that would implement specific behaviors and visuals.
 */
public abstract class GameObject {
    private final int width;
    private final int height = 1;

    private double movementSpeed;
    private final Point position = new Point();
    private final Point destination = new Point();
    private double fixedMovementX = 0;

    private Hitbox hitboxRelative;

    /**
     * Constructs a GameObject with specified dimensions, movement speed, starting position, and hitbox.
     *
     * @param width          The width of the object in 'chunks'.
     * @param movementSpeed  The speed at which the object moves.
     * @param startPositionX The starting X coordinate of the object.
     * @param hitboxRelative The relative hitbox of the object for collision detection.
     */
    public GameObject(int width, double movementSpeed, int startPositionX, Hitbox hitboxRelative) {
        this.width = width;
        this.movementSpeed = movementSpeed;
        this.hitboxRelative = hitboxRelative;

        position.setX(startPositionX);
        destination.setX(startPositionX);
    }

    /**
     * Returns the width of the object in chunks.
     *
     * @return The width in chunks.
     */
    public int getWidthInChunks() {
        return width;
    }

    /**
     * Returns the width of the object in pixels.
     *
     * @return The width in pixels.
     */
    public int getWidthInPixel() {
        return UTILS.chunksToPixel(width);
    }

    /**
     * Returns the height of the object in chunks.
     *
     * @return The height in chunks.
     */
    public int getHeightInChunks() {
        return height;
    }

    /**
     * Returns the height of the object in pixels.
     *
     * @return The height in pixels.
     */
    public int getHeightInPixel() {
        return UTILS.chunksToPixel(height);
    }

    /**
     * Returns the object's movement speed.
     *
     * @return The movement speed.
     */
    public double getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Sets the movement speed of the object.
     *
     * @param movementSpeed The movement speed to set.
     */
    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /**
     * Gets the current position of the object.
     *
     * @return The current position as a Point.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Gets the current x-position of the object.
     *
     * @return The current x-position in pixels.
     */
    public int getPositionX() {
        return position.getX();
    }

    /**
     * Gets the current y-position of the object.
     *
     * @return The current y-position in pixels.
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
     * Gets the current destination of the object.
     *
     * @return The current destination as a Point.
     */
    public Point getDestination() {
        return destination;
    }

    /**
     * Gets the current x-destination of the object.
     *
     * @return The current x-destination in pixels.
     */
    public int getDestinationX() {
        return destination.getX();
    }

    /**
     * Gets the current y-destination of the object.
     *
     * @return The current y-destination in pixels.
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
     * Calculates and returns the absolute hitbox of the object based
     * on its current position and the relative hitbox provided.
     *
     * @param positionY The Y coordinate of the object's current position in pixels.
     *                  Used to adjust the absolute hitbox accordingly.
     * @return The absolute hitbox of the object.
     */
    public Hitbox getHitboxAbsolute(int positionY) {
        return new Hitbox(position.getY() + hitboxRelative.getTop() + positionY, position.getX() + getWidthInPixel() + hitboxRelative.getRight(), position.getY() + getHeightInPixel() + hitboxRelative.getBottom() + positionY, position.getX() + hitboxRelative.getLeft());
    }

    public void setHitboxRelative(Hitbox hitboxRelative) {
        this.hitboxRelative = hitboxRelative;
    }

    /**
     * Determines if the object is currently moving towards its destination.
     *
     * @return true if the object is moving; false otherwise.
     */
    public boolean isMoving() {
        return getPositionX() != getDestinationX() || getPositionY() != getDestinationY();
    }

    /**
     * Draws the object on the canvas.
     * This method is meant to be adapted by subclasses to provide custom drawing functionality.
     *
     * @param pApplet the PApplet instance for drawing
     */
    public void draw(PApplet pApplet) {
        if (Math.abs(fixedMovementX) >= 1) {
            setPositionX(getPositionX() + (int) fixedMovementX);
            setDestinationX(getDestinationX() + (int) fixedMovementX);
            fixedMovementX = 0;
        }
    }
}
