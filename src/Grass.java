import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;

import SpriteLib.Point;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Grass extends Floor {

    private PImage snakeSprite;

    private MultiSprite multiSprite = new MultiSprite(CONSTANTS.CHUNK_SIZE, CONSTANTS.CHUNK_SIZE, ANCHORTYPE.TOP_LEFT);
    private int positionY;

    private ArrayList<Snake> snakes = new ArrayList<>();

    public Grass(PApplet pApplet, PImage spriteMap, PImage snakeSprite, int positionY) {
        this.positionY = positionY;
        this.snakeSprite = snakeSprite;
        multiSprite.addFrames(pApplet, spriteMap, CONSTANTS.CHUNK_SIZE, UTILS.chunksToPixel(9), 1);

        generateSnakes(pApplet);
    }

    public Grass(PApplet pApplet, PImage spriteMap, int positionY) {
        this.positionY = positionY;
        multiSprite.addFrames(pApplet, spriteMap, CONSTANTS.CHUNK_SIZE, UTILS.chunksToPixel(9), 1);
    }

    public void generateSnakes(PApplet pApplet) {
        snakes = new ArrayList<>();
        int amount = UTILS.getCurrentLevelData().getMedianStripSnakesAmount();
        double speed = UTILS.getCurrentLevelData().getMedianStripSnakesSpeed();

        for (int i = 0; i < amount; i++) {
            snakes.add(new Snake(pApplet, snakeSprite, speed, UTILS.generateVehicleXPosition(UTILS.chunksToPixel(2), i, amount)));
        }
    }

    public boolean checkCollision(Hitbox frogHitbox) {
        for (Snake snake : snakes) {
            if (UTILS.isColliding(frogHitbox, snake.getHitboxAbsolute(UTILS.chunksToPixel(8)))) {
                return true;
            }
        }
        return false;
    }

    public void draw(PApplet pApplet) {
        pApplet.pushMatrix();
        pApplet.translate(0, UTILS.chunksToPixel(positionY));
        for (int i = 0; i < CONSTANTS.CHUNKS_HORIZONTAL; i++) {
            multiSprite.draw(pApplet, new Point(UTILS.chunksToPixel(i), 0));
        }

        for (Snake snake : snakes) {
            snake.draw(pApplet);
        }
        pApplet.popMatrix();
    }
}
