import SpriteLib.Point;
import SpriteLib.Sequence;
import SpriteLib.SequencedSprite;
import processing.core.PApplet;

public class FrogAuto extends Frog {

    private boolean isParked = true;

    public FrogAuto(PApplet pApplet, Point startPosition, int movementSpeed) {
        super(pApplet, startPosition, movementSpeed);

        SequencedSprite sequencedSprite = getSequencedSprite();

        // Animations
        sequencedSprite.addSequence(new Sequence("animation-up", "animation-up", 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1));
        sequencedSprite.addSequence(new Sequence("animation-left", "animation-left", 5, 5, 5, 5, 5, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4));
    }

    public void goToPositionX(int positionX) {
        if (positionX != getDestinationX()) {
            isParked = false;
            if (positionX > getDestinationX()) {
                startAnimationRight();
            } else {
                startAnimationLeft();
            }
            setDestinationX(positionX);
        }
    }

    public void goToPositionY(int positionY) {
        if (positionY != getDestinationY()) {
            isParked = false;
            if (positionY > getDestinationY()) {
                startAnimationDown();
            } else {
                startAnimationUp();
            }
            setDestinationY(positionY);
        }
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

        if (!isParked && !isMoving()) {
            getSequencedSprite().gotoSequence("up");
            isParked = true;
        }
    }
}
