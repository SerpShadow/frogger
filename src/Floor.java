import General.Hitbox;
import processing.core.PApplet;

/**
 * Represents an abstract concept of a floor or ground within the game. This class provides a framework for implementing collision detection with game entities.
 * Classes extending this abstract class are expected to provide concrete implementations for checking collisions between the floor and entities.
 */
public abstract class Floor {
    /**
     * Checks for collisions between the floor and a game entity based on their hitboxes.
     * Implementing classes should provide the specific logic to determine if a collision occurs between the floor and an entity.
     *
     * @param frogHitbox The hitbox of the frog or any other entity that may collide with the floor.
     * @return {@code true} if there is a collision between the entity and the floor, {@code false} otherwise.
     */
    public abstract boolean checkCollision(Hitbox frogHitbox);


    /**
     * Draws the floor element to the screen.
     * Implementing classes are expected to define how they visually represent themselves within the game environment.
     *
     * @param pApplet The PApplet instance used for drawing the floor element. Provides drawing methods and the graphical context.
     */
    public abstract void draw(PApplet pApplet);

}
