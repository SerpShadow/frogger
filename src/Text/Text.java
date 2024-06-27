package Text;

import General.CONSTANTS;
import General.UTILS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import SpriteLib.Point;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents text on screen using a custom font from a sprite map.
 * The text's appearance, including position and color, can be customized.
 */
public class Text {

    private final Point position;
    private final TEXT_COLOR textColor;
    private String[] string;

    MultiSprite multiSprite = new MultiSprite(CONSTANTS.CHUNK_SIZE_HALF, CONSTANTS.CHUNK_SIZE_HALF, ANCHORTYPE.TOP_LEFT);

    /**
     * Constructs a text object with a specified position and color,
     * but without an initial string value.
     *
     * @param pApplet   The PApplet context used for rendering.
     * @param position  The on-screen position for the text.
     * @param textColor The color of the text.
     */
    public Text(PApplet pApplet, Point position, TEXT_COLOR textColor) {
        this.position = position;
        this.textColor = textColor;
        PImage spriteMap = pApplet.loadImage("assets/keys.png");

        // Load characters from the spritesheet.
        for (int i = 0; i < 4 * 6; i++) {
            for (int ii = 0; ii < 10; ii++) {
                multiSprite.addFrames(pApplet, spriteMap, ii * CONSTANTS.CHUNK_SIZE_HALF * 2, UTILS.chunksToPixel(i), 1);
            }
        }
    }

    /**
     * Constructs a text object with specified position, color, and initial string value.
     *
     * @param pApplet   The PApplet context used for rendering.
     * @param position  The on-screen position for the text.
     * @param textColor The color of the text.
     * @param string    The initial string value to display.
     */
    public Text(PApplet pApplet, Point position, TEXT_COLOR textColor, String string) {
        this(pApplet, position, textColor);
        this.string = string.toUpperCase().split("");
    }

    /**
     * Sets the string value of the text object, updating what will be displayed on screen.
     *
     * @param string The string value to set.
     */
    public void setString(String string) {
        this.string = string.split("");
    }

    /**
     * Draws the text on the provided PApplet context. Each character of the text is drawn
     * using sprites from a sprite map.
     *
     * @param pApplet The PApplet context used for rendering.
     */
    public void draw(PApplet pApplet) {
        for (int i = 0; i < string.length; i++) {
            String indexes = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ- ©□";
            int indexOfKey = indexes.indexOf(string[i]);
            multiSprite.setFrame(indexOfKey + textColor.getI() * 40);
            multiSprite.draw(pApplet, new Point(position.getX() + i * CONSTANTS.CHUNK_SIZE_HALF, position.getY()));
        }
    }
}
