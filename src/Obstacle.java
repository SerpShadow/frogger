import General.Hitbox;
import processing.core.PApplet;

/**
 * Represents an abstract obstacle in the game.
 * This class extends {@link GameObject} with added behaviors specific to game obstacles,
 * such as automatic movement and position checking.
 * <p>
 * Specific types of obstacles should extend this class and implement the {@code checkPosition} method
 * to define custom behaviors on movement boundaries or interactions with other game objects.
 */
public abstract class Obstacle extends GameObject {

    /**
     * Constructs an Obstacle with specified dimensions, movement speed, starting position, and hitbox.
     *
     * @param width          The width of the obstacle in 'chunks'.
     * @param movementSpeed  The speed at which the obstacle moves.
     * @param startPositionX The starting X coordinate of the obstacle.
     * @param hitboxRelative The relative hitbox of the obstacle for collision detection.
     */
    public Obstacle(int width, double movementSpeed, int startPositionX, Hitbox hitboxRelative) {
        super(width, movementSpeed, startPositionX, hitboxRelative);
    }

    /**
     * Constructs an Obstacle with specified dimensions and movement speed, starting position, but without a custom hitbox.
     * <p>
     * This constructor creates an obstacle with a default hitbox same size as the game object.
     *
     * @param width          The width of the obstacle in 'chunks'.
     * @param movementSpeed  The speed at which the obstacle moves.
     * @param startPositionX The starting X coordinate of the obstacle.
     */
    public Obstacle(int width, int movementSpeed, int startPositionX) {
        this(width, movementSpeed, startPositionX, new Hitbox(0, 0, 0, 0));
    }

    /**
     * This method is used to check and update the obstacle's position.
     * Implementations should ensure the obstacle adheres to game boundaries and
     * interacts with the game environment as expected.
     * <p>
     * It must be implemented by subclasses to define specific obstacle behavior.
     */
    protected abstract void checkPosition();

    /**
     * Draws the obstacle on the given PApplet canvas and updates its position according to its fixed movement.
     * <p>
     * This method overrides the {@code draw} method in {@link GameObject} to include automatic movement and position checking before drawing.
     *
     * @param pApplet The PApplet instance (canvas) on which to draw the obstacle.
     */
    public void draw(PApplet pApplet) {
        setFixedMovementX(getFixedMovementX() + getMovementSpeed());
        checkPosition();
        super.draw(pApplet);
    }
}
