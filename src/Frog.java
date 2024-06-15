import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.*;
import processing.core.PImage;
import processing.core.PApplet;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class Frog {

    private final int width = CONSTANTS.CHUNK_SIZE;
    private final int height = CONSTANTS.CHUNK_SIZE;
    private boolean dead = false;
    private boolean inputBlocked = false;


    private final SequencedSprite sequencedSprite = new SequencedSprite(width, height, 30, ANCHORTYPE.TOP_LEFT);
    private Point positionCurrent = new Point(7 * CONSTANTS.CHUNK_SIZE, 14 * CONSTANTS.CHUNK_SIZE);
    private Point positionAbsolute = new Point(7 * CONSTANTS.CHUNK_SIZE, 14 * CONSTANTS.CHUNK_SIZE);

    private final int movementSpeed;
    private double movementX = 0;

    public Frog(PApplet pApplet, int movementSpeed) {
        this.movementSpeed = movementSpeed;

        // Movement Frames
        PImage frogSprite = pApplet.loadImage("assets/frog.png");
        for (int i = 0; i < 12; i++) {
            sequencedSprite.addFrames(pApplet, frogSprite, 16 * i + 8 * i, 0, 1);
        }

        // Death Frames
        PImage frogDeathSprite = pApplet.loadImage("assets/frog-death.png");
        sequencedSprite.addFrames(pApplet, frogDeathSprite, 0, 0, 1);
        for (int i = 0; i < 3; i++) {
            sequencedSprite.addFrames(pApplet, frogDeathSprite, 32 + 16 * i + 8 * i, 0, 1);
        }
        for (int i = 0; i < 4; i++) {
            sequencedSprite.addFrames(pApplet, frogDeathSprite, 112 + 16 * i + 8 * i, 0, 1);
        }


        // Death Sequences
        sequencedSprite.addSequence(new Sequence("dead", "dead", 19));
        sequencedSprite.addSequence(new Sequence("death-water", "dead", 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12));
        sequencedSprite.addSequence(new Sequence("death-land", "dead", 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12));


        sequencedSprite.addSequence(new Sequence("up", "up", 2));
        sequencedSprite.gotoSequence("up");
    }

    public Frog(PApplet pApplet, int movementSpeed, Point startPosition) {
        this(pApplet, movementSpeed);
        positionCurrent = new Point(startPosition.getX(), startPosition.getY());
        positionAbsolute = new Point(startPosition.getX(), startPosition.getY());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isInputBlocked() {
        return inputBlocked;
    }

    public SequencedSprite getSequencedSprite() {
        return sequencedSprite;
    }

    public Point getPositionCurrent() {
        return positionCurrent;
    }

    public void setPositionCurrentX(int positionX) {
        positionCurrent.setX(positionX);
    }

    public Point getPositionAbsolute() {
        return positionAbsolute;
    }

    public void setPositionAbsolute(Point positionAbsolute) {
        this.positionAbsolute = positionAbsolute;
    }

    public void setPositionAbsoluteX(int positionX) {
        positionAbsolute.setX(positionX);
    }

    public void setPositionAbsoluteY(int positionY) {
        positionAbsolute.setY(positionY);
    }

    public void increaseMovementXBy(double amount) {
        movementX += amount;
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isMoving() {
        return positionCurrent.getX() != positionAbsolute.getX() || positionCurrent.getY() != positionAbsolute.getY();
    }

    public Hitbox getHitbox() {
        return new Hitbox(positionCurrent.getY() + 2, positionCurrent.getX() + width - 2, positionCurrent.getY() + height - 2, positionCurrent.getX() + 2);
    }

    public void onDeath() {
        if (dead) return;
        dead = true;
        if (UTILS.pixelToChunks(positionCurrent.getY()) < CONSTANTS.CHUNKS_VERTICAL / 2) {
            sequencedSprite.gotoSequence("death-water");
        } else {
            sequencedSprite.gotoSequence("death-land");
        }

        CompletableFuture.delayedExecutor(CONSTANTS.RESPAWN_DELAY, TimeUnit.MILLISECONDS).execute(() -> {
            dead = false;
            positionCurrent = new Point(7 * CONSTANTS.CHUNK_SIZE, 14 * CONSTANTS.CHUNK_SIZE);
            positionAbsolute = new Point(7 * CONSTANTS.CHUNK_SIZE, 14 * CONSTANTS.CHUNK_SIZE);
            sequencedSprite.gotoSequence("up");

        });
    }

    public void onRestart() {
        inputBlocked = true;
        sequencedSprite.gotoSequence("dead");

        positionCurrent = new Point(7 * CONSTANTS.CHUNK_SIZE, 14 * CONSTANTS.CHUNK_SIZE);
        positionAbsolute = new Point(7 * CONSTANTS.CHUNK_SIZE, 14 * CONSTANTS.CHUNK_SIZE);

        CompletableFuture.delayedExecutor(CONSTANTS.RESPAWN_DELAY, TimeUnit.MILLISECONDS).execute(() -> {
            inputBlocked = false;
            sequencedSprite.gotoSequence("up");
        });
    }

    public void draw(PApplet pApplet) {

        if (Math.abs(movementX) >= 1) {
            positionCurrent.setX(positionCurrent.getX() + (int) movementX);
            positionAbsolute.setX(positionAbsolute.getX() + (int) movementX);
            movementX = 0;
        }

        if (positionAbsolute.getX() < positionCurrent.getX()) {
            positionCurrent.setX(positionCurrent.getX() - movementSpeed);
        }
        if (positionAbsolute.getX() > positionCurrent.getX()) {
            positionCurrent.setX(positionCurrent.getX() + movementSpeed);
        }
        if (positionAbsolute.getY() < positionCurrent.getY()) {
            positionCurrent.setY(positionCurrent.getY() - movementSpeed);
        }
        if (positionAbsolute.getY() > positionCurrent.getY()) {
            positionCurrent.setY(positionCurrent.getY() + movementSpeed);
        }
        sequencedSprite.draw(pApplet, positionCurrent);
    }

}
