import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.*;
import processing.core.PImage;
import processing.core.PApplet;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class Frog extends GameObject {

    private final int width = CONSTANTS.CHUNK_SIZE;
    private final int height = CONSTANTS.CHUNK_SIZE;
    private final Point startPosition;

    private boolean dead = false;
    private boolean inputBlocked = false;


    private final SequencedSprite sequencedSprite = new SequencedSprite(width, height, 30, ANCHORTYPE.TOP_LEFT);

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

    private void setFrogToStartPosition() {
        setPositionX(startPosition.getX());
        setPositionY(startPosition.getY());
        setDestinationX(startPosition.getX());
        setDestinationY(startPosition.getY());
    }

    public boolean isInputBlocked() {
        return inputBlocked;
    }

    public SequencedSprite getSequencedSprite() {
        return sequencedSprite;
    }

    public boolean isDead() {
        return dead;
    }

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

    public void onRestart() {
        inputBlocked = true;
        sequencedSprite.gotoSequence("dead");

        CompletableFuture.delayedExecutor(CONSTANTS.RESPAWN_DELAY, TimeUnit.MILLISECONDS).execute(() -> {
            inputBlocked = false;
            setFrogToStartPosition();
            sequencedSprite.gotoSequence("up");
        });
    }

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
