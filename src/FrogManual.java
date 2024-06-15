import General.CONSTANTS;
import General.UTILS;
import SpriteLib.Sequence;
import SpriteLib.SequencedSprite;
import processing.core.PApplet;
import processing.core.PConstants;

public class FrogManual extends Frog {
    public FrogManual(PApplet pApplet) {
        super(pApplet, CONSTANTS.SPEED_FROG);

        SequencedSprite sequencedSprite = getSequencedSprite();

        // Movement Sequences
        // sequencedSprite.addSequence(new Sequence("up", "up", 2)); already added in Frog
        sequencedSprite.addSequence(new Sequence("up-move", "up", 1, 0, 0, 1));
        sequencedSprite.addSequence(new Sequence("left", "left", 5));
        sequencedSprite.addSequence(new Sequence("left-move", "left", 4, 3, 3, 4));
        sequencedSprite.addSequence(new Sequence("down", "down", 8));
        sequencedSprite.addSequence(new Sequence("down-move", "down", 7, 6, 6, 7));
        sequencedSprite.addSequence(new Sequence("right", "right", 9));
        sequencedSprite.addSequence(new Sequence("right-move", "right", 10, 11, 11, 10));
    }

    public void keyPressed(int keyCode, Game game) {
        if (isInputBlocked() || isDead() || getPositionAbsolute().getX() != getPositionCurrent().getX() || getPositionAbsolute().getY() != getPositionCurrent().getY()) {
            return;
        }

        switch (keyCode) {
            case PConstants.UP:
                goUp();
                game.increaseScoreBy(CONSTANTS.POINTS_PER_STEP);
                break;
            case PConstants.LEFT:
                goLeft();
                break;
            case PConstants.DOWN:
                if (getPositionAbsolute().getY() < UTILS.chunksToPixel(14)) {
                    goDown();
                }
                break;
            case PConstants.RIGHT:
                goRight();
                break;
            default:
                break;
        }
    }

    public void goUp() {
        getSequencedSprite().gotoSequence("up-move");
        setPositionAbsoluteY(getPositionAbsolute().getY() - getHeight());
    }

    public void goLeft() {
        getSequencedSprite().gotoSequence("left-move");
        setPositionAbsoluteX(getPositionAbsolute().getX() - getWidth());
    }

    public void goDown() {
        getSequencedSprite().gotoSequence("down-move");
        setPositionAbsoluteY(getPositionAbsolute().getY() + getHeight());
    }

    public void goRight() {
        getSequencedSprite().gotoSequence("right-move");
        setPositionAbsoluteX(getPositionAbsolute().getX() + getWidth());
    }
}
