import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;

import SpriteLib.Point;
import processing.core.PApplet;
import processing.core.PImage;

public class Grass {

    private MultiSprite multiSprite;
    private int topPosition;

    public Grass(PApplet pApplet, PImage spriteMap, int topPosition) {
        this.topPosition = topPosition;
        multiSprite = new MultiSprite(16, 16, ANCHORTYPE.TOP_LEFT);
        multiSprite.addFrames(pApplet, spriteMap, 16, 144, 1);
    }

    public void draw(PApplet pApplet) {
        for (int i = 0; i < 14; i++) {
            multiSprite.draw(pApplet, new Point(i * 16, topPosition));
        }
    }
}
