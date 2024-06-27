import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.Sequence;
import SpriteLib.SequencedSprite;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a snake obstacle in the game. The snake moves horizontally across the screen and can change direction based on its position and a random factor.
 * It uses an animated sprite to visually represent its movement.
 */
public class Snake extends Obstacle {

    private final SequencedSprite sequencedSprite = new SequencedSprite(UTILS.chunksToPixel(2), UTILS.chunksToPixel(1), 1, ANCHORTYPE.TOP_LEFT);

    /**
     * Constructs a Snake object with specified parameters.
     *
     * @param pApplet        The Processing applet used for drawing, which provides context and graphical functionality.
     * @param snakeSprite    The sprite sheet image for the snake's animation.
     * @param speed          The horizontal movement speed of the snake.
     * @param startPositionX The starting X coordinate of the snake.
     */
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

    /**
     * Checks the snake's position and updates its movement direction and position based on its current state and position.
     * This method allows the snake to bounce back at the edges or randomly switch directions.
     */
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

    /**
     * Draws the snake on the provided PApplet. This includes moving the snake based on its fixed movement and drawing its animated sprite.
     *
     * @param pApplet The PApplet instance (canvas) on which to draw the snake.
     */
    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);

        sequencedSprite.draw(pApplet, getPosition());
    }
}
