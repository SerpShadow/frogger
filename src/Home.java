import General.CONSTANTS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import SpriteLib.Point;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Home {
    private ArrayList<MultiSprite> multiSprites = new ArrayList<>();
    MultiSprite bottom;

    public Home(PApplet pApplet, PImage spriteMap) {
        bottom = new MultiSprite(8, 8, ANCHORTYPE.TOP_LEFT);
        bottom.addFrames(pApplet, spriteMap, 25 * CONSTANTS.CHUNK_SIZE, 13 * CONSTANTS.CHUNK_SIZE, 6);
        bottom.addFrames(pApplet, spriteMap, 25 * CONSTANTS.CHUNK_SIZE, 14 * CONSTANTS.CHUNK_SIZE, 6);
        bottom.addFrames(pApplet, spriteMap, 25 * CONSTANTS.CHUNK_SIZE, 15 * CONSTANTS.CHUNK_SIZE, 6);


    }

    private void drawHome(PApplet pApplet) {
        int offsetY = (int) (1.5 * CONSTANTS.CHUNK_SIZE);
        for (int i = 0; i < CONSTANTS.CHUNKS_HORIZONTAL * 2; i++) {
            switch (i % 6) {
                case 0:
                    bottom.setFrame(2);
                    bottom.draw(pApplet, new Point(i * 8, offsetY));
                    break;
                case 1:
                case 2:
                    bottom.setFrame(4);
                    bottom.draw(pApplet, new Point(i * 8, offsetY));
                    break;
                case 3:
                    bottom.setFrame(0);
                    bottom.draw(pApplet, new Point(i * 8, offsetY));
                    break;
                case 4:
                case 5:
                    bottom.setFrame(8);
                    bottom.draw(pApplet, new Point(i * 8, offsetY));
                    break;
            }
        }

        for (int i = 0; i < CONSTANTS.CHUNKS_HORIZONTAL * 2; i++) {
            switch (i % 6) {
                case 0:
                    bottom.setFrame(10);
                    bottom.draw(pApplet, new Point(i * 8, offsetY + 8));
                    break;
                case 3:
                    bottom.setFrame(6);
                    bottom.draw(pApplet, new Point(i * 8, offsetY + 8));
                    break;
                case 4:
                case 5:
                    bottom.setFrame(8);
                    bottom.draw(pApplet, new Point(i * 8, offsetY + 8));
                    break;
            }
        }

        for (int i = 0; i < CONSTANTS.CHUNKS_HORIZONTAL * 2; i++) {
            switch (i % 6) {
                case 0:
                    bottom.setFrame(16);
                    bottom.draw(pApplet, new Point(i * 8, offsetY + 16));
                    break;
                case 3:
                    bottom.setFrame(12);
                    bottom.draw(pApplet, new Point(i * 8, offsetY + 16));
                    break;
                case 4:
                case 5:
                    bottom.setFrame(4);
                    bottom.draw(pApplet, new Point(i * 8, offsetY + 16));
                    break;
            }
        }
    }


    public void draw(PApplet pApplet) {
        drawHome(pApplet);

    }
}
