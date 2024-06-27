import General.Hitbox;
import General.UTILS;
import SpriteLib.Point;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Turtle class represents a floating turtle that can optionally submerge.
 * It extends the Floating class and uses the Processing library for drawing.
 */
public class Turtle extends Floating {
    private final Hitbox hitbox = new Hitbox(0, -8, 0, 8);

    private final boolean isSubmerging;


    /**
     * Constructs a new Turtle.
     *
     * @param pApplet       the PApplet instance for drawing
     * @param turtleSprite  the sprite image of the turtle
     * @param width         the width of the turtle in chunks
     * @param speed         the speed of the turtle
     * @param startPosition the starting position of the turtle
     * @param isSubmerging  indicates if the turtle is submerging
     */
    public Turtle(PApplet pApplet, PImage turtleSprite, int width, double speed, int startPosition, boolean isSubmerging) {
        super(pApplet, width, speed, startPosition);
        this.isSubmerging = isSubmerging;
        setHitboxRelative(hitbox);

        getMultiSprite().addFrames(pApplet, turtleSprite, 0, 0, 4);
        getMultiSprite().addFrameCopy(3);
        getMultiSprite().addFrames(pApplet, turtleSprite, UTILS.chunksToPixel(4), 0, 2);
        getMultiSprite().addFrameCopy(5);
        getMultiSprite().addFrameCopy(3);
        getMultiSprite().addFrameCopy(3);
    }

    /**
     * Draws the turtle on the given PApplet instance.
     *
     * @param pApplet the PApplet instance for drawing
     */
    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);

        int counter;

        if (isSubmerging) {
            counter = (pApplet.millis() / 300) % 10;
            if (counter == 6) {
                // remove Hitbox
                setHitboxRelative(new Hitbox(0, -getWidthInPixel(), -getHeightInPixel(), 0));
            } else if (counter == 7) {
                setHitboxRelative(hitbox);
            }
        } else {
            counter = (pApplet.millis() / 500) % 3;
        }

        for (int i = 0; i < getWidthInChunks(); i++) {
            int positionX = getPositionX() + UTILS.chunksToPixel(i);
            Point drawPosition = new Point(positionX, getPositionY());
            getMultiSprite().setFrame(counter);
            getMultiSprite().draw(pApplet, drawPosition);

        }
    }

}
