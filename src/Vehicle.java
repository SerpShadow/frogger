import General.CONSTANTS;
import General.Hitbox;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a vehicle object in the game, which is a type of obstacle that the player must avoid.
 * Vehicles are visually represented with a `MultiSprite` that can handle multiple vehicle types.
 */
public class Vehicle extends Obstacle {

    private final MultiSprite multiSprite;

    /**
     * Constructs a Vehicle object with a specified sprite map, vehicle type, speed, and starting position.
     *
     * @param pApplet       The Processing applet used for drawing, which provides context and graphical functionality.
     * @param spriteMap     The sprite map image containing the vehicle graphics.
     * @param vehicleType   The type of the vehicle, which determines its appearance and dimensions.
     * @param speed         The horizontal movement speed of the vehicle.
     * @param startPosition The starting X coordinate of the vehicle.
     */
    public Vehicle(PApplet pApplet, PImage spriteMap, VEHICLE_TYPE vehicleType, double speed, int startPosition) {
        super(vehicleType.getWidth(), speed, startPosition, new Hitbox(0, 0, 0, 0));

        multiSprite = new MultiSprite(getWidthInPixel(), getHeightInPixel(), ANCHORTYPE.TOP_LEFT);

        switch (vehicleType) {
            case TRUCK:
                multiSprite.addFrames(pApplet, spriteMap, 3 * CONSTANTS.CHUNK_SIZE, 0, 1);
                setHitboxRelative(new Hitbox(3, -2, -3, 3));
                break;
            case RACE_CAR:
                multiSprite.addFrames(pApplet, spriteMap, 7 * CONSTANTS.CHUNK_SIZE, 0, 1);
                setHitboxRelative(new Hitbox(1, 0, -1, 0));
                break;
            case COUPE:
                multiSprite.addFrames(pApplet, spriteMap, 5 * CONSTANTS.CHUNK_SIZE + 8, 0, 1);
                setHitboxRelative(new Hitbox(3, -1, -3, 0));
                break;
            case BULLDOZER:
                multiSprite.addFrames(pApplet, spriteMap, CONSTANTS.CHUNK_SIZE + 8, 0, 1);
                setHitboxRelative(new Hitbox(2, -1, -2, 1));
                break;
            case DUNE_BUGGY:
                multiSprite.addFrames(pApplet, spriteMap, 7 * CONSTANTS.CHUNK_SIZE, CONSTANTS.CHUNK_SIZE, 1);
                setHitboxRelative(new Hitbox(1, 0, -1, 0));
                break;
            default:
                break;
        }


    }

    /**
     * Checks and updates the vehicle's position based on its movement speed and the screen boundary.
     * Vehicles reappear on the opposite side of the screen once they move off-screen.
     */
    @Override
    protected void checkPosition() {
        if (getMovementSpeed() > 0) {
            if (getPositionX() > CONSTANTS.PIXEL_HORIZONTAL) {
                setPositionX(-getWidthInPixel()); // move object to the left but -width to render outside the screen
            }
        } else {
            if (getPositionX() < -getWidthInPixel()) {
                setPositionX(CONSTANTS.PIXEL_HORIZONTAL); // move object to the right
            }
        }
    }

    /**
     * Draws the vehicle on the given PApplet canvas. This includes moving the vehicle based on its fixed movement and drawing its sprite.
     *
     * @param pApplet The PApplet instance (canvas) on which to draw the vehicle.
     */

    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);
        multiSprite.draw(pApplet, getPosition());


    }

}
