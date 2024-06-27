import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;

/**
 * Abstract class representing a river segment within the game.
 * Provides a foundation for river behaviors, including movement characteristics
 * and interaction with game entities through collision detection.
 */
public abstract class River extends Floor {

    private final RIVER_TYPE riverType;
    private final double movementSpeed;

    /**
     * Constructs a River segment with a specified type and movement speed.
     *
     * @param riverType     The type of the river, which might influence its appearance, behavior, or the way entities interact with it.
     * @param movementSpeed The speed at which entities within the river move, suggesting a current or flow direction.
     */
    public River(RIVER_TYPE riverType, double movementSpeed) {
        this.riverType = riverType;
        this.movementSpeed = movementSpeed;

    }

    /**
     * Gets the river's type.
     *
     * @return The {@code RIVER_TYPE} representing the river's characteristics.
     */
    public RIVER_TYPE getRiverType() {
        return riverType;
    }

    /**
     * Gets the river's movement speed.
     *
     * @return The speed at which the entities within the river are moving.
     */
    public double getMovementSpeed() {
        return movementSpeed;
    }


    /**
     * Determines if there is a collision between the river and a game entity.
     * This abstract method must be implemented by subclasses to specify how collisions with the river are detected.
     *
     * @param frogHitbox The hitbox of the frog to check for collisions with.
     * @return {@code true} if a collision is detected; {@code false} otherwise.
     */
    @Override
    public abstract boolean checkCollision(Hitbox frogHitbox);


    /**
     * Draws the river segment on the game canvas.
     * This abstract method must be implemented by subclasses to include specific drawing logic.
     *
     * @param pApplet The PApplet instance used for drawing.
     */
    @Override
    public abstract void draw(PApplet pApplet);
}
