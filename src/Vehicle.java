import General.CONSTANTS;
import General.Hitbox;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import processing.core.PApplet;
import processing.core.PImage;

public class Vehicle extends Obstacle {

    private final MultiSprite multiSprite;

    public Vehicle(PApplet pApplet, PImage spriteMap, VEHICLE_TYPE vehicleType, double speed, int startPosition) {
        super(vehicleType.getWidth(), speed, startPosition, new Hitbox(0, 0, 0, 0));

        multiSprite = new MultiSprite(getWidthInPixel(), getHeight(), ANCHORTYPE.TOP_LEFT);

        switch (vehicleType) {
            case TRUCK:
                multiSprite.addFrames(pApplet, spriteMap, 3 * CONSTANTS.CHUNK_SIZE, 0, 1);
//                hitbox = new Hitbox(position.getY() + 3, getPositionX + width - 2, position.getY() + height - 3, getPositionX + 3);
                break;
            case RACE_CAR:
                multiSprite.addFrames(pApplet, spriteMap, 7 * CONSTANTS.CHUNK_SIZE, 0, 1);
//                hitbox = new Hitbox(position.getY() + 1, getPositionX + width, position.getY() + height - 1, position.getX());
                break;
            case COUPE:
                multiSprite.addFrames(pApplet, spriteMap, 5 * CONSTANTS.CHUNK_SIZE + 8, 0, 1);
//                hitbox = new Hitbox(position.getY() + 3, getPositionX + width - 1, position.getY() + height - 3, position.getX());
                break;
            case BULLDOZER:
                multiSprite.addFrames(pApplet, spriteMap, CONSTANTS.CHUNK_SIZE + 8, 0, 1);
//                hitbox = new Hitbox(position.getY() + 2, getPositionX + width - 1, position.getY() + height - 2, getPositionX + 1);
                break;
            case DUNE_BUGGY:
                multiSprite.addFrames(pApplet, spriteMap, 7 * CONSTANTS.CHUNK_SIZE, CONSTANTS.CHUNK_SIZE, 1);
//                hitbox = new Hitbox(position.getY() + 1, getPositionX + width, position.getY() + height - 1, position.getX());
                break;
            default:
                break;
        }


    }

    void resetPosition() {
        if (getSpeed() > 0) {
            setPositionX(-getWidthInPixel());
        } else {
            setPositionX(14 * CONSTANTS.CHUNK_SIZE);
        }
    }

    protected void checkPosition() {
        if (getSpeed() > 0) {
            if (getPositionX() > CONSTANTS.PIXEL_HORIZONTAL) {
                setPositionX(-getWidthInPixel()); // move object to the left but -width to render outside the screen
            }
        } else {
            if (getPositionX() < -getWidthInPixel()) {
                setPositionX(CONSTANTS.PIXEL_HORIZONTAL); // move object to the right
            }
        }
    }

    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);
        multiSprite.draw(pApplet, getPosition());



    }

}
