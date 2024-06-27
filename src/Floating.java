import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import SpriteLib.Point;
import processing.core.PApplet;

/**
 * Represents a floating object in the game, which could serve various purposes
 * such as obstacles or platforms for the player to navigate across water sections.
 * This class manages the graphical representation and behavior of such objects.
 */
public class Floating extends Obstacle {

    private final MultiSprite multiSprite = new MultiSprite(CONSTANTS.CHUNK_SIZE, CONSTANTS.CHUNK_SIZE, ANCHORTYPE.TOP_LEFT);

    /**
     * Constructs a Floating object with specified properties and a custom hitbox.
     *
     * @param pApplet       The Processing applet used for drawing, providing context and functionality.
     * @param width         The width of the object, affecting both its appearance and collision detection.
     * @param speed         The movement speed of the object, determining how fast it moves across the screen.
     * @param startPosition The initial X coordinate where the object is placed in the game world.
     * @param hitbox        A custom hitbox for the object, used in collision detection to determine interactions with other objects.
     */
    public Floating(PApplet pApplet, int width, double speed, int startPosition, Hitbox hitbox) {
        super(width, speed, startPosition, hitbox);

        setPositionX(startPosition);

    }

    /**
     * Constructs a Floating object with specified properties but uses a default hitbox.
     *
     * @param pApplet       The Processing applet used for drawing, providing context and functionality.
     * @param width         The width of the object, affecting both its appearance and collision detection.
     * @param speed         The movement speed of the object, determining how fast it moves across the screen.
     * @param startPosition The initial X coordinate where the object is placed in the game world.
     */
    public Floating(PApplet pApplet, int width, double speed, int startPosition) {
        this(pApplet, width, speed, startPosition, new Hitbox(0, 0, 0, 0));
    }


    /**
     * Provides access to the MultiSprite associated with the Floating object.
     * This can be used to manage the object's appearance, such as adding or changing sprites.
     *
     * @return The MultiSprite object associated with this Floating object.
     */
    public MultiSprite getMultiSprite() {
        return multiSprite;
    }


    /**
     * Checks the position of the Floating object and wraps it around the screen edges to ensure
     * continuous movement. Upon reaching a screen edge, the object's position is updated to
     * reappear on the opposite side of the screen.
     */
    @Override
    protected void checkPosition() {
        if (getMovementSpeed() > 0) {
            if (getPositionX() + getWidthInPixel() > CONSTANTS.PIXEL_HORIZONTAL * 2) {
                setPositionX(-getWidthInPixel()); // move object to the left but -width to render outside the screen
            }
        } else {
            if (getPositionX() < -getWidthInPixel()) {
                setPositionX(CONSTANTS.PIXEL_HORIZONTAL * 2 - getWidthInPixel()); // move object to the right
            }
        }
    }

    /**
     * Draws the Floating object on the canvas.
     *
     * @param pApplet The PApplet instance (canvas) on which to draw the Floating object.
     */
    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);


    }
}
