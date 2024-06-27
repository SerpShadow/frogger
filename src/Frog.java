import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.*;
import processing.core.PImage;
import processing.core.PApplet;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class Frog extends GameObject {

    private final Point startPosition;

    private boolean dead = false;
    private boolean inputBlocked = false;


    private final SequencedSprite sequencedSprite = new SequencedSprite(CONSTANTS.CHUNK_SIZE, CONSTANTS.CHUNK_SIZE, 30, ANCHORTYPE.TOP_LEFT);

    /**
     * Constructs a Frog object with the specified parameters.
     *
     * @param pApplet       the PApplet instance
     * @param startPosition the starting position of the frog
     * @param movementSpeed the speed of the frog's movement
     */
    public Frog(PApplet pApplet, Point startPosition, int movementSpeed) {
        super(1, movementSpeed, UTILS.chunksToPixel(7), new Hitbox(2, -2, -2, 2));
        this.startPosition = startPosition;
        setFrogToStartPosition();

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

    /**
     * Sets the frog to its starting position.
     */
    private void setFrogToStartPosition() {
        setPositionX(startPosition.getX());
        setPositionY(startPosition.getY());
        setDestinationX(startPosition.getX());
        setDestinationY(startPosition.getY());
    }

    /**
     * Checks if the input is blocked.
     *
     * @return true if input is blocked, false otherwise
     */
    public boolean isInputBlocked() {
        return inputBlocked;
    }

    /**
     * Gets the sequenced sprite of the frog.
     *
     * @return the sequenced sprite
     */
    public SequencedSprite getSequencedSprite() {
        return sequencedSprite;
    }

    /**
     * Checks if the frog is dead.
     *
     * @return true if the frog is dead, false otherwise
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Handles the frog's death sequence.
     */
    public void onDeath() {
        if (dead) return;
        dead = true;
        if (UTILS.pixelToChunks(getPositionY()) < CONSTANTS.CHUNKS_VERTICAL / 2) {
            sequencedSprite.gotoSequence("death-water");
        } else {
            sequencedSprite.gotoSequence("death-land");
        }

        CompletableFuture.delayedExecutor(CONSTANTS.RESPAWN_DELAY, TimeUnit.MILLISECONDS).execute(() -> {
            dead = false;
            setFrogToStartPosition();
            sequencedSprite.gotoSequence("up");

        });
    }

    /**
     * Handles the frog's restart sequence.
     */
    public void onRestart() {
        inputBlocked = true;
        setFrogToStartPosition();
        sequencedSprite.gotoSequence("dead");

        CompletableFuture.delayedExecutor(CONSTANTS.RESPAWN_DELAY, TimeUnit.MILLISECONDS).execute(() -> {
            inputBlocked = false;
            sequencedSprite.gotoSequence("up");
        });
    }

    /**
     * Draws the frog on the PApplet and checks if the position needs to be changed based on the destination.
     *
     * @param pApplet the PApplet instance
     */
    public void draw(PApplet pApplet) {
        super.draw(pApplet);

        if (getDestinationX() < getPositionX()) {
            setPositionX(getPositionX() - (int) getMovementSpeed());
        } else if (getDestinationX() > getPositionX()) {
            setPositionX(getPositionX() + (int) getMovementSpeed());
        }
        if (getDestinationY() < getPositionY()) {
            setPositionY(getPositionY() - (int) getMovementSpeed());
        } else if (getDestinationY() > getPositionY()) {
            setPositionY(getPositionY() + (int) getMovementSpeed());
        }
        sequencedSprite.draw(pApplet, getPosition());
    }

}
