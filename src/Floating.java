import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import SpriteLib.Point;
import processing.core.PApplet;

public class Floating extends Obstacle {

    private final MultiSprite multiSprite = new MultiSprite(CONSTANTS.CHUNK_SIZE, CONSTANTS.CHUNK_SIZE, ANCHORTYPE.TOP_LEFT);

    public Floating(PApplet pApplet, int width, double speed, int startPosition, Hitbox hitbox) {
        super(width, speed, startPosition, hitbox);

        setPositionX(startPosition);

    }

    public Floating(PApplet pApplet, int width, double speed, int startPosition) {
        this(pApplet, width, speed, startPosition, new Hitbox(0, 0, 0, 0));
    }


    public MultiSprite getMultiSprite() {
        return multiSprite;
    }

    protected void checkPosition() {
        if (getSpeed() > 0) {
            if (getPositionX() + getWidthInPixel() > CONSTANTS.PIXEL_HORIZONTAL * 2) {
                setPositionX(-getWidthInPixel()); // move object to the left but -width to render outside the screen
            }
        } else {
            if (getPositionX() < -getWidthInPixel()) {
                setPositionX(CONSTANTS.PIXEL_HORIZONTAL * 2 - getWidthInPixel()); // move object to the right
            }
        }
    }

    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);


    }
}
