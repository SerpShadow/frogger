import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.Point;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a log floating on water in the game. Logs serve as platforms that characters can utilize to cross water sections.
 * This class manages the log's visual appearance, including its texture and size, and integrates with the game's physics via a custom hitbox.
 */
public class Log extends Floating {

    private Hitbox hitbox = new Hitbox(0, -8, 0, 8);

    /**
     * Constructs a Log object with specified attributes and applies a sprite for its appearance.
     *
     * @param pApplet       The Processing applet used for drawing, providing context and graphical functionality.
     * @param logSprite     The sprite image for the log, which will be rendered to represent the log in the game.
     * @param width         The width of the log in 'chunks', affecting how long the log appears and interacts in the game world.
     * @param speed         The movement speed of the log, defining how quickly it floats across the water.
     * @param startPosition The initial X coordinate of the log, determining where it will begin floating from.
     */
    public Log(PApplet pApplet, PImage logSprite, int width, double speed, int startPosition) {
//    public Log(PApplet pApplet, PImage logSprite,  int width, double speed, int startPosition, double probabilityCrocodile) {
        super(pApplet, width, speed, startPosition);
        setHitboxRelative(hitbox);

        // Adds the log sprite frames to the MultiSprite. Assumes the sprite sheet has 3 frames for the log's appearance: start, middle, and end sections.
        getMultiSprite().addFrames(pApplet, logSprite, 0, 0, 3);
    }

    /**
     * Draws the log on the canvas. This method overrides the `draw` method from `Floating` to customize how logs are rendered.
     * It adjusts the sprite frame depending on the log section being drawn (start, middle, end) to create a continuous log appearance.
     *
     * @param pApplet The PApplet instance (canvas) on which to draw the Log.
     */
    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);

        // Iterates through the width of the log in chunks to draw each segment with the correct sprite frame.
        for (int i = 0; i < getWidthInChunks(); i++) {
            int positionX = getPositionX() + UTILS.chunksToPixel(i);
            Point drawPosition = new Point(positionX, getPositionY());

            // Determines which sprite frame to use based on the segment's position in the log.
            if (i == 0) {
                getMultiSprite().setFrame(0); // Start of the log
            } else if (i == getWidthInChunks() - 1) {
                getMultiSprite().setFrame(2); // Start of the log
            } else {
                getMultiSprite().setFrame(1); // Middle sections of the log
            }
            // Draws the segment at its calculated position.
            getMultiSprite().draw(pApplet, drawPosition);
        }
    }
}
