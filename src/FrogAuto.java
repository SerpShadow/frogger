import SpriteLib.Point;
import SpriteLib.Sequence;
import SpriteLib.SequencedSprite;
import processing.core.PApplet;

public class FrogAuto extends Frog {

    public FrogAuto(PApplet pApplet, Point startPosition) {
        super(pApplet, 8, startPosition);

        SequencedSprite sequencedSprite = getSequencedSprite();

        // Animations
        sequencedSprite.addSequence(new Sequence("animation-up", "animation-up", 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1));
        sequencedSprite.addSequence(new Sequence("animation-left", "animation-left", 5, 5, 5, 5, 5, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4));
    }

    public void goToPositionX(int newXPosition) {
        if (newXPosition > getPositionAbsolute().getX()) {
            startAnimationRight();
        } else {
            startAnimationLeft();
        }
        getPositionAbsolute().setX(newXPosition);
    }

    public void goToPositionY(int newYPosition) {
        if (newYPosition > getPositionAbsolute().getY()) {
            startAnimationDown();

        } else {
            startAnimationUp();
        }
        getPositionAbsolute().setY(newYPosition);
    }

    private void startAnimationUp() {
        getSequencedSprite().gotoSequence("animation-up");
    }

    private void startAnimationRight() {
        getSequencedSprite().gotoSequence("animation-right");
    }

    private void startAnimationDown() {
        getSequencedSprite().gotoSequence("animation-down");
    }

    private void startAnimationLeft() {
        getSequencedSprite().gotoSequence("animation-left");
    }

    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);

        if (!isMoving()) {
            getSequencedSprite().gotoSequence("up");
        }
    }
}
