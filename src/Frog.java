import General.CONSTANTS;
import SpriteLib.*;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PApplet;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class Frog {

    private int width = CONSTANTS.CHUNK_SIZE;
    private int height = CONSTANTS.CHUNK_SIZE;
    private boolean dead = false;


    SequencedSprite sequencedSprite = new SequencedSprite(width, height, 30, ANCHORTYPE.TOP_LEFT);
    private Point positionCurrent = new Point(7 * CONSTANTS.CHUNK_SIZE, 14 * CONSTANTS.CHUNK_SIZE);
    private Point positionAbsolute = new Point(7 * CONSTANTS.CHUNK_SIZE, 14 * CONSTANTS.CHUNK_SIZE);

    public Frog(PApplet pApplet) {
        PImage frogSprite = pApplet.loadImage("assets/frog.png");

        // Movement
        for (int i = 0; i < 12; i++) {
            sequencedSprite.addFrames(pApplet, frogSprite, 16 * i + 8 * i, 0, 1);
        }

        // Death
        sequencedSprite.addFrames(pApplet, frogSprite, 0, 16, 1);
        for (int i = 0; i < 3; i++) {
            sequencedSprite.addFrames(pApplet, frogSprite, 32 + 16 * i + 8 * i, 16, 1);
        }
        for (int i = 0; i < 4; i++) {
            sequencedSprite.addFrames(pApplet, frogSprite, 112 + 16 * i + 8 * i, 16, 1);
        }


        // Movement
        sequencedSprite.addSequence(new Sequence("up", "up", 2));
        sequencedSprite.addSequence(new Sequence("up-move", "up", 1, 0, 0, 1));
        sequencedSprite.addSequence(new Sequence("left", "left", 5));
        sequencedSprite.addSequence(new Sequence("left-move", "left", 4, 3, 3, 4));
        sequencedSprite.addSequence(new Sequence("down", "down", 8));
        sequencedSprite.addSequence(new Sequence("down-move", "down", 7, 6, 6, 7));
        sequencedSprite.addSequence(new Sequence("right", "right", 9));
        sequencedSprite.addSequence(new Sequence("right-move", "right", 10, 11, 11, 10));

        // Death
        sequencedSprite.addSequence(new Sequence("dead", "dead", 19));
        sequencedSprite.addSequence(new Sequence("death-land", "dead", 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12));


        sequencedSprite.gotoSequence("up");
    }

    public Frog(PApplet pApplet, Point startPosition) {
        this(pApplet);
        positionCurrent = startPosition;
        positionAbsolute = startPosition;
    }

    public boolean isDead() {
        return dead;
    }

    public Hitbox getHitbox() {
        return new Hitbox(positionCurrent.getY() + 2, positionCurrent.getX() + width - 2, positionCurrent.getY() + height - 2, positionCurrent.getX() + 2);
    }

    public void keyPressed(int keyCode, Game game) {
        System.out.println("keyPressed  " + keyCode);
        if (dead || positionAbsolute.getX() != positionCurrent.getX() || positionAbsolute.getY() != positionCurrent.getY()) {
            return;
        }

        switch (keyCode) {
            case PConstants.UP:
                goUp();
                game.increaseScoreBy(CONSTANTS.POINTS_PER_STEP);
                break;
            case PConstants.LEFT:
                goLeft();
                break;
            case PConstants.DOWN:
                goDown();
                break;
            case PConstants.RIGHT:
                goRight();
                break;
            default:
                break;
        }
    }

    public void goUp() {
        sequencedSprite.gotoSequence("up-move");
        positionAbsolute.setY(positionAbsolute.getY() - height);
    }

    public void goLeft() {
        sequencedSprite.gotoSequence("left-move");
        positionAbsolute.setX(positionAbsolute.getX() - width);
    }

    public void goDown() {
        sequencedSprite.gotoSequence("down-move");
        positionAbsolute.setY(positionAbsolute.getY() + height);
    }

    public void goRight() {
        sequencedSprite.gotoSequence("right-move");
        positionAbsolute.setX(positionAbsolute.getX() + width);
    }

    public void onDeath() {
        if (dead) return;
        dead = true;
        sequencedSprite.gotoSequence("death-land");

        CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(() -> {
            dead = false;
            positionCurrent = new Point(7 * CONSTANTS.CHUNK_SIZE, 14 * CONSTANTS.CHUNK_SIZE);
            positionAbsolute = new Point(7 * CONSTANTS.CHUNK_SIZE, 14 * CONSTANTS.CHUNK_SIZE);
            sequencedSprite.gotoSequence("up");

        });
    }

    public void draw(PApplet pApplet) {
        int movement = CONSTANTS.SPEED_FROG;
        if (positionAbsolute.getX() < positionCurrent.getX()) {
            positionCurrent.setX(positionCurrent.getX() - movement);
        }
        if (positionAbsolute.getX() > positionCurrent.getX()) {
            positionCurrent.setX(positionCurrent.getX() + movement);
        }
        if (positionAbsolute.getY() < positionCurrent.getY()) {
            positionCurrent.setY(positionCurrent.getY() - movement);
        }
        if (positionAbsolute.getY() > positionCurrent.getY()) {
            positionCurrent.setY(positionCurrent.getY() + movement);
        }
        sequencedSprite.draw(pApplet, positionCurrent);
    }

}
