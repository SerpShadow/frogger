import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import SpriteLib.Point;
import processing.core.PApplet;
import processing.core.PImage;

public class Home extends Floor {

    private final MultiSprite multiSpriteWalls = new MultiSprite(CONSTANTS.CHUNK_SIZE_HALF, CONSTANTS.CHUNK_SIZE_HALF, ANCHORTYPE.TOP_LEFT);

    private final HomeElement[] homeElements = new HomeElement[5];

    public Home(PApplet pApplet) {
        PImage homeSprite = pApplet.loadImage("assets/home.png");

        multiSpriteWalls.addFrames(pApplet, homeSprite, 0, UTILS.chunksToPixel(0), 6);
        multiSpriteWalls.addFrames(pApplet, homeSprite, 0, UTILS.chunksToPixel(1), 6);
        multiSpriteWalls.addFrames(pApplet, homeSprite, 0, UTILS.chunksToPixel(2), 6);

        homeElements[0] = new HomeElement(pApplet, UTILS.chunksToPixel(0.5));
        homeElements[1] = new HomeElement(pApplet, UTILS.chunksToPixel(3.5));
        homeElements[2] = new HomeElement(pApplet, UTILS.chunksToPixel(6.5));
        homeElements[3] = new HomeElement(pApplet, UTILS.chunksToPixel(9.5));
        homeElements[4] = new HomeElement(pApplet, UTILS.chunksToPixel(12.5));
    }

    public boolean getAllHomesOccupied() {
        boolean allHomesOccupied = true;
        for (HomeElement homeElement : homeElements) {
            if (!homeElement.isOccupied()) {
                allHomesOccupied = false;
                break;
            }
        }
        return allHomesOccupied;
    }

    public void resetOccupiedHomes() {
        for (HomeElement homeElement : homeElements) {
            homeElement.setOccupied(false);
        }
    }

    public boolean checkCollision(Hitbox frogHitbox) {
        for (HomeElement homeElement : homeElements) {
            if (UTILS.isColliding(frogHitbox, homeElement.getHitboxAbsolute(UTILS.chunksToPixel(2)))) {
                System.out.println("Collision found");
                homeElement.setOccupied(true);
                return true;
            }
        }
        return false;
    }

    private void drawHome(PApplet pApplet) {
        for (int i = 0; i < CONSTANTS.CHUNKS_HORIZONTAL * 2; i++) {
            switch (i % 6) {
                case 0:
                    multiSpriteWalls.setFrame(2);
                    multiSpriteWalls.draw(pApplet, new Point(i * 8, 0));
                    break;
                case 1:
                case 2:
                    multiSpriteWalls.setFrame(4);
                    multiSpriteWalls.draw(pApplet, new Point(i * 8, 0));
                    break;
                case 3:
                    multiSpriteWalls.setFrame(0);
                    multiSpriteWalls.draw(pApplet, new Point(i * 8, 0));
                    break;
                case 4:
                case 5:
                    multiSpriteWalls.setFrame(8);
                    multiSpriteWalls.draw(pApplet, new Point(i * 8, 0));
                    break;
            }
        }

        for (int i = 0; i < CONSTANTS.CHUNKS_HORIZONTAL * 2; i++) {
            switch (i % 6) {
                case 0:
                    multiSpriteWalls.setFrame(10);
                    multiSpriteWalls.draw(pApplet, new Point(i * 8, CONSTANTS.CHUNK_SIZE_HALF));
                    break;
                case 3:
                    multiSpriteWalls.setFrame(6);
                    multiSpriteWalls.draw(pApplet, new Point(i * 8, CONSTANTS.CHUNK_SIZE_HALF));
                    break;
                case 4:
                case 5:
                    multiSpriteWalls.setFrame(8);
                    multiSpriteWalls.draw(pApplet, new Point(i * 8, CONSTANTS.CHUNK_SIZE_HALF));
                    break;
            }
        }

        for (int i = 0; i < CONSTANTS.CHUNKS_HORIZONTAL * 2; i++) {
            switch (i % 6) {
                case 0:
                    multiSpriteWalls.setFrame(16);
                    multiSpriteWalls.draw(pApplet, new Point(i * 8, CONSTANTS.CHUNK_SIZE));
                    break;
                case 3:
                    multiSpriteWalls.setFrame(12);
                    multiSpriteWalls.draw(pApplet, new Point(i * 8, CONSTANTS.CHUNK_SIZE));
                    break;
                case 4:
                case 5:
                    multiSpriteWalls.setFrame(4);
                    multiSpriteWalls.draw(pApplet, new Point(i * 8, CONSTANTS.CHUNK_SIZE));
                    break;
            }
        }
    }


    public void draw(PApplet pApplet) {
        pApplet.pushMatrix();
        pApplet.translate(0, UTILS.chunksToPixel(1.5));
        drawHome(pApplet);

        for (HomeElement homeElement : homeElements) {
            homeElement.draw(pApplet);
        }

        pApplet.popMatrix();

    }
}
