import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.Sequence;
import SpriteLib.SequencedSprite;
import processing.core.PApplet;
import processing.core.PImage;

public class Snake extends Obstacle {

    private final SequencedSprite sequencedSprite = new SequencedSprite(UTILS.chunksToPixel(2), UTILS.chunksToPixel(1), 1, ANCHORTYPE.TOP_LEFT);

    public Snake(PApplet pApplet, PImage snakeSprite, double speed, int startPositionX) {
        super(2, speed, startPositionX, new Hitbox(2, -2, -3, 2));


        for (int i = 0; i < 3; i++) {
            sequencedSprite.addFrames(pApplet, snakeSprite, i * UTILS.chunksToPixel(2.5), 0, 1);
        }
        for (int i = 0; i < 3; i++) {
            sequencedSprite.addFrames(pApplet, snakeSprite, UTILS.chunksToPixel(7) + i * UTILS.chunksToPixel(2.5), 0, 1);
        }

        sequencedSprite.addSequence(new Sequence("left", "left", 0, 0, 0, 1, 1, 1, 2, 2, 2, 1, 1, 1));
        sequencedSprite.addSequence(new Sequence("right", "right", 3, 3, 3, 4, 4, 4, 5, 5, 5, 4, 4, 4));


        sequencedSprite.gotoSequence("right");

    }

    @Override
    protected void checkPosition() {
        if (getMovementSpeed() > 0) {
            if (getPositionX() >= CONSTANTS.PIXEL_HORIZONTAL) {

                if (UTILS.randomBoolean(0.5)) {
                    setMovementSpeed(getMovementSpeed() * -1);
                    sequencedSprite.gotoSequence("left");
                } else {
                    setPositionX(-UTILS.chunksToPixel(2));
                }
            }
        } else {
            if (getPositionX() <= -UTILS.chunksToPixel(2)) {
                if (UTILS.randomBoolean(0.5)) {
                    setMovementSpeed(getMovementSpeed() * -1);
                    sequencedSprite.gotoSequence("right");
                } else {
                    setPositionX(CONSTANTS.PIXEL_HORIZONTAL);
                }
            }
        }
    }

    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);

        sequencedSprite.draw(pApplet, getPosition());
    }
}
