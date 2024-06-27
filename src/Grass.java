import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;

import SpriteLib.Point;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * Represents a grass section within the game, which can optionally contain snakes that pose a hazard.
 * Extends the {@link Floor} class to add specific functionality for grass terrain.
 */
public class Grass extends Floor {

    private PImage snakeSprite;

    private final MultiSprite multiSprite = new MultiSprite(CONSTANTS.CHUNK_SIZE, CONSTANTS.CHUNK_SIZE, ANCHORTYPE.TOP_LEFT);
    private final int positionY;

    private ArrayList<Snake> snakes = new ArrayList<>();

    /**
     * Constructs a grass section with specified sprites for the ground and snakes, and sets its vertical position.
     * Also, initializes and randomly places snakes within the grass section.
     *
     * @param pApplet     The PApplet context used for drawing.
     * @param spriteMap   The sprite image for the grass.
     * @param snakeSprite The sprite image for the snakes.
     * @param positionY   The vertical position of the grass section.
     */
    public Grass(PApplet pApplet, PImage spriteMap, PImage snakeSprite, int positionY) {
        this.positionY = positionY;
        this.snakeSprite = snakeSprite;
        multiSprite.addFrames(pApplet, spriteMap, CONSTANTS.CHUNK_SIZE, UTILS.chunksToPixel(9), 1);

        generateSnakes(pApplet);
    }

    /**
     * Constructs a grass section without snakes, setting only its sprite and vertical position.
     *
     * @param pApplet   The PApplet context used for drawing.
     * @param spriteMap The sprite image for the grass.
     * @param positionY The vertical position of the grass section.
     */
    public Grass(PApplet pApplet, PImage spriteMap, int positionY) {
        this.positionY = positionY;
        multiSprite.addFrames(pApplet, spriteMap, CONSTANTS.CHUNK_SIZE, UTILS.chunksToPixel(9), 1);
    }

    /**
     * Generates and randomly places a specified number of snakes within the grass section based on the current level's settings.
     *
     * @param pApplet The PApplet context used for drawing the snakes.
     */
    public void generateSnakes(PApplet pApplet) {
        snakes = new ArrayList<>();
        int amount = UTILS.getCurrentLevelData().getMedianStripSnakesAmount();
        double speed = UTILS.getCurrentLevelData().getMedianStripSnakesSpeed();

        for (int i = 0; i < amount; i++) {
            snakes.add(new Snake(pApplet, snakeSprite, speed, UTILS.generateVehicleXPosition(UTILS.chunksToPixel(2), i, amount)));
        }
    }

    /**
     * Checks if the player's frog collides with any snakes in the grass section.
     *
     * @param frogHitbox The hitbox of the player's frog.
     * @return {@code true} if a collision is detected, {@code false} otherwise.
     */
    public boolean checkCollision(Hitbox frogHitbox) {
        for (Snake snake : snakes) {
            if (UTILS.isColliding(frogHitbox, snake.getHitboxAbsolute(UTILS.chunksToPixel(8)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Draws the grass section and any snakes within it to the provided {@code PApplet}.
     *
     * @param pApplet The PApplet context used for drawing.
     */
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
