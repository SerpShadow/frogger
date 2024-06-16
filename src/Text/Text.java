package Text;

import General.CONSTANTS;
import General.UTILS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import SpriteLib.Point;
import processing.core.PApplet;
import processing.core.PImage;

public class Text {

    private final Point position;
    private final TEXT_COLOR textColor;

    private String[] string;

    MultiSprite multiSprite = new MultiSprite(CONSTANTS.CHUNK_SIZE_HALF, CONSTANTS.CHUNK_SIZE_HALF, ANCHORTYPE.TOP_LEFT);

    public Text(PApplet pApplet, Point position, TEXT_COLOR textColor) {
        this.position = position;
        this.textColor = textColor;
        PImage spriteMap = pApplet.loadImage("assets/keys.png");

        for (int i = 0; i < 4 * 6; i++) {
            for (int ii = 0; ii < 10; ii++) {
                multiSprite.addFrames(pApplet, spriteMap, ii * CONSTANTS.CHUNK_SIZE_HALF * 2, UTILS.chunksToPixel(i), 1);
            }
        }
    }

    public Text(PApplet pApplet, Point position, TEXT_COLOR textColor, String string) {
        this(pApplet, position, textColor);
        this.string = string.toUpperCase().split("");
    }

    public void setString(String string) {
        this.string = string.split("");
    }

    public void draw(PApplet pApplet) {
        for (int i = 0; i < string.length; i++) {
            String indexes = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ- ©□";
            int indexOfKey = indexes.indexOf(string[i]);
            multiSprite.setFrame(indexOfKey + textColor.getI() * 40);
            multiSprite.draw(pApplet, new Point(position.getX() + i * CONSTANTS.CHUNK_SIZE_HALF, position.getY()));
        }
    }
}
