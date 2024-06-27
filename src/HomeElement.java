import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a home or goal element in the game, where players aim to safely guide the frog.
 * It can toggle its occupancy state and change its appearance based on whether it is occupied.
 */
public class HomeElement extends Obstacle {

    private boolean occupied = false;
    private final MultiSprite multiSprite = new MultiSprite(CONSTANTS.CHUNK_SIZE, CONSTANTS.CHUNK_SIZE, ANCHORTYPE.TOP_LEFT);

    /**
     * Constructs a HomeElement object at the specified starting position.
     * Initializes its appearance with sprites representing its occupied and unoccupied states.
     *
     * @param pApplet        The Processing applet used for drawing and loading resources.
     * @param startPositionX The initial X coordinate of the home element.
     */
    public HomeElement(PApplet pApplet, int startPositionX) {
        super(1, 0, startPositionX, new Hitbox(0, -4, 0, 4));

        PImage homeElementsSprite = pApplet.loadImage("assets/home-elements.png");
        for (int i = 0; i < 5; i++) {
            multiSprite.addFrames(pApplet, homeElementsSprite, i * UTILS.chunksToPixel(1.5), 0, 1);
        }
    }

    /**
     * Returns whether the home element is currently occupied.
     *
     * @return {@code true} if the element is occupied, otherwise {@code false}.
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Sets the occupancy state of the home element.
     *
     * @param occupied The occupancy state to set; {@code true} if the space is now occupied, otherwise {@code false}.
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * This method is overridden to perform a no-operation since HomeElement objects do not themselves move.
     */
    @Override
    protected void checkPosition() {
        // not used, as this element does not move
    }

    /**
     * Draws the HomeElement on the game board, applying different appearances based on its occupancy state.
     *
     * @param pApplet The PApplet instance (canvas) on which to draw the HomeElement.
     */
    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);
        // Adjusts drawing position to accommodate sprite's anchor point
        pApplet.pushMatrix();
        pApplet.translate(0, UTILS.chunksToPixel(0.5));
        // Selects and draws the appropriate sprite based on the occupancy state
        if (occupied) {
            multiSprite.setFrame(1); // Frame 1 represents the occupied state
        } else {
            // todo: handle stuff such as crocodile and fly
        }
        multiSprite.draw(pApplet, getPosition());

        pApplet.popMatrix();

    }


}
